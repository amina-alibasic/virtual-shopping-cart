package com.shopping.cart.model;

public class Coupon {
    private String couponName;
    private Long appliedSku;
    private Double discountPrice;

    public String getCouponName() {
        return couponName;
    }

    public Long getAppliedSku() {
        return appliedSku;
    }

    public Double getDiscountPrice() {
        return discountPrice;
    }
}