
package com.mercadolibre.rest.controller.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CouponResponse {
    @JsonProperty("item_ids")
    List<String> itemCodes;
    @JsonProperty("total")
    Float total;

    public List<String> getItemCodes() {
        return itemCodes;
    }

    public void setItemCodes(List<String> itemCodes) {
        this.itemCodes = itemCodes;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float amount) {
        this.total = amount;
    }

    @Override
    public String toString() {
        return "CouponResponse [itemCodes=" + itemCodes + ", total=" + total + "]";
    }
}
