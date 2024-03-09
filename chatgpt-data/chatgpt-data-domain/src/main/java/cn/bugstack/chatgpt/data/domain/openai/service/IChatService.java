package cn.bugstack.chatgpt.data.domain.openai.service;

import cn.bugstack.chatgpt.data.domain.openai.model.aggregates.ChatProcessAggregate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author refain
 * @description
 * @create 2023-07-22 20:53
 */
public interface IChatService {

    ResponseBodyEmitter completions(ResponseBodyEmitter emitter, ChatProcessAggregate chatProcess);

}
