package cn.bugstack.chatgpt.test;

import cn.bugstack.chatgpt.common.Constants;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionRequest;
import cn.bugstack.chatgpt.domain.chat.ChatCompletionResponse;
import cn.bugstack.chatgpt.domain.chat.Message;
import cn.bugstack.chatgpt.session.Configuration;
import cn.bugstack.chatgpt.session.OpenAiSession;
import cn.bugstack.chatgpt.session.OpenAiSessionFactory;
import cn.bugstack.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author 小傅哥，微信：fustack
 * @description 客户端输入测试
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Slf4j
public class ClientTest {

    public static void main(String[] args) throws InterruptedException, JsonProcessingException {
        // 1. 配置文件；
        // 1.1 官网原始 apiHost https://api.openai.com/ - 官网的Key可直接使用
        // 1.2 三方公司 apiHost https://pro-share-aws-api.zcyai.com/ - 需要找我获得 Key
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://pro-share-aws-api.zcyai.com/");
        configuration.setApiKey("sk-b0A0eSKTNxgBqrHv7aAa0808EdB849C89499D928648bD416");

        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        OpenAiSession openAiSession = factory.openSession();

        System.out.println("我是 OpenAI ChatGPT，请输入你的问题：");

        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .stream(true)
                .messages(new ArrayList<>())
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .maxTokens(1024)
                .build();

        // 3. 等待输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String text = scanner.nextLine();

            chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(text).build());
            EventSource eventSource = openAiSession.chatCompletions(chatCompletion, new EventSourceListener() {
                @Override
                public void onEvent(EventSource eventSource, String id, String type, String data) {
                    System.out.println(data);
                }

                @Override
                public void onFailure(EventSource eventSource, Throwable t, Response response) {
                    System.out.println(response.code());
                }

                @Override
                public void onClosed(EventSource eventSource) {
                    System.out.println("请输入你的问题：");
                }
            });
        }

    }

}
