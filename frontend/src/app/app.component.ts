import { Component, OnInit } from '@angular/core';
import * as cartJson from '../assets/cart.json'
import * as couponsJson from '../assets/coupons.json'

import { ReceiptService } from 'src/services/receipt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  receipt: Receipt = cartJson;
  couponsList = couponsJson;
  calculatedReceiptObj: CalculatedReceipt = new CalculatedReceipt;

  constructor(private receiptService: ReceiptService) { }


  showGrandTotal: Boolean = false;
  showSubtotalAndTaxTotal: Boolean = false;
  showTaxableSubtotal: Boolean = false;
  applyCouponsView: Boolean = false;

  clearAll() {
    this.showGrandTotal = false;
    this.showSubtotalAndTaxTotal = false;
    this.showTaxableSubtotal = false;
    this.applyCouponsView = false;
    this.calculatedReceiptObj = new CalculatedReceipt;
  }

  calculateGrandTotal() {
    this.receiptService.calculateGrandTotal(this.receipt).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showGrandTotal = true;
    this.showTaxableSubtotal = false;
    this.showSubtotalAndTaxTotal = false;
    this.applyCouponsView = false;
  }

  calculateSubtotalAndTaxTotal() {
    this.receiptService.calculateSubtotalAndTaxTotal(this.receipt).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showGrandTotal = true;
    this.showSubtotalAndTaxTotal = true;
    this.showTaxableSubtotal = false;
    this.applyCouponsView = false;
  }

  calculateTaxableSubtotal() {
    this.receiptService.calculateTaxableSubtotal(this.receipt).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.showSubtotalAndTaxTotal = true;
    this.showTaxableSubtotal = true;
    this.applyCouponsView = false;
  }

  applyCoupons(){
    this.receiptService.applyCoupons(this.receipt, this.couponsList.coupons).subscribe((response) =>{
      this.calculatedReceiptObj = response;
    });
    this.applyCouponsView = true;
  }
}

export class Item {
  itemName: string = '';
  sku: number = 0;
  isTaxable: boolean = false;
  ownBrand: boolean = false;
  price: number = 0;
}

export class Coupon {
  couponName: string = '';
  appliedSku: number = 0;
  discountPrice: number = 0;
}

export class Receipt {
  items: Item[] = [];
}


export class CalculatedReceipt {
  grandTotal: number = 0;
  subtotalBeforeDiscounts: number = 0;
  taxTotalBeforeDiscounts: number = 0;
  taxTotalAfterDiscounts: number = 0;
  taxableSubtotalBeforeDiscounts: number = 0;
  discountTotal: number = 0;
  subtotalAfterDiscounts: number = 0;
  taxableSubtotalAfterDiscounts: number = 0;

}