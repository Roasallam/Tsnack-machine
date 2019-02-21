package com.progressoft.induction;

import java.math.BigDecimal;

public class Money {

   static Money ZERO = new Money(BigDecimal.valueOf(0));
   static Money QUARTER_DINAR = new Money(BigDecimal.valueOf(0.25));
   static Money HALF_DINAR = new Money(BigDecimal.valueOf(0.50));
   static Money DINAR = new Money(BigDecimal.valueOf(1.0));

   BigDecimal value;

   Money(BigDecimal value) {
      if (value == null)
         throw new IllegalArgumentException("value can't be null");

      if (value.compareTo(BigDecimal.ZERO) < 0)
         throw new IllegalArgumentException("value can't be negative");

      this.value = value;
   }

   BigDecimal getValue() {
      return value;
   }

   public void setValue(BigDecimal value) {
      this.value = value;
   }

   Money add (Money money) {
      if (money == null)
         return this;

      BigDecimal value = this.getValue().add(money.getValue());

      return new Money(value);
   }

   Money subtract (Money money) {
      if (money == null)
         return this;

      if (this.isLessThan(money))
         throw new IllegalArgumentException("can't be subtracted");

      BigDecimal value = this.getValue().subtract(money.getValue());

      return new Money(value);
   }

   boolean isLessThan (Money money) {
      if (money == null)
         return false;

      BigDecimal value = money.getValue();

      return this.getValue().compareTo(value) < 0;
   }

   boolean isEqual (Money money) {
      if (money == null)
         return false;

      BigDecimal value = money.getValue();

      return this.getValue().compareTo(value) == 0;
   }

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
