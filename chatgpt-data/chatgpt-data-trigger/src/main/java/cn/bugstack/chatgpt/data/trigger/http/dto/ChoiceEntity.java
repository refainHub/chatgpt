package cn.bugstack.chatgpt.data.trigger.http.dto;

import lombok.Data;

/**
 * @author refain
 * @description
 * @create 2023-07-16 08:47
 */
@Data
public class ChoiceEntity {

    /** stream = true 请求参数里返回的属性是 delta */
    private MessageEntity delta;
    /** stream = false 请求参数里返回的属性是 delta */
    private MessageEntity message;

}
