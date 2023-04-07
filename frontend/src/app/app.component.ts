import { Component, OnInit } from '@angular/core';
import * as jsonData from '../assets/cart.json'
import { ReceiptService } from 'src/services/receipt.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'frontend';
  cart = jsonData;

  constructor(private receiptService: ReceiptService) { }
  private URL = '../assets/cart.json';

  ngOnInit() {
    console.log(this.cart.items);
    // this.receiptService.
  }
}
