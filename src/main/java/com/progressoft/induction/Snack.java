package com.progressoft.induction;

import java.math.BigDecimal;

public class Snack {

   int quantity;
   private SnackType snackType;
   private BigDecimal price;

   public Snack(int quantity, SnackType snackType, BigDecimal price) {
      this.quantity = quantity;
      this.snackType = snackType;
      this.price = price;
   }

   int quantity () {
      return quantity;
   }

   public void decreaseQuantity() {
      quantity = quantity -1;
   }

   public BigDecimal getPrice() {
      return price;
   }

   public void setPrice(BigDecimal price) {
      this.price = price;
   }
}
