package com.shopping.cart;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReceiptService {

    public Double calculateGrandTotal(Receipt receipt){
        Double total = new Double(0.00);
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
            total += item.getPrice();
        }
        return total;

    }
}
