package cn.refain.chatgpt.domain.other;

/**
 * @Author: refain
 * @Description: 使用量
 * @Date: 2024/2/28 15:53
 * @Version: 1.0
 */


import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @Author: refain
 * @Description: 使用量
 * @Date: 2024/2/28 15:53
 * @Version: 1.0
 */

public class Usage implements Serializable {

    /** 提示令牌 */
    @JsonProperty("prompt_tokens")
    private long promptTokens;
    /** 完成令牌 */
    @JsonProperty("completion_tokens")
    private long completionTokens;
    /** 总量令牌 */
    @JsonProperty("total_tokens")
    private long totalTokens;

    public long getPromptTokens() {
        return promptTokens;
    }

    public void setPromptTokens(long promptTokens) {
        this.promptTokens = promptTokens;
    }

    public long getCompletionTokens() {
        return completionTokens;
    }

    public void setCompletionTokens(long completionTokens) {
        this.completionTokens = completionTokens;
    }

    public long getTotalTokens() {
        return totalTokens;
    }

    public void setTotalTokens(long totalTokens) {
        this.totalTokens = totalTokens;
    }

}
