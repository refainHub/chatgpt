package cn.refain.chatgpt.domain.qa;


import cn.refain.chatgpt.domain.other.Usage;
import lombok.Data;

import java.awt.*;
import java.io.Serializable;
import java.util.List;


@Data
public class QACompletionResponse implements Serializable {

    /** ID */
    private String id;
    /** 对象 */
    private String object;
    /** 模型 */
    private String model;
    /** 对话 */
    private Choice[] choices;
    /** 创建 */
    private long created;
    /** 耗材 */
    private Usage usage;

}
