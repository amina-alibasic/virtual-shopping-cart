package com.shopping.cart;

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
    ReceiptService receiptService;
    @CrossOrigin("*")
    @RequestMapping(value = "/calculate-grand-total", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> calculateGrandTotal(@RequestBody Receipt receiptJSON) {
        Double totalOfReceipt = receiptService.calculateGrandTotal(receiptJSON);
        JSONObject resp = new JSONObject();
        resp.put("total", totalOfReceipt);

        if (totalOfReceipt == null) {
            return ResponseEntity.notFound().build();
        } else {
          return new ResponseEntity<>(resp.toString(), HttpStatus.OK);
        }
    }
}
