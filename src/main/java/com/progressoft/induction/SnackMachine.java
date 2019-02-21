package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.HashSet;

public class SnackMachine implements Machine {

   static int DEFAULT_QUANTITY = 10;
   static Money MONEY_INSIDE = Money.ZERO;
   static Money MONEY_IN_TRANSACTION = Money.ZERO;
   static Money CHANGE = Money.ZERO;
   static HashSet<Double> acceptedMoney = setAcceptedMoney();
   static Snack chewingGums = new Snack(DEFAULT_QUANTITY, SnackType.CHEWING_GUM, BigDecimal.valueOf(0.5));
   static Snack chips = new Snack(DEFAULT_QUANTITY, SnackType.CHIPS, BigDecimal.valueOf(1.0));
   static Snack chocolate = new Snack(DEFAULT_QUANTITY, SnackType.CHOCOLATE, BigDecimal.valueOf(2.0));

   private static HashSet<Double> setAcceptedMoney () {
      HashSet<Double> acceptedMoney = new HashSet<>();

      for (MoneyUnit moneyUnit : MoneyUnit.values()) {
         acceptedMoney.add(moneyUnit.value);
      }
      return acceptedMoney;
   }

   public Money moneyInside () {
      return MONEY_INSIDE;
   }

   public void insertMoney (Money money) {
      if ((money == null) || !(isAcceptedMoney(money)))
         throw new IllegalArgumentException("Unsupported money unit");

      MONEY_IN_TRANSACTION = MONEY_IN_TRANSACTION.add(money);
   }

   public Money moneyInTransaction () {
      return MONEY_IN_TRANSACTION;
   }

   public Money buySnack (SnackType snackType) {
      if (snackType == null)
         throw new IllegalArgumentException("please choose a snack");

      boolean check = false;

      switch (snackType) {
         case CHEWING_GUM: {
            check = check(chewingGums);
            if(check)
               chewingGums();
         }
         case CHIPS: {
            check = check(chips);
            if (check)
               chips();
         }
         case CHOCOLATE: {
            check = check(chocolate);
            if (check)
               chocolates();
         }
      }
      return CHANGE;
   }

   Snack chewingGums () {
      buy(chewingGums);
      chewingGums.decreaseQuantity();
      return chewingGums;
   }

   Snack chips () {
      buy(chips);
      chips.decreaseQuantity();
      return chips;
   }

   Snack chocolates () {
      buy(chocolate);
      chocolate.decreaseQuantity();
      return chocolate;
   }

   void buy (Snack snack) {
      BigDecimal newValue = MONEY_IN_TRANSACTION.getValue();
      MONEY_INSIDE.setValue(newValue);
      change(snack);
      MONEY_IN_TRANSACTION = Money.ZERO;
   }

   void change (Snack snack) {
      Money price = new Money(snack.getPrice());

      if (price.isLessThan(MONEY_INSIDE))
         CHANGE = MONEY_INSIDE.subtract(price);
   }

   boolean check (Snack snack) {
      if (!isAvailable(snack))
         throw new IllegalStateException("not available");

      if (!CanBuy(snack))
         throw new IllegalStateException("not enough money");
      return true;
   }

   public boolean isAcceptedMoney(Money money) {
      double value = money.getValue().abs().doubleValue();

      return acceptedMoney.contains(value);
   }

   boolean CanBuy (Snack snack) {
      BigDecimal price = snack.getPrice();
      Money money = new Money(price);

      return !(MONEY_IN_TRANSACTION.isLessThan(money) || !(MONEY_IN_TRANSACTION.isEqual(money)));
   }

   boolean isAvailable (Snack snack) {
      return snack.quantity() > 0;
   }
}
