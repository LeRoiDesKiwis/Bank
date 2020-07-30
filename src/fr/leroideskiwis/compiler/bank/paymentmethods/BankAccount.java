package fr.leroideskiwis.compiler.bank.paymentmethods;

import fr.leroideskiwis.compiler.bank.Transaction;

public class BankAccount implements PaymentMethod {

    private MasterCard masterCard;
    private final String name;
    private PaymentMethodIdentifier id;
    private final int bankId;
    private float amount;

    public BankAccount(String name, String iban, int bankId) {
        this.name = name;
        this.id = new PaymentMethodIdentifier(iban, PaymentMethodType.BANKACCOUNT);
        this.bankId = bankId;
        this.amount = 1000;
        System.out.println("Your iban : "+iban);
    }

    public void linkMastercard(MasterCard masterCard){
        this.masterCard = masterCard;
        masterCard.linkBankAccount(this);
    }

    @Override
    public void credit(float amount) {
        this.amount+=amount;
        System.out.println(String.format("%s -> +%s€", name, amount));
        displayAmount();
    }

    @Override
    public void debit(float amount) {
        this.amount-=amount;
        System.out.println(String.format("%s -> -%s€", name, amount));
        displayAmount();
    }

    @Override
    public boolean valid(float amount, Transaction.TransactionType type) {
        return type == Transaction.TransactionType.CREDIT;
    }

    public boolean hasAtLeast(float amount) {
        return this.amount >= amount;
    }

    public boolean isIban(PaymentMethodIdentifier iban){
        return this.id.isId(iban);
    }

    public boolean isMasterCard(PaymentMethodIdentifier cardNumber){
        return masterCard.isCardNumber(cardNumber);
    }

    public void displayAmount(){
        System.out.println(String.format("You have %s€", amount));
    }

    public MasterCard getMasterCard(){
        return masterCard;
    }
}
