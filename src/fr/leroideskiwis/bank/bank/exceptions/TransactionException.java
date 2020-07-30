package fr.leroideskiwis.bank.bank.exceptions;

public class TransactionException extends Exception {

    public TransactionException(){
        this("Can't apply transaction ! Verify that you have enough money in your account or contact your bank for more informations.");
    }

    public TransactionException(String text){
        super(text);
    }

}
