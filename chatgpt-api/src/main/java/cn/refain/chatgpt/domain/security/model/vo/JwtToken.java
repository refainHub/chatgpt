package cn.refain.chatgpt.domain.security.model.vo;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: refain
 * @Description:
 * @Date: 2024/2/26 22:39
 * @Version: 1.0
 */
public class JwtToken implements AuthenticationToken {

    private String jwt;

    public JwtToken(String jwt) {
        this.jwt = jwt;
    }

    /**
     * 等同于账户
     */

    @Override
    public Object getPrincipal() {
        return jwt;
    }

    /**
     * 等同于账户
     */

    @Override
    public Object getCredentials() {
        return jwt;
    }
}
