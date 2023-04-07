package com.shopping.cart;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReceiptService {
    private Double total = 0.0;

    public Double calculateTotal(Receipt receipt){
        List<Item> itemsInReceipt = receipt.getItems();
        for(Item item : itemsInReceipt){
            this.total += item.getPrice();
        }
        return total;

    }
}
