package fr.leroideskiwis.compiler.bank.paymentmethods;

import fr.leroideskiwis.compiler.bank.Transaction;
import fr.leroideskiwis.compiler.utils.CustomScanner;

public class MasterCard implements PaymentMethod {

    private String code;
    private BankAccount bankAccount;
    private final PaymentMethodIdentifier cardNumber;
    private int tries = 5;

    public MasterCard(String cardNumber) {
        this.code = "1234";
        this.cardNumber = new PaymentMethodIdentifier(cardNumber, PaymentMethodType.MASTERCARD);
        System.out.println("Your card number : "+cardNumber);
    }

    public void linkBankAccount(BankAccount bankAccount){
        this.bankAccount = bankAccount;
    }

    @Override
    public void credit(float amount) {
        bankAccount.credit(amount);
    }

    @Override
    public void debit(float amount) {
        bankAccount.debit(amount);
    }

    @Override
    public boolean valid(float amount, Transaction.TransactionType type) {
        if(type == Transaction.TransactionType.CREDIT) return true;
        if(!bankAccount.hasAtLeast(amount)) return false;

        CustomScanner customScanner = new CustomScanner();
        String pin = !isBlocked() ? customScanner.askString("Code pin required : ") : "";

        while(!this.code.equals(pin) && !isBlocked()){
            tries--;
            pin = customScanner.askString(String.format("Code pin denied, %s tries left : ", tries));
        }

        if(isBlocked()) {
            System.out.println("Sorry, your card is blocked. Please contact your bank for more informations.");
            return false;
        }

        return true;
    }

    private boolean isBlocked(){
        return tries <= 0;
    }

    public boolean isCardNumber(PaymentMethodIdentifier cardNumber){
        return this.cardNumber.isId(cardNumber);
    }

}
