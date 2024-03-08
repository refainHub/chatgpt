package cn.bugstack.chatgpt.data.domain.openai.model.aggregates;

import cn.bugstack.chatgpt.data.domain.openai.model.entity.MessageEntity;
import cn.bugstack.chatgpt.data.types.enums.ChatGPTModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author refain
 * @description
 * @create 2023-07-22 21:09
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatProcessAggregate {

    /** 验证信息 */
    private String token;
    /** 默认模型 */
    private String model = ChatGPTModel.GPT_3_5_TURBO.getCode();
    /** 问题描述 */
    private List<MessageEntity> messages;



}
