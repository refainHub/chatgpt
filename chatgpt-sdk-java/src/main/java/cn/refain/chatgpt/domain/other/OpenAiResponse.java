package cn.refain.chatgpt.domain.other;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/28 15:52
 * @Version: 1.0
 */
@Data
public class OpenAiResponse<T> implements Serializable {

    private String object;
    private List<T> data;
    private Error error;


    @Data
    public class Error {
        private String message;
        private String type;
        private String param;
        private String code;
    }

}
