package cn.bugstack.chatgpt.domain.embedd;

import cn.bugstack.chatgpt.domain.other.Usage;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author refain
 * @description 反馈对象
 */
@Data
public class EmbeddingResponse implements Serializable {

    private String object;
    private List<Item> data;
    private String model;
    private Usage usage;

}
