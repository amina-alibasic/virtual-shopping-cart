package com.shopping.cart.model;

public class CalculatedReceipt {
    private Double grandTotal;
    private Double subtotalBeforeDiscounts;
    private Double taxTotalBeforeDiscounts;
    private Double taxTotalAfterDiscounts;
    private Double taxableSubtotalBeforeDiscounts;
    private Double discountTotal;
    private Double subtotalAfterDiscounts;
    private Double taxableSubtotalAfterDiscounts;
    private final Double taxRate = 0.0825;

    public Double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getSubtotalBeforeDiscounts() {
        return subtotalBeforeDiscounts;
    }

    public void setSubtotalBeforeDiscounts(Double subtotalBeforeDiscounts) {
        this.subtotalBeforeDiscounts = subtotalBeforeDiscounts;
    }

    public Double getTaxTotalBeforeDiscounts() {
        return taxTotalBeforeDiscounts;
    }

    public void setTaxTotalBeforeDiscounts(Double taxTotal) {
        this.taxTotalBeforeDiscounts = taxTotal;
    }

    public Double getTaxTotalAfterDiscounts() {
        return taxTotalAfterDiscounts;
    }

    public void setTaxTotalAfterDiscounts(Double taxTotal) {
        this.taxTotalAfterDiscounts = taxTotal;
    }

    public Double getTaxableSubtotalBeforeDiscounts() {
        return taxableSubtotalBeforeDiscounts;
    }

    public void setTaxableSubtotalBeforeDiscounts(Double taxableSubtotalBeforeDiscounts) {
        this.taxableSubtotalBeforeDiscounts = taxableSubtotalBeforeDiscounts;
    }

    public Double getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(Double discountTotal) {
        this.discountTotal = discountTotal;
    }

    public Double getSubtotalAfterDiscounts() {
        return subtotalAfterDiscounts;
    }

    public void setSubtotalAfterDiscounts(Double subtotalAfterDiscounts) {
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
    }

    public Double getTaxableSubtotalAfterDiscounts() {
        return taxableSubtotalAfterDiscounts;
    }

    public void setTaxableSubtotalAfterDiscounts(Double taxableSubtotalAfterDiscounts) {
        this.taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts;
    }

    public Double getTaxRate() {
        return taxRate;
    }
}
