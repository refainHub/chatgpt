package cn.refain.chatgpt.session;

import cn.refain.chatgpt.IOpenAiApi;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.jetbrains.annotations.NotNull;


/**
 * @Author: refain
 * @Description:配置信息
 * @Date: 2024/2/28 15:44
 * @Version: 1.0
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    @Setter
    private IOpenAiApi openAiApi;

    @Getter
    @Setter
    private OkHttpClient okHttpClient;

    @Getter
    @NotNull
    private String apiKey;

    @Getter
    private String apiHost;

    /**
     * 字段废弃，不在使用
     */
    @Getter
    @Deprecated
    private String authToken;

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }

}