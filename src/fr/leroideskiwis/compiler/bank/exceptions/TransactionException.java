package fr.leroideskiwis.compiler.bank.exceptions;

import fr.leroideskiwis.compiler.bank.Transaction;

import java.util.function.Supplier;

public class TransactionException extends Exception {

    public TransactionException(){
        this("Can't apply transaction ! Verify that you have enough money in your account or contact your bank for more informations.");
    }

    public TransactionException(String text){
        super(text);
    }

}
