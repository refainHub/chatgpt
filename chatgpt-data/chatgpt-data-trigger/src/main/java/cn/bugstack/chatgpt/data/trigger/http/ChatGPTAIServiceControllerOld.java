package cn.bugstack.chatgpt.data.trigger.http;

import cn.bugstack.chatgpt.common.Constants;
import cn.bugstack.chatgpt.data.trigger.http.dto.ChatGPTRequestDTO;
import cn.bugstack.chatgpt.data.types.exception.ChatGPTException;
import cn.bugstack.chatgpt.domain.chat.ChatChoice;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.chat.Message;
import cn.bugstack.chatgpt.session.OpenAiSession;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @author refain
 * @description
 * @create 2023-07-23 08:46
 */
@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v0/")
public class ChatGPTAIServiceControllerOld {

    @Resource
    private OpenAiSession openAiSession;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;
    /**
     * 流式问题，ChatGPT 请求接口
     * <p>
     * curl -X POST \
     * http://localhost:8080/api/v1/chat/completions \
     * -H 'Content-Type: application/json;charset=utf-8' \
     * -H 'Authorization: b8b6' \
     * -d '{
     * "messages": [
     * {
     * "content": "写一个java冒泡排序",
     * "role": "user"
     * }
     * ],
     * "model": "gpt-3.5-turbo"
     * }'
     */
    @RequestMapping(value = "chat/completions", method = RequestMethod.POST)
    public ResponseBodyEmitter completionsStream(@RequestBody ChatGPTRequestDTO request, @RequestHeader("Authorization") String token, HttpServletResponse response) {
        log.info("流式问答请求开始，使用模型：{} 请求信息：{}", request.getModel(), JSON.toJSONString(request.getMessages()));
        try {
            // 1. 基础配置；流式输出、编码、禁用缓存
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            if (!token.equals("b8b6")) throw new RuntimeException("token err!");

            // 2. 异步处理 HTTP 响应处理类
            ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
            emitter.onCompletion(() -> {
                log.info("流式问答请求完成，使用模型：{}", request.getModel());
            });
            emitter.onError(throwable -> log.error("流式问答请求疫情，使用模型：{}", request.getModel(), throwable));

            // 3.1 构建参数
            List<Message> messages = request.getMessages().stream()
                    .map(entity -> Message.builder()
                            .role(Constants.Role.valueOf(entity.getRole().toUpperCase()))
                            .content(entity.getContent())
                            .name(entity.getName())
                            .build())
                    .collect(Collectors.toList());

            ChatCompletionRequest chatCompletion = ChatCompletionRequest
                    .builder()
                    .stream(true)
                    .messages(messages)
                    .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                    .build();

            // 3.2 请求应答
            openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
                @Override
                public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
                    ChatCompletionResponse chatCompletionResponse = JSON.parseObject(data, ChatCompletionResponse.class);
                    List<ChatChoice> choices = chatCompletionResponse.getChoices();
                    for (ChatChoice chatChoice : choices) {
                        Message delta = chatChoice.getDelta();
                        if (Constants.Role.ASSISTANT.getCode().equals(delta.getRole())) continue;

                        // 应答完成
                        String finishReason = chatChoice.getFinishReason();
                        if (StringUtils.isNoneBlank(finishReason) && "stop".equals(finishReason)) {
                            emitter.complete();
                            break;
                        }

                        // 发送信息
                        try {
                            emitter.send(delta.getContent());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            });
            return emitter;
        } catch (Exception e) {
            log.error("流式应答，请求模型：{} 发生异常", request.getModel(), e);
            throw new ChatGPTException(e.getMessage());
        }
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public ResponseBodyEmitter completionsStream(HttpServletResponse response) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");

        ResponseBodyEmitter emitter = new ResponseBodyEmitter();

        threadPoolExecutor.execute(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    emitter.send("strdddddddddddddddd\r\n" + i);
                    Thread.sleep(100);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            emitter.complete();
        });

        return emitter;
    }

}
