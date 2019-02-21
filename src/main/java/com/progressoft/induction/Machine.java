package com.progressoft.induction;

public interface Machine {

   Money moneyInside ();

   void insertMoney (Money money);

   Money moneyInTransaction ();

   Money buySnack (SnackType snackType);

   boolean isAcceptedMoney (Money money);
}
