package com.progressoft.induction;

public enum MoneyUnit {
   QUARTER_DINAR(0.25),
   HALF_DINAR(0.5),
   DINAR(1.0),
   JD5(5.0),
   JD10(10.0);

   double value;

   MoneyUnit(double value) {
      this.value = value;
   }
}
