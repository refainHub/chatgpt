package cn.refain.chatgpt;


import cn.refain.chatgpt.domain.chat.ChatCompletionRequest;
import cn.refain.chatgpt.domain.chat.ChatCompletionResponse;
import cn.refain.chatgpt.domain.other.OpenAiResponse;
import cn.refain.chatgpt.domain.qa.QACompletionRequest;
import cn.refain.chatgpt.domain.qa.QACompletionResponse;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.io.File;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author 小傅哥，微信：fustack
 * @description 以 ChatGPT 官网 API 模型，定义接口。官网：https://platform.openai.com/playground
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public interface IOpenAiApi {

    String v1_completions = "v1/completions";

    /**
     * 文本问答
     *
     * @param qaCompletionRequest 请求信息
     * @return 应答结果
     */
    @POST(v1_completions)
    Single<QACompletionResponse> completions(@Body QACompletionRequest qaCompletionRequest);

    String v1_chat_completions = "v1/chat/completions";

    /**
     * 问答模型；默认 GPT-3.5
     *
     * @param chatCompletionRequest 请求信息
     * @return 应答结果
     */
    @POST(v1_chat_completions)
    Single<ChatCompletionResponse> completions(@Body ChatCompletionRequest chatCompletionRequest);

    /**
     * 检索文件
     * curl https://api.openai.com/v1/files/file-XjGxS3KTG0uNmNOK362iJua3 \
     * -H "Authorization: Bearer $OPENAI_API_KEY"
     *
     * @param fileId 文件ID
     * @return 应答结果
     */
    @GET("v1/files/{file_id}")
    Single<File> retrieveFile(@Path("file_id") String fileId);

    /**
     * 检索文件内容信息
     * curl https://api.openai.com/v1/files/file-XjGxS3KTG0uNmNOK362iJua3/content \
     * -H "Authorization: Bearer $OPENAI_API_KEY" > file.jsonl
     *
     * @param fileId 文件ID
     * @return 应答结果
     */
    @Streaming
    @GET("v1/files/{file_id}/content")
    Single<ResponseBody> retrieveFileContent(@Path("file_id") String fileId);




}

