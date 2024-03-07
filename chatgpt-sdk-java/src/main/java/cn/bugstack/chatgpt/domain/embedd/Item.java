package cn.bugstack.chatgpt.domain.embedd;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 小傅哥，微信：fustack
 * @description 条目信息
 * @github https://github.com/fuzhengwei
 * @Copyright 公众号：bugstack虫洞栈 | 博客：https://bugstack.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
@Data
public class Item implements Serializable {

    private String object;
    private List<BigDecimal> embedding;
    private Integer index;

}
