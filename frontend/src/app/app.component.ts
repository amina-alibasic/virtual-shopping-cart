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
  formatOption: number = 0; // 0 - JSON, 1 - amt only
  totalAmount: totalOfCart = new totalOfCart;

  constructor(private receiptService: ReceiptService) { }


  changeFormat(option: number) {
    this.formatOption = option;
  }

  calculate() {
    this.receiptService.calculateTotal(this.cart).subscribe((response) =>{
      this.totalAmount = response;
    }
    );
  }
}

export class totalOfCart {
  total: number = 0;
}