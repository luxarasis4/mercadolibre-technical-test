
package com.mercadolibre.service;

import java.util.List;
import java.util.Map;

import com.mercadolibre.rest.controller.dto.CouponRequest;
import com.mercadolibre.rest.controller.dto.CouponResponse;

public interface CouponService {
    public CouponResponse coupon(CouponRequest request);

    public List<String> calculate(Map<String, Float> items, Float amount);
}
