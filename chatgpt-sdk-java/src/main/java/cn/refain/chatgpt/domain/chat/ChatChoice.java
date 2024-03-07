package cn.refain.chatgpt.domain.chat;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: refain
 * @Description: 对话信息
 * @Date: 2024/2/28 15:47
 * @Version: 1.0
 */
@Data
public class ChatChoice implements Serializable {

    private long index;
    /** stream = true 请求参数里返回的属性是 delta */
    @JsonProperty("delta")
    private Message delta;
    /** stream = false 请求参数里返回的属性是 delta */
    @JsonProperty("message")
    private Message message;
    @JsonProperty("finish_reason")
    private String finishReason;

}
