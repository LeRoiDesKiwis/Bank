package fr.leroideskiwis.bank.bank.paymentmethods;

import fr.leroideskiwis.bank.bank.Transaction;

public interface PaymentMethod {

    void credit(float amount);
    void debit(float amount);
    boolean valid(float amount, Transaction.TransactionType type);

}
