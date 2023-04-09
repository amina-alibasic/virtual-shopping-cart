package com.shopping.cart.service;

import com.shopping.cart.model.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReceiptService implements IReceiptService {

    private CalculatedReceipt calculatedReceipt = new CalculatedReceipt();

    @Override
    public JSONObject calculateGrandTotal(Receipt receipt){
        // set grand total to 0 each time the API is called (don't persist the value)
        this.calculatedReceipt.setGrandTotal(0.00);

        // calculate grand total
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
            this.calculatedReceipt.setGrandTotal(this.calculatedReceipt.getGrandTotal() + item.getPrice());
        }

        JSONObject resp = new JSONObject();
        resp.put("grandTotal", this.calculatedReceipt.getGrandTotal());
        return resp;
    }

    @Override
    public JSONObject calculateSubtotalAndTaxTotal(Receipt receipt){
        this.calculatedReceipt.setSubtotalBeforeDiscounts(0.00);
        // if total hasn't been calculated yet
        if(isGrandTotalCalculated()){
            this.calculateGrandTotal(receipt);
        }
        // calculate subtotal and tax total
        this.calculatedReceipt.setSubtotalBeforeDiscounts(this.calculatedReceipt.getGrandTotal() / (1 + this.calculatedReceipt.getTaxRate()));
        this.calculatedReceipt.setTaxTotalBeforeDiscounts(this.calculatedReceipt.getGrandTotal() * this.calculatedReceipt.getTaxRate());

        JSONObject result = new JSONObject();
        result.put("subtotalBeforeDiscounts", this.calculatedReceipt.getSubtotalBeforeDiscounts());
        result.put("taxTotalBeforeDiscounts", this.calculatedReceipt.getTaxTotalBeforeDiscounts());
        result.put("grandTotal", this.calculatedReceipt.getGrandTotal());
        return result;

    }

    @Override
    public JSONObject calculateTaxableSubtotal(Receipt receipt){
        this.calculatedReceipt.setTaxableSubtotalBeforeDiscounts(0.00);

        if(isGrandTotalCalculated()){
            this.calculateGrandTotal(receipt);
        }

        // if subtotal hasn't been calculated yet
        if(isSubtotalCalculated()){
            this.calculateSubtotalAndTaxTotal(receipt);
        }

        // calculate taxable subtotal
        calculateTaxableSubtotalBeforeDiscounts(receipt.getItems());

        JSONObject result = new JSONObject();
        result.put("subtotalBeforeDiscounts", this.calculatedReceipt.getSubtotalBeforeDiscounts());
        result.put("taxTotalBeforeDiscounts", this.calculatedReceipt.getTaxTotalBeforeDiscounts());
        result.put("grandTotal", this.calculatedReceipt.getGrandTotal());
        result.put("taxableSubtotalBeforeDiscounts", this.calculatedReceipt.getTaxableSubtotalBeforeDiscounts());
        return result;

    }

    @Override
    public JSONObject applyCoupons(Receipt receipt, List<Coupon> coupons) {
        if(isGrandTotalCalculated()){
            this.calculateGrandTotal(receipt);
        }

        if(isSubtotalCalculated()){
            this.calculateSubtotalAndTaxTotal(receipt);
        }

        calculateDiscountTotal(coupons);
        calculateSubtotalAfterDiscounts();
        calculateTaxableSubtotalAfterDiscounts(receipt.getItems(),coupons);
        calculateTaxTotalAfterDiscounts();

        JSONObject result = new JSONObject();
        result.put("subtotalBeforeDiscounts", this.calculatedReceipt.getSubtotalBeforeDiscounts());
        result.put("discountTotal", this.calculatedReceipt.getDiscountTotal());
        result.put("subtotalAfterDiscounts", this.calculatedReceipt.getSubtotalAfterDiscounts());
        result.put("taxableSubtotalAfterDiscounts", this.calculatedReceipt.getTaxableSubtotalAfterDiscounts());
        result.put("taxTotalBeforeDiscounts", this.calculatedReceipt.getTaxTotalBeforeDiscounts());
        result.put("taxTotalAfterDiscounts", this.calculatedReceipt.getTaxTotalAfterDiscounts());
        result.put("grandTotal", this.calculatedReceipt.getGrandTotal());

        return result;
    }

    private Boolean isGrandTotalCalculated() {
        try{
            return Objects.isNull(this.calculatedReceipt.getGrandTotal());
        }
        catch (NullPointerException ex){
            return false;
        }
    }

    private Boolean isSubtotalCalculated() {
        try{
            return Objects.isNull(this.calculatedReceipt.getSubtotalBeforeDiscounts());
        }
        catch (NullPointerException ex) {
            return false;
        }

    }

    private void calculateTaxableSubtotalBeforeDiscounts(List<Item> items) {
        for(Item item : items){
            if(item.getIsTaxable()) {
                this.calculatedReceipt.setTaxableSubtotalBeforeDiscounts(
                        this.calculatedReceipt.getTaxableSubtotalBeforeDiscounts() +
                                (item.getPrice() * this.calculatedReceipt.getTaxRate()));
            }
        }
    }

    private void calculateSubtotalAfterDiscounts() {
        Double grandTotal = this.calculatedReceipt.getGrandTotal();
        Double discountTotal = this.calculatedReceipt.getDiscountTotal();
        // grand total after discounts applied = old grand total - total amount discounts applied
        this.calculatedReceipt.setSubtotalAfterDiscounts((grandTotal - discountTotal)
                / (1 + this.calculatedReceipt.getTaxRate()));
    }

    private void calculateDiscountTotal(List<Coupon> coupons) {
        this.calculatedReceipt.setDiscountTotal(0.00);
        for(Coupon coupon : coupons) {
            this.calculatedReceipt.setDiscountTotal(this.calculatedReceipt.getDiscountTotal() + coupon.getDiscountPrice());
        }
    }

    private void calculateTaxableSubtotalAfterDiscounts(List<Item> items, List<Coupon> coupons) {
        this.calculatedReceipt.setTaxableSubtotalAfterDiscounts(0.00);
        for(Item item : items){
            for(Coupon coupon : coupons){
                if(Objects.equals(coupon.getAppliedSku(), item.getSku())){
                    double newItemPrice = item.getPrice() - coupon.getDiscountPrice();
                    if(newItemPrice < 0){
                        // price cannot be negative
                        item.setPrice(0.00);
                    }
                    else {
                        // item has discount, new item price = old price - discount price
                        item.setPrice(newItemPrice);
                    }
                }
            }
            if(item.getIsTaxable()) {
                // if item is taxable, include its price into taxable subtotal
                this.calculatedReceipt.setTaxableSubtotalAfterDiscounts(
                        this.calculatedReceipt.getTaxableSubtotalAfterDiscounts() +
                                (item.getPrice() * this.calculatedReceipt.getTaxRate()));
            }
        }
    }

    private void calculateTaxTotalAfterDiscounts() {
        // Always do discount first then do tax of the discounted price
        // grand total after discounts applied = old grand total - total amount discounts applied
        Double grandTotal = this.calculatedReceipt.getGrandTotal();
        Double discountTotal = this.calculatedReceipt.getDiscountTotal();
        this.calculatedReceipt.setTaxTotalAfterDiscounts((grandTotal - discountTotal) * this.calculatedReceipt.getTaxRate());

    }
}
