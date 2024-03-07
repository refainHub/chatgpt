package cn.refain.chatgpt.common;

/**
 * @Author: refain
 * @Description:通用类
 * @Date: 2024/2/28 15:07
 * @Version: 1.0
 */
public class Constants {

    public final static String NULL = "NULL";
    /**
     * 官网支持的请求角色类型；system、user、assistant
     * https://platform.openai.com/docs/guides/chat/introduction
     */

    public enum Role {

        SYSTEM("system"),
        USER("user"),
        ASSISTANT("assistant"),
        ;

        private String code;

        Role(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

    }

}
