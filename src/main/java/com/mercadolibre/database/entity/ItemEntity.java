
package com.mercadolibre.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
        name = "item")
public class ItemEntity implements Serializable {
    private static final long serialVersionUID = 2110349641920840787L;

    @Id
    @Column(
            name = "id")
    private String id;
    @Column(
            name = "price")
    private Float price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
