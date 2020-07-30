package fr.leroideskiwis.bank.bank;

import fr.leroideskiwis.bank.bank.exceptions.TransactionException;
import fr.leroideskiwis.bank.bank.paymentmethods.PaymentMethod;

public class Transaction {

    private final PaymentMethod sender;
    private final PaymentMethod collector;
    public final float amount;

    public Transaction(PaymentMethod t, PaymentMethod r, float amount){
        this.sender = t;
        this.collector = r;
        this.amount = amount;
    }

    public boolean isValid(){
        return sender.valid(amount, TransactionType.DEBIT) && collector.valid(amount, TransactionType.CREDIT);
    }

    public void valid() throws TransactionException {
        if(!isValid()) throw new TransactionException();
        sender.debit(amount);
        collector.credit(amount);
    }

    public enum TransactionType{
        DEBIT, CREDIT;
    }

}
