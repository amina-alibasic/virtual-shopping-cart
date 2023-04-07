package com.shopping.cart;

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

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    }

    public Boolean getTaxable() {
        return isTaxable;
    }

    public void setTaxable(Boolean taxable) {
        isTaxable = taxable;
    }

    public Boolean getOwnBrand() {
        return ownBrand;
    }

    public void setOwnBrand(Boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
