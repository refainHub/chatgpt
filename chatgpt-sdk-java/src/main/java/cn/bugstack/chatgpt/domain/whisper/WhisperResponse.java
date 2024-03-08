package cn.bugstack.chatgpt.domain.whisper;

import lombok.Data;

import java.io.Serializable;

/**
 * @author refain
 * @description
 */
@Data
public class WhisperResponse implements Serializable {
    private String text;
}
