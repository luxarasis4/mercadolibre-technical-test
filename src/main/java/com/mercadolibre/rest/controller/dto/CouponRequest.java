
package com.mercadolibre.rest.controller.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAlias;

public class CouponRequest {
    @JsonAlias("item_ids")
    @NotNull
    @NotEmpty
    private List<String> itemIds;
    @JsonAlias("amount")
    @NotNull
    private Float amount;

    public CouponRequest() {
        super();
    }

    public CouponRequest(@NotNull @NotEmpty List<String> itemIds, @NotNull Float amount) {
        super();
        this.itemIds = itemIds;
        this.amount = amount;
    }

    public List<String> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<String> itemIds) {
        this.itemIds = itemIds;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
