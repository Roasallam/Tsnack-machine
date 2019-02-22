package com.progressoft.induction;

/**
 * This {@code MoneyUnit}
 * defines money units available
 * for a snack machine.
 * The {@code MoneyUnit} has a
 * {@code value} to define the unit value.
 */
public enum MoneyUnit {
   QUARTER_DINAR(0.25),
   HALF_DINAR(0.5),
   DINAR(1.0),
   JD5(5.0),
   JD10(10.0);


   /**
    * The value for a money unit.
    */
   double value;

   /**
    * constructs {@code MoneyUnit}
    * with specified value
    * @param value the value for the money unit
    */
   MoneyUnit(double value) {
      this.value = value;
   }
}
