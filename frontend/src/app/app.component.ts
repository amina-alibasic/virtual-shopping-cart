import { Component, OnInit } from '@angular/core';
import * as jsonData from '../assets/cart.json'
import { ReceiptService } from 'src/services/receipt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
  cart = jsonData;
  calculatedReceiptObj: CalculatedReceipt = new CalculatedReceipt;

  constructor(private receiptService: ReceiptService) { }


  showGrandTotal: Boolean = false;
  showSubtotalAndTaxTotal: Boolean = false;
  showTaxableSubtotal: Boolean = false;

  clearAll() {
    this.showGrandTotal = false;
    this.showSubtotalAndTaxTotal = false;
    this.showTaxableSubtotal = false;
    this.calculatedReceiptObj = new CalculatedReceipt;
  }

  calculateGrandTotal() {
    this.receiptService.calculateGrandTotal(this.cart).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showGrandTotal = true;
    this.showTaxableSubtotal = false;
    this.showSubtotalAndTaxTotal = false;
  }

  calculateSubtotalAndTaxTotal() {
    this.receiptService.calculateSubtotalAndTaxTotal(this.cart).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showGrandTotal = true;
    this.showSubtotalAndTaxTotal = true;
  }

  calculateTaxableSubtotal() {
    this.receiptService.calculateTaxableSubtotal(this.cart).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showSubtotalAndTaxTotal = true;
    this.showTaxableSubtotal = true;
  }
}

export class CalculatedReceipt {
  grandTotal: number = 0;
  subtotal: number = 0;
  taxTotal: number = 0;
  taxableSubtotal: number = 0;

}