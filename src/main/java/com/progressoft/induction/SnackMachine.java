package com.progressoft.induction;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * The {@code SnackMachine}
 * implements specified behaviors
 * in order to buy a snack,
 * it has it's own accepted money units
 * and contains different types of snacks.
 */
public class SnackMachine implements Machine {

   /**
    * Default quantity for each snack
    * available in the snack machine.
    */
   static int DEFAULT_QUANTITY = 10;

   /**
    * The amount of {@code Money} accumulated
    * inside the machine when the
    * buying process begins.
    */
   Money moneyInside;

   /**
    * The amount of {@code Money} accumulated
    * in the transaction until the
    * buying process begins.
    */
   Money moneyInTransaction;

   /**
    * The amount of {@code Money} left
    * from buying a specific snack.
    */
   Money change;

   /**
    * A set which contains
    * all money units supported
    * by {@code SnackMachine}.
    */
   HashSet<Double> acceptedMoney;

   /**
    * {@code Snack} of type
    * {@code SnackType.CHEWING_GUM}
    */
   Snack chewingGums;

   /**
    * {@code Snack} of type
    * {@code SnackType.CHIPS}
    */
   Snack chips;

   /**
    * {@code Snack} of type
    * {@code SnackType.CHOCOLATE}
    */
   Snack chocolate;

   /**
    * Constructs {@code SnackMachine}
    * instance and instantiate
    * moneyInside, moneyInTransaction, change
    * to {@code Money} with value of {@code BigDecimal}
    * with value of 0,
    * and sets accepted money units, for this {@code SnackMachine}
    * instance,
    * Instantiate {@code Snack} chewingGums,
    * chips, chocolate with their default quantities
    * and their prices.
    */
   public SnackMachine() {
      moneyInside = new Money(BigDecimal.valueOf(0));
      moneyInTransaction = new Money(BigDecimal.valueOf(0));
      change = new Money(BigDecimal.valueOf(0));
      acceptedMoney = setAcceptedMoney();
      chewingGums = new Snack(DEFAULT_QUANTITY, SnackType.CHEWING_GUM, BigDecimal.valueOf(0.5));
      chips = new Snack(DEFAULT_QUANTITY, SnackType.CHIPS, BigDecimal.valueOf(1.0));
      chocolate = new Snack(DEFAULT_QUANTITY, SnackType.CHOCOLATE, BigDecimal.valueOf(2.0));
   }

   /**
    * Prepare a set of accepted money units values
    * which supported by this {@code SnackMachine}
    * @return a set of accepted {@code MoneyUnits} values
    */
   private HashSet<Double> setAcceptedMoney () {
      HashSet<Double> acceptedMoney = new HashSet<>();

      for (MoneyUnit moneyUnit : MoneyUnit.values()) {
         acceptedMoney.add(moneyUnit.value);
      }
      return acceptedMoney;
   }

   /**
    * Returns the amount of money
    * inserted to the machine
    * i.e: returns the amount of money accumulated
    * when buying process starts.
    * @return {@code Money} inside the machine
    */
   public Money moneyInside () {
      return moneyInside;
   }

   /**
    * Returns {@code Money} accumulated
    * in a transaction.
    * @return {@code Money} accumulated in a
    * transaction
    */
   public Money moneyInTransaction () {
      return moneyInTransaction;
   }

   /**
    * Returns {@code Snack} of type
    * {@code SnackType} CHEWING_GUMS.
    * @return {@code Snack} of type
    * CHEWING_GUMS.
    */
   Snack chewingGums () {
      return chewingGums;
   }

   /**
    * Returns {@code Snack} of type
    * {@code SnackType} CHIPS.
    * @return {@code Snack} of type
    * CHIPS
    */
   Snack chips () {
      return chips;
   }

   /**
    * Returns {@code Snack} of type
    * {@code SnackType} CHOCOLATE.
    * @return {@code Snack} of type
    * CHOCOLATE
    */
   Snack chocolates () {
      return chocolate;
   }

   /**
    * Adds {@param money} to the accumulated
    * money in a transaction.
    * @param money money to insert
    */
   public void insertMoney (Money money) {
      if ((money == null) || !(isAcceptedMoney(money)))
         throw new IllegalArgumentException("Unsupported money unit");

      moneyInTransaction = moneyInTransaction.add(money);
   }

   /**
    * Performs buying process,
    * according to the snack type, this method
    * checks if that snack is available or not,
    * it also checks if money inside the machine is enough
    * to buy that snack,
    * if so it performs buying process and returns
    * the amount of change,
    * otherwise it {@throws IllegalStateException}.
    * @param snackType specified type of a {@code Snack}
    * @return {@code Money} the amount of change left
    * from buying that snack
    */
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

   /**
    * Sets the value of {@code Money}
    * moneyInside which is used to buy
    * a specific snack,
    * re instantiate {@code Money} moneyInTransaction
    * with new value of {@code BigDecimal} of value 0
    * decrease the quantity of the snack.
    * @param snack specified snack to buy
    */
   void buy (Snack snack) {
      BigDecimal newValue = moneyInTransaction.getValue();
      moneyInside.setValue(newValue);
      change(snack);
      moneyInTransaction = new Money(BigDecimal.valueOf(0));
      snack.decreaseQuantity();
   }

   /**
    * Calculates the amount of
    * {@code Money} change after buying
    * a specified snack.
    * @param snack specified snack to buy
    */
   void change (Snack snack) {
      Money price = new Money(snack.getPrice());

      if (price.isLessThan(moneyInside))
         change.setValue(moneyInside.subtract(price).getValue());
   }

   /**
    * Checks if the specified snack
    * is available,
    * and if {@code Money} moneyInside is
    * enough to buy it, returns true if so,
    * {@throws IllegalStateException} otherwise.
    * @param snack specified snack to buy
    * @return true if specified snack is available and
    * {@code Money} moneyInside is enough to buy it
    */
   boolean check (Snack snack) {
      if (!isAvailable(snack))
         throw new IllegalStateException("not available");

      if (!CanBuy(snack))
         throw new IllegalStateException("not enough money");
      return true;
   }

   /**
    * Checks if passed {@param money}
    * is supported by the machine or not.
    * @param money specified money to check if
    *              supported or not
    * @return true if money is supported by
    * the machine, false otherwise
    */
   public boolean isAcceptedMoney(Money money) {
      double value = money.getValue().abs().doubleValue();

      return acceptedMoney.contains(value);
   }

   /**
    * Checks if {@code Money} moneyInTransaction
    * is enough to buy a specified snack.
    * @param snack specified snack to buy
    * @return true if {@code Money} moneyInTransaction
    * is enough to buy a {@code Snack} snack, false otherwise
    */
   boolean CanBuy (Snack snack) {
      BigDecimal price = snack.getPrice();
      Money money = new Money(price);

      return money.isLessThan(moneyInTransaction) || money.isEqual(moneyInTransaction);
   }

   /**
    * Checks the quantity of
    * a specified snack.
    * @param snack specified snack to buy
    * @return true if there is an available quantity
    * of a {@code Snack} snack, false otherwise
    */
   boolean isAvailable (Snack snack) {
      return snack.quantity() > 0;
   }
}
