import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `http://localhost:8080/api`;
  }

  
  public calculateGrandTotal(cartJson: any) {
    return this.http.post<any>(this.url + '/calculate-grand-total', cartJson);
  }

  public calculateSubtotalAndTaxTotal(cartJson: any) {
    return this.http.post<any>(this.url + '/calculate-subtotal-and-tax-total', cartJson);
  }

  public calculateTaxableSubtotal(cartJson: any) {
    return this.http.post<any>(this.url + '/calculate-taxable-subtotal', cartJson);
  }
}
