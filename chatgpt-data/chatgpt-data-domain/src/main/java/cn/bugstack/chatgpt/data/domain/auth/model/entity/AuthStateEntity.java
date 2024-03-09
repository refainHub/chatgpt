package cn.bugstack.chatgpt.data.domain.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: refain
 * @Description: 鉴权结果
 * @Date: 2024/3/9 16:26
 * @Version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthStateEntity {

    private String code;
    private String info;
    /**
     * 公众号id
     */
    private String openId;
    private String token;

}
