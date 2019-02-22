package com.progressoft.induction;

import java.math.BigDecimal;

/**
 * This {@code Snack} is
 * a DATA model which defines
 * specific data for a snack
 */
public class Snack {

   /**
    * The quantity of a snack.
    */
   int quantity;

   /**
    * The {@code SnackType} type of a snack.
    */
   private SnackType snackType;

   /**
    * The price of a snack.
    */
   private BigDecimal price;

   /**
    * Constructs a new instance of
    * {@code Snack} snack with it's price, quantity
    * and {@code SnackType} type.
    * @param quantity the quantity of this {@code Snack} snack
    * @param snackType the {@code SnackType} type of this {@code Snack} snack
    * @param price the price of this {@code Snack} snack
    */
   public Snack(int quantity, SnackType snackType, BigDecimal price) {
      this.quantity = quantity;
      this.snackType = snackType;
      this.price = price;
   }

   /**
    * Returns the quantity of this
    * {@code Snack} snack.
    * @return the quantity of the snack
    */
   int quantity () {
      return quantity;
   }

   /**
    * Decreases the amount of quantity
    * for a {@code Snack} snack.
    */
   public void decreaseQuantity() {
      quantity = quantity -1;
   }

   /**
    * Returns the price of
    * this {@code Snack} snack.
    * @return the price of the snack
    */
   public BigDecimal getPrice() {
      return price;
   }

   /**
    * Sets the price of
    * this {@code Snack} snack.
    * @param price the price of this {@code Snack}
    *              snack
    */
   public void setPrice(BigDecimal price) {
      this.price = price;
   }
}
