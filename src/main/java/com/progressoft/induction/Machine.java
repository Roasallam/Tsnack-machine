package com.progressoft.induction;

/**
 * The {@code Machine} defines
 * the behaviors for a machine
 */
public interface Machine {

   /**
    * Returns the amount of money
    * inserted to the machine
    * i.e: returns the amount of money accumulated
    * when buying process starts.
    * @return {@code Money} inside the machine
    */
   Money moneyInside ();

   /**
    * Adds {@param money} to the accumulated
    * money in a transaction.
    * @param money money to insert
    */
   void insertMoney (Money money);

   /**
    * Returns {@code Money} accumulated
    * in a transaction.
    * @return {@code Money} accumulated in a
    * transaction
    */
   Money moneyInTransaction ();

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
   Money buySnack (SnackType snackType);

   /**
    * Checks if passed {@param money}
    * is supported by the machine or not.
    * @param money specified money to check if
    *              supported or not
    * @return true if money is supported by
    * the machine, false otherwise
    */
   boolean isAcceptedMoney (Money money);
}
