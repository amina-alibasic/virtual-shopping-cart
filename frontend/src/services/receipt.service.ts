import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  private url: string;

  constructor(private http: HttpClient) {
    this.url = `http://localhost:8080/api/calculate-total`;
  }

  
 public calculateTotal(cartJson: any) {
  const headers = {
    'Content-Type' : 'application/json',
    'Accept': 'application/json',
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "GET, POST, PUT, DELETE, PATCH, OPTIONS",
    "Access-Control-Allow-Headers": "X-Requested-With, content-type, Authorization"
  };
  const body = cartJson;

  return this.http.post<any>(this.url,body, { headers });
}
}
