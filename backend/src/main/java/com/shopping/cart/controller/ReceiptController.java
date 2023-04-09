package com.shopping.cart.controller;

import com.shopping.cart.model.Receipt;
import com.shopping.cart.service.IReceiptService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReceiptController {
    @Autowired
    IReceiptService receiptService;
    @CrossOrigin("*")
    @RequestMapping(value = "/calculate-grand-total", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> calculateGrandTotal(@RequestBody Receipt receipt) {
        JSONObject totalOfReceipt = receiptService.calculateGrandTotal(receipt);
        if (totalOfReceipt != null) {
            return new ResponseEntity<>(totalOfReceipt.toString(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/calculate-subtotal-and-tax-total", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> calculateSubtotalAndTaxTotal(@RequestBody Receipt receipt) {
        JSONObject response = receiptService.calculateSubtotalAndTaxTotal(receipt);
        if (response != null) {
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/calculate-taxable-subtotal", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> calculateTaxableSubtotal(@RequestBody Receipt receipt) {
        JSONObject response = receiptService.calculateTaxableSubtotal(receipt);
        if (response != null) {
            return new ResponseEntity<>(response.toString(), HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}
