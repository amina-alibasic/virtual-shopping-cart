package com.shopping.cart.model;

public class Item
{
    private String itemName;
    private Long sku;
    private Boolean isTaxable;
    private Boolean ownBrand;
    private Double price;

    public String getItemName() {
        return itemName;
    }
    public Long getSku() {
        return sku;
    }
    public Boolean getIsTaxable() {
        return isTaxable;
    }
    public Boolean getOwnBrand() {
        return ownBrand;
    }
    public Double getPrice() {
        return price;
    }
}
