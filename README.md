# Virtual Shopping Cart

## Clone the repository
In your terminal of choice, run:
```
git clone https://github.com/amina-alibasic/virtual-shopping-cart.git
```
## Backend
### Requires Java 8 (jdk 1.8.0_361) installation & Apache Maven 3.6.3.
Enter directory `backend` of this repo and run:

```
mvn spring-boot:run -Dserverport=8080
```
## Frontend
### Requires Angular CLI (my version is 15.2.4), Angular Material, `npm` and NodeJS.
In new terminal window, enter directory `frontend` of this repo and run:
```
npm install
ng serve --port 4200
```
# Shopping Cart Features
Develop a Receipt API that accepts a `cart.json` file as input and returns a calculated receipt in JSON format.<br/>
(Path: `virtual-shopping-cart\frontend\src\assets\cart.json`)<br/>
Design a cohesive API that integrates the following features.

## Feature 1
Calculate the grand total of a given shopping cart.<br/>
All items are single quantities, and all prices are in USD.<br/>
Output the following: Grand total.

## Feature 2
Calculate the subtotal and tax total of a given shopping cart.<br/>
The tax rate is 0.0825 (8.25%).<br/>
Output the following: Subtotal, Tax total, and Grand total.

## Feature 3
Some items are taxable, and some are not.<br/>
If "isTaxable" is true, calculate tax for that item, and if false, skip the tax calculation.<br/>
Output the following: Subtotal, Taxable subtotal, Tax total, and Grand total.

## Feature 4
Coupons discount an item's price before tax is calculated.<br/>
Coupons are automatically applied to a shopping cart if the item is present.<br/>
Use the `coupons.json` as a reference list.<br/> 
(Path: `virtual-shopping-cart\frontend\src\assets\coupons.json`)<br/>
The final price of an item cannot be negative.<br/>
Treat the "discountPrice" as a subtraction. A discount of $0.75 applied to an item with a price of $1.00 will have a final price of ($1.00 - $0.75) = $0.25.
<br/>
Output the following: <br/>
Subtotal before discounts, Discount total, Subtotal after discounts, Taxable subtotal after discounts, Tax total, and Grand total.
