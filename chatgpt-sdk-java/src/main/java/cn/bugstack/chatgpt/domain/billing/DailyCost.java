package cn.bugstack.chatgpt.domain.billing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @author refain
 * @description 金额消耗
 */
@Data
public class DailyCost {
    @JsonProperty("timestamp")
    private long timestamp;
    @JsonProperty("line_items")
    private List<LineItem> lineItems;
}
