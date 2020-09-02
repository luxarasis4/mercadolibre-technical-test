
package com.mercadolibre.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mercadolibre.rest.controller.dto.CouponRequest;
import com.mercadolibre.rest.controller.dto.CouponResponse;
import com.mercadolibre.service.CouponService;

@RestController
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("coupon")
    public ResponseEntity<CouponResponse> coupon(@Valid @RequestBody CouponRequest request)
            throws JsonMappingException, JsonProcessingException {
        return ResponseEntity.ok().body(couponService.coupon(request));
    }
}
