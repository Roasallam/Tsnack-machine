package com.progressoft.induction;

import java.math.BigDecimal;

/**
 * This {@code Money} defines
 * specific data for a money
 * and implements the behaviors
 * such as addition and subtraction
 * and comparison methods
 */
public class Money {

   /**
    * {@code Money} money with {@code BigDecimal}
    * of value 0
    */
   static Money ZERO = new Money(BigDecimal.valueOf(0));

   /**
    * {@code Money} money with {@code BigDecimal}
    * of value 0.25
    */
   static Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));

   /**
    * {@code Money} money with {@code BigDecimal}
    * of value 0.50
    */
   static Money HALF_DINAR = new Money(BigDecimal.valueOf(0.50));

   /**
    * {@code Money} money with {@code BigDecimal}
    * of value 1.0
    */
   static Money DINAR = new Money(BigDecimal.valueOf(1.0));

   /**
    * The {@code BigDecimal} value of {@code Money} money.
    */
   BigDecimal value;

   /**
    * Constructs {@code Money} instance
    * with specified {@code BigDecimal} value.
    * @param value value for money
    */
   Money(BigDecimal value) {
      if (value == null)
         throw new IllegalArgumentException("value can't be null");

      if (value.compareTo(BigDecimal.ZERO) < 0)
         throw new IllegalArgumentException("value can't be negative");

      this.value = value;
   }

   /**
    * Returns {@code BigDecimal} value of
    * this {@code Money}
    * @return value of money
    */
   BigDecimal getValue() {
      return value;
   }

   /**
    * Sets the {@code BigDecimal} value
    * with a new {@code BigDecimal} new value
    * @param value new value to set
    */
   public void setValue(BigDecimal value) {
      this.value = value;
   }

   /**
    * Addition operation for {@code Money} values
    * @param money money with specific {@code BigDecimal}
    *              value to add
    * @return new {@code Money} instance with
    * new value results from addition of values
    */
   Money add (Money money) {
      if (money == null)
         return this;

      BigDecimal value = this.getValue().add(money.getValue());

      return new Money(value);
   }

   /**
    * Subtraction operation for {@code Money} values.
    * @param money money with specific {@code BigDecimal}
    *              value to subtract
    * @return new {@code Money} instance with
    * new value results from subtraction of values
    */
   Money subtract (Money money) {
      if (money == null)
         return this;

      if (this.isLessThan(money))
         throw new IllegalArgumentException("can't be subtracted");

      BigDecimal value = this.getValue().subtract(money.getValue());

      return new Money(value);
   }

   /**
    * Compares if {@code Money} this is
    * less than {@param money}.
    * @param money money to compare with
    * @return true if {@code this} is less than
    * {@param money}, false otherwise
    */
   boolean isLessThan (Money money) {
      if (money == null)
         return false;

      BigDecimal value = money.getValue();

      return this.getValue().compareTo(value) < 0;
   }

   /**
    * Compares if {@code Money} this {@code BigDecimal}
    * value is equal to {@param money}
    * {@code BigDecimal} value
    * @param money money to compare with
    * @return true if {@code this} value is equal to
    * {@param money} value, false otherwise
    */
   boolean isEqual (Money money) {
      if (money == null)
         return false;

      BigDecimal value = money.getValue();

      return this.getValue().compareTo(value) == 0;
   }

   /**
    * Checks if a specified object of {@code Money}
    * is equal to another object of {@code Money}
    * by comparing the {@code BigDecimal} values
    * for each.
    * @param obj object to compare with
    * @return true if both objects has equal {@code BigDecimal}
    * values, false otherwise
    */
   @Override
   public boolean equals(Object obj) {
      if (obj == null) {
         return false;
      }

      if (!Money.class.isAssignableFrom(obj.getClass())) {
         return false;
      }
      final Money other = (Money) obj;
      return isEqual(other);
   }
}
