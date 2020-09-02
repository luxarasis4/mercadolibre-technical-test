
package com.mercadolibre.service;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mercadolibre.rest.controller.dto.CouponRequest;
import com.mercadolibre.rest.controller.dto.CouponResponse;

public interface CouponService {
    public CouponResponse coupon(CouponRequest request)
            throws JsonMappingException, JsonProcessingException;

    public List<String> calculate(Map<String, Float> items, Float amount);
}
