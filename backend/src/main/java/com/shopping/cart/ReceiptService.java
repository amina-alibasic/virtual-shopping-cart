package com.shopping.cart;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReceiptService {

    private Double grandTotal;
    private Double subtotal;
    private Double taxTotal;
    private Double taxableSubtotal;
    private final Double taxRate = 0.0825;

    public JSONObject calculateGrandTotal(Receipt receipt){
        // set grand total to 0 each time the API is called (don't persist the value)
        this.grandTotal = 0.00;

        // calculate grand total
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
            this.grandTotal += item.getPrice();
        }

        JSONObject resp = new JSONObject();
        resp.put("grandTotal", this.grandTotal);
        return resp;
    }

    public JSONObject calculateSubtotalAndTaxTotal(Receipt receipt){
        this.subtotal = 0.00;
        // if total hasn't been calculated yet
        if(this.grandTotal == null){
            this.calculateGrandTotal(receipt);
        }
        // calculate subtotal and tax total
        this.subtotal = this.grandTotal / (1 + this.taxRate);
        this.taxTotal = this.grandTotal * taxRate;

        JSONObject result = new JSONObject();
        result.put("subtotal", this.subtotal);
        result.put("taxTotal", this.taxTotal);
        result.put("grandTotal", this.grandTotal);
        return result;

    }

    public JSONObject calculateTaxableSubtotal(Receipt receipt){
        this.taxableSubtotal = 0.00;
        // if total hasn't been calculated yet
        if(this.grandTotal == null){
            this.calculateGrandTotal(receipt);
        }

        // if subtotal hasn't been calculated yet
        if(this.subtotal == null || this.taxTotal == null){
            this.calculateSubtotalAndTaxTotal(receipt);
        }

        // calculate taxable subtotal
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
           if(item.getIsTaxable()) {
               this.taxableSubtotal += item.getPrice() * this.taxRate;
           }
        }

        JSONObject result = new JSONObject();
        result.put("subtotal", this.subtotal);
        result.put("taxTotal", this.taxTotal);
        result.put("grandTotal", this.grandTotal);
        result.put("taxableSubtotal", this.taxableSubtotal);
        return result;

    }
}
