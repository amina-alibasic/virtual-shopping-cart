package com.shopping.cart;

import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
@Service
public class ReceiptService {

    public Double calculateTotal(Receipt receipt){
        Double total = 0.0;
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
            total += item.getPrice();
        }
        DecimalFormat df = new DecimalFormat("0.00");
        // format Double to 2 decimal places (using String), then convert to double again
        return Double.valueOf(df.format(total));

    }
}
