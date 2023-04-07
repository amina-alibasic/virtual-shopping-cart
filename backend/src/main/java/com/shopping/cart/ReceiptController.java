package com.shopping.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static ch.qos.logback.core.joran.spi.ConsoleTarget.SystemOut;

@RestController
@RequestMapping("/api")
public class ReceiptController {
    @Autowired
    ReceiptService receiptService;
    @GetMapping("/calculate-total")
    public ResponseEntity<Double> calculateTotal(@RequestBody Receipt receiptJSON) {
        Double totalOfReceipt = receiptService.calculateTotal(receiptJSON);
        if (totalOfReceipt == null) {
            return ResponseEntity.notFound().build();
        } else {
            System.out.println("**************************************************************");
            System.out.println( ResponseEntity.ok(totalOfReceipt));
            return ResponseEntity.ok(totalOfReceipt);
        }
    }
}
