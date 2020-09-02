
package com.mercadolibre.rest.client;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.rest.client.dto.MercadolibreGetItemByIdResponse;

@Component
public class MercadolibreClient {
    private static final Logger logger = LoggerFactory.getLogger(MercadolibreClient.class);
    private static final String PATH_GET_ITEM_BY_ID = "/items/{0}";

    @Value("${mercadolibre.rest.base-url}")
    private String baseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public MercadolibreGetItemByIdResponse getItemById(String id)
            throws JsonMappingException, JsonProcessingException {
        MercadolibreGetItemByIdResponse response = null;

        try {
            response = restTemplate
                    .getForEntity(baseUrl + MessageFormat.format(PATH_GET_ITEM_BY_ID, id),
                            MercadolibreGetItemByIdResponse.class)
                    .getBody();
        } catch (HttpClientErrorException e) {
            String responseString = e.getResponseBodyAsString();

            logger.error("GetItemById error, response[{}]", responseString);

            response = new ObjectMapper().readValue(responseString,
                    MercadolibreGetItemByIdResponse.class);
        }

        return response;
    }
}
