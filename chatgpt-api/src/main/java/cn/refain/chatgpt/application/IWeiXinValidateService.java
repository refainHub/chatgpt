package cn.refain.chatgpt.application;

/**
 * @Author: refain
 * @Description: 微信公众号验签服务
 * @Date: 2024/3/6 20:47
 * @Version: 1.0
 */

public interface IWeiXinValidateService {

    boolean checkSign(String signature, String timestamp, String nonce);

}

