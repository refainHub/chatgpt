package cn.bugstack.chatgpt.data.domain.auth.service;

import cn.bugstack.chatgpt.data.domain.auth.model.entity.AuthStateEntity;

/**
 * @Author: refain
 * @Description: 鉴权验证服务接口
 * @Date: 2024/3/9 16:29
 * @Version: 1.0
 */
public interface IAuthService {

    /**
     * 登录验证
     * @param code 验证码
     * @return Token
     */
    AuthStateEntity doLogin(String code);

    boolean checkToken(String token);

}
