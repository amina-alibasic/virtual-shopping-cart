package com.shopping.cart.service;

import com.shopping.cart.model.Coupon;
import com.shopping.cart.model.Receipt;
import org.json.JSONObject;

import java.util.List;

public interface IReceiptService {
    JSONObject calculateGrandTotal(Receipt receipt);
    JSONObject calculateSubtotalAndTaxTotal(Receipt receipt);
    JSONObject calculateTaxableSubtotal(Receipt receipt);
    JSONObject applyCoupons(Receipt receipt, List<Coupon> coupons);
}
