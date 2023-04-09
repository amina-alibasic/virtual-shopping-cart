package com.shopping.cart.service;

import com.shopping.cart.model.Receipt;
import org.json.JSONObject;

public interface IReceiptService {
    JSONObject calculateGrandTotal(Receipt receipt);
    JSONObject calculateSubtotalAndTaxTotal(Receipt receipt);
    JSONObject calculateTaxableSubtotal(Receipt receipt);
}
