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

  
 public calculateTotal() {
  return this.http.get<any>(this.url);
}
}
