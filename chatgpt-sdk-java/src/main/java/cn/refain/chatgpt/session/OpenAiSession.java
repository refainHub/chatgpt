package cn.refain.chatgpt.session;

import cn.refain.chatgpt.domain.chat.ChatCompletionRequest;
import cn.refain.chatgpt.domain.chat.ChatCompletionResponse;
import cn.refain.chatgpt.domain.qa.QACompletionRequest;
import cn.refain.chatgpt.domain.qa.QACompletionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;


/**
 * @Author: refain
 * @Description: OpenAi 会话接口
 * @Date: 2024/2/28 15:17
 * @Version: 1.0
 */
public interface OpenAiSession {


    /**
     * 文本问答；简单请求
     *
     * @param question 请求信息
     * @return 应答结果
     */
    QACompletionResponse completions(String question);

    /**
     * 文本问答
     * @param qaCompletionRequest 请求信息
     * @return                    返回结果
     */
    @Deprecated
    QACompletionResponse completions(QACompletionRequest qaCompletionRequest);



    /**
     * 问答模型 GPT-3.5/4.0
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    ChatCompletionResponse completions(ChatCompletionRequest chatCompletionRequest);
    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈
     *
     * @param apiHostByUser         自定义host
     * @param apiKeyByUser          自定义Key
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听；通过监听的 onEvent 方法接收数据
     * @return 应答结果
     */
    EventSource chatCompletions(String apiHostByUser, String apiKeyByUser, ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;


    /**
     * 问答模型 GPT-3.5/4.0 & 流式反馈
     *
     * @param chatCompletionRequest 请求信息
     * @param eventSourceListener   实现监听；通过监听的 onEvent 方法接收数据
     * @return 应答结果
     */
    EventSource chatCompletions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;



}

