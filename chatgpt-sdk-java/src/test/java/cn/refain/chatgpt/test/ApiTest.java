package cn.refain.chatgpt.test;


import cn.refain.chatgpt.common.Constants;

import cn.refain.chatgpt.domain.chat.ChatCompletionRequest;
import cn.refain.chatgpt.domain.chat.ChatCompletionResponse;
import cn.refain.chatgpt.domain.chat.Message;
import cn.refain.chatgpt.domain.other.OpenAiResponse;
import cn.refain.chatgpt.domain.qa.QACompletionRequest;
import cn.refain.chatgpt.domain.qa.QACompletionResponse;
import cn.refain.chatgpt.session.Configuration;
import cn.refain.chatgpt.session.OpenAiSession;
import cn.refain.chatgpt.session.OpenAiSessionFactory;
import cn.refain.chatgpt.session.defaults.DefaultOpenAiSessionFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;


@Slf4j
public class ApiTest {

    private OpenAiSession openAiSession;

    @Before
    public void test_OpenAiSessionFactory() {
        // 1. 配置文件 [联系小傅哥获取key]
        // 1.1 官网原始 apiHost https://api.openai.com/ - 官网的Key可直接使用
        // 1.2 三方公司 apiHost https://pro-share-aws-api.zcyai.com/ - 需要找我获得 Key 【支持3.5\4.0流式问答模型调用，有些模型已废弃不对接使用】
        Configuration configuration = new Configuration();
        configuration.setApiHost("https://pro-share-aws-api.zcyai.com/");
        configuration.setApiKey("sk-lFAkwdXFIFI93gvF4183917b5b42491e864b538862118692");
        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 3. 开启会话
        this.openAiSession = factory.openSession();
    }

    /**
     * 简单问答模式
     */
    @Test
    public void test_qa_completions() throws JsonProcessingException {
        QACompletionResponse response01 = openAiSession.completions("写个java冒泡排序");
        log.info("测试结果：{}", new ObjectMapper().writeValueAsString(response01.getChoices()));
    }

    /**
     * 此对话模型 3.5 接近于官网体验
     */
    @Test
    public void test_chat_completions() {
        // 1. 创建参数
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(Collections.singletonList(Message.builder().role(Constants.Role.USER).content("写一个java冒泡排序").build()))
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .build();
        // 2. 发起请求
        ChatCompletionResponse chatCompletionResponse = openAiSession.completions(chatCompletion);
        // 3. 解析结果
        chatCompletionResponse.getChoices().forEach(e -> {
            log.info("测试结果：{}", e.getMessage());
        });
    }

    /**
     * 上下文对话
     */
    @Test
    public void test_chat_completions_context() {
        // 1-1. 创建参数
        ChatCompletionRequest chatCompletion = ChatCompletionRequest
                .builder()
                .messages(new ArrayList<>())
                .model(ChatCompletionRequest.Model.GPT_3_5_TURBO.getCode())
                .user("testUser01")
                .build();
        // 写入请求信息
        chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content("写一个java冒泡排序").build());

        // 1-2. 发起请求
        ChatCompletionResponse chatCompletionResponse01 = openAiSession.completions(chatCompletion);
        log.info("测试结果：{}", chatCompletionResponse01.getChoices());

        // 写入请求信息
        chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content(chatCompletionResponse01.getChoices().get(0).getMessage().getContent()).build());
        chatCompletion.getMessages().add(Message.builder().role(Constants.Role.USER).content("换一种写法").build());

        ChatCompletionResponse chatCompletionResponse02 = openAiSession.completions(chatCompletion);
        log.info("测试结果：{}", chatCompletionResponse02.getChoices());
    }







}

