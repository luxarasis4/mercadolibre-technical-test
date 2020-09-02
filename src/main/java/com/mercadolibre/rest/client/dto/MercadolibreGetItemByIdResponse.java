
package com.mercadolibre.rest.client.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class MercadolibreGetItemByIdResponse {
    @JsonAlias("id")
    private String id;
    @JsonAlias("title")
    private String title;
    @JsonAlias("price")
    private Float price;
    @JsonAlias("site_id")
    private String sideId;
    @JsonAlias("message")
    private String message;
    @JsonAlias("error")
    private String error;
    @JsonAlias("status")
    private String status;
    @JsonAlias("cause")
    private List<String> cause;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSideId() {
        return sideId;
    }

    public void setSideId(String sideId) {
        this.sideId = sideId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getCause() {
        return cause;
    }

    public void setCause(List<String> cause) {
        this.cause = cause;
    }
}
