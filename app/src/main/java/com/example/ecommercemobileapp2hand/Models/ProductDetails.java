package com.example.ecommercemobileapp2hand.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ProductDetails {
    @JsonProperty("product_details_id")
    private int product_details_id;
    @JsonProperty("product_id")
    private int product_id;
    @JsonProperty("product_color_id")
    private int product_color_id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sale_price")
    private BigDecimal sale_price;
    @JsonProperty("details_created_at")
    private Timestamp createdAt;
    public ProductDetails(){}

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public int getProduct_details_id() {
        return product_details_id;
    }

    public void setProduct_details_id(int product_details_id) {
        this.product_details_id = product_details_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_color_id() {
        return product_color_id;
    }

    public void setProduct_color_id(int product_color_id) {
        this.product_color_id = product_color_id;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getSale_price() {
        return sale_price;
    }

    public void setSale_price(BigDecimal sale_price) {
        this.sale_price = sale_price;
    }

    public ProductDetails(int product_details_id, int product_id, int product_color_id, String description, BigDecimal sale_price, Timestamp createdAt) {
        this.product_details_id = product_details_id;
        this.product_id = product_id;
        this.product_color_id = product_color_id;
        this.description = description;
        this.sale_price = sale_price;
        this.createdAt = createdAt;
    }
}
