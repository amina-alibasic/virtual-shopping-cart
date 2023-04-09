import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { CalculatedReceipt, Coupon, Receipt } from 'src/app/app.component';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `http://localhost:8080/api`;
  }

  
  public calculateGrandTotal(receipt: Receipt) {
    return this.http.post<CalculatedReceipt>(this.url + '/calculate-grand-total', receipt);
  }

  public calculateSubtotalAndTaxTotal(receipt: Receipt) {
    return this.http.post<CalculatedReceipt>(this.url + '/calculate-subtotal-and-tax-total', receipt);
  }

  public calculateTaxableSubtotal(receipt: Receipt) {
    return this.http.post<CalculatedReceipt>(this.url + '/calculate-taxable-subtotal', receipt);
  }

  public applyCoupons(receipt: Receipt, couponList: Coupon[]) {
    return this.http.post<CalculatedReceipt>(this.url + '/apply-coupons', {receipt, couponList});
  }
}
