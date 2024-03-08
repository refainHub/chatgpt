package cn.bugstack.chatgpt.domain.images;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author refain
 * @description 条目
 */
@Data
public class Item implements Serializable {

    private String url;
//    @JsonProperty("b64_json")
//    private String b64Json;
    @JsonProperty("revised_prompt")
    private String revisedPrompt;
}
