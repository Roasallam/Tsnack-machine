package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.HashSet;

public class SnackMachine implements Machine {

   static int DEFAULT_QUANTITY = 10;
   Money moneyInside;
   Money moneyInTransaction;
   Money change;
   HashSet<Double> acceptedMoney;
   Snack chewingGums;
   Snack chips;
   Snack chocolate;

   public SnackMachine() {
      moneyInside = new Money(BigDecimal.valueOf(0));
      moneyInTransaction = new Money(BigDecimal.valueOf(0));
      change = new Money(BigDecimal.valueOf(0));
      acceptedMoney = setAcceptedMoney();
      chewingGums = new Snack(DEFAULT_QUANTITY, SnackType.CHEWING_GUM, BigDecimal.valueOf(0.5));
      chips = new Snack(DEFAULT_QUANTITY, SnackType.CHIPS, BigDecimal.valueOf(1.0));
      chocolate = new Snack(DEFAULT_QUANTITY, SnackType.CHOCOLATE, BigDecimal.valueOf(2.0));
   }

   private HashSet<Double> setAcceptedMoney () {
      HashSet<Double> acceptedMoney = new HashSet<>();

      for (MoneyUnit moneyUnit : MoneyUnit.values()) {
         acceptedMoney.add(moneyUnit.value);
      }
      return acceptedMoney;
   }

   public Money moneyInside () {
      return moneyInside;
   }

   public Money moneyInTransaction () {
      return moneyInTransaction;
   }

   Snack chewingGums () {
      return chewingGums;
   }

   Snack chips () {
      return chips;
   }

   Snack chocolates () {
      return chocolate;
   }

   public void insertMoney (Money money) {
      if ((money == null) || !(isAcceptedMoney(money)))
         throw new IllegalArgumentException("Unsupported money unit");

      moneyInTransaction = moneyInTransaction.add(money);
   }

   public Money buySnack (SnackType snackType) {
      if (snackType == null)
         throw new IllegalArgumentException("please choose a snack");

      Snack snack = chewingGums();
      switch (snackType) {
         case CHEWING_GUM: snack = chewingGums();
         break;
         case CHIPS: snack = chips();
         break;
         case CHOCOLATE: snack = chocolates();
      }

      boolean check = check(snack);
      if(check)
         buy(snack);

      return change;
   }

   void buy (Snack snack) {
      BigDecimal newValue = moneyInTransaction.getValue();
      moneyInside.setValue(newValue);
      change(snack);
      moneyInTransaction = new Money(BigDecimal.valueOf(0));
      snack.decreaseQuantity();
   }

   void change (Snack snack) {
      Money price = new Money(snack.getPrice());

      if (price.isLessThan(moneyInside))
         change.setValue(moneyInside.subtract(price).getValue());
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

      return money.isLessThan(moneyInTransaction) || money.isEqual(moneyInTransaction);
   }

   boolean isAvailable (Snack snack) {
      return snack.quantity() > 0;
   }
}
