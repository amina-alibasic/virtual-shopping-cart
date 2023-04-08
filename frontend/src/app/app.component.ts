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
  grandTotalObject: grandTotal = new grandTotal;

  constructor(private receiptService: ReceiptService) { }


  changeFormat(option: number) {
    this.formatOption = option;
  }

  calculateGrandTotal() {
    this.receiptService.calculateGrandTotal(this.cart).subscribe((response) =>{
      this.grandTotalObject = response;
    }
    );
  }
}

export class grandTotal {
  amount: number = 0;
}