package cn.bugstack.chatgpt.domain.billing;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author refain
 * @description 消耗列表数据
 */
@Data
public class LineItem {
    /** 模型 */
    private String name;
    /** 金额 */
    private BigDecimal cost;
}
