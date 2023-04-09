package com.shopping.cart.wrapper;

import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.Receipt;
import java.util.List;


public class ApplyCouponsWrapper {
    private Receipt receipt;
    private List<Coupon> couponList;


    public Receipt getReceipt() {
        return receipt;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

}
