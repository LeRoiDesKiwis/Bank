package fr.leroideskiwis.compiler.bank.paymentmethods;

import fr.leroideskiwis.compiler.bank.Transaction;

public interface PaymentMethod {

    void credit(float amount);
    void debit(float amount);
    boolean valid(float amount, Transaction.TransactionType type);

}
