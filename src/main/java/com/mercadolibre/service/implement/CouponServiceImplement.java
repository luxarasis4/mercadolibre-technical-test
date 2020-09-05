
package com.mercadolibre.service.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mercadolibre.database.entity.ItemEntity;
import com.mercadolibre.database.repository.ItemRepository;
import com.mercadolibre.exception.CanNotBuyNothingException;
import com.mercadolibre.rest.client.MercadolibreClient;
import com.mercadolibre.rest.client.dto.MercadolibreGetItemByIdResponse;
import com.mercadolibre.rest.controller.dto.CouponRequest;
import com.mercadolibre.rest.controller.dto.CouponResponse;
import com.mercadolibre.service.CouponService;

@Service
public class CouponServiceImplement implements CouponService {
    private static final Logger logger = LoggerFactory.getLogger(CouponServiceImplement.class);

    private float amount;
    private List<Entry<String, Float>> itemSet;

    @Autowired
    private MercadolibreClient mercadolibreClient;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public CouponResponse coupon(CouponRequest request) {
        float amount = request.getAmount();
        List<String> itemIds = request.getItemIds();
        Map<String, Float> items = new HashMap<>();

        List<ItemEntity> itemEntities = itemRepository.findAllById(itemIds);
        for (ItemEntity itemEntity : itemEntities) {
            String itemId = itemEntity.getId();
            items.put(itemId, itemEntity.getPrice());

            itemIds.remove(itemId);
        }

        for (String itemId : itemIds) {
            try {
                MercadolibreGetItemByIdResponse item = mercadolibreClient.getItemById(itemId);

                Float itemPrice = item.getPrice();

                if (itemPrice != null && itemPrice > 0)
                    items.put(itemId, item.getPrice());
            } catch (JsonProcessingException e) {
                logger.error("ERROR ->", e);
            }
        }

        List<String> itemIdsResult = this.calculate(items, amount);

        if (itemIdsResult.isEmpty())
            throw new CanNotBuyNothingException("no puede comprar nada");

        float total = 0;
        for (String itemId : itemIdsResult) {
            total += items.get(itemId);
        }

        CouponResponse response = new CouponResponse();
        response.setTotal(total);
        response.setItemCodes(itemIdsResult);

        return response;
    }

    @Override
    public List<String> calculate(Map<String, Float> items, Float amount) {
        logger.info("Coupon calculate init, items[{}] - amount[{}]", items, amount);

        this.amount = amount;
        this.itemSet = new ArrayList<>(items.entrySet());

        float initAmount = 0;
        List<String> initItemCodes = new ArrayList<>();

        CouponResponse couponResponse = new CouponResponse();
        couponResponse.setTotal(initAmount);
        couponResponse.setItemCodes(initItemCodes);

        for (int i = 0; i < this.itemSet.size(); i++) {
            CouponResponse couponAux = this.calculate(initItemCodes, initAmount, i);

            if (couponAux.getTotal() > couponResponse.getTotal()) {
                couponResponse.setTotal(couponAux.getTotal());
                couponResponse.setItemCodes(couponAux.getItemCodes());
            }
        }

        logger.info("Coupon calculate init, response[{}]", couponResponse);

        return couponResponse.getItemCodes();
    }

    private CouponResponse calculate(List<String> itemsCode, float itemsAmount, int index) {
        CouponResponse couponResult = new CouponResponse();
        couponResult.setItemCodes(itemsCode);
        couponResult.setTotal(itemsAmount);

        for (int i = index; i < this.itemSet.size(); i++) {
            float auxAmount = itemsAmount + this.itemSet.get(index).getValue();
            String indexCode = this.itemSet.get(index).getKey();

            if (auxAmount <= this.amount) {
                List<String> auxItemsCode = new ArrayList<>(itemsCode);
                auxItemsCode.add(indexCode);

                logger.info("Coupon calculate, aux-items-code[{}] - aux-amount[{}] - i[{}]",
                        auxItemsCode, auxAmount, i);

                CouponResponse couponAux = this.calculate(auxItemsCode, auxAmount, i + 1);

                if (couponAux.getTotal() > couponResult.getTotal()) {
                    couponResult.setTotal(couponAux.getTotal());
                    couponResult.setItemCodes(couponAux.getItemCodes());
                }
            }
        }

        return couponResult;
    }
}
