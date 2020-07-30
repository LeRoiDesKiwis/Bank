package fr.leroideskiwis.compiler.bank;

import fr.leroideskiwis.compiler.bank.paymentmethods.*;
import fr.leroideskiwis.compiler.utils.CustomScanner;
import fr.leroideskiwis.compiler.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {

    private List<BankAccount> bankAccounts = new ArrayList<>();
    private int id;
    private String name;

    protected Bank(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void createAccount(String name){
        String iban = "FR"+Utils.generateRandomString(23, 49, 57);
        BankAccount account = new BankAccount(name, iban, id);
        CustomScanner scanner = new CustomScanner();

        account.linkMastercard(createMasterCard());
        bankAccounts.add(account);
    }

    public MasterCard createMasterCard(){
        CustomScanner customScanner = new CustomScanner();
        //String code = customScanner.askCode("Code pin : ", "Retype your code pin : ", "Error, please retry : ");
        String cardNumber = Utils.generateRandomString(16, 49, 57);
        return new MasterCard(cardNumber);

    }

    public Optional<PaymentMethod> getPaymentMethod(PaymentMethodIdentifier id){
        if(id.isType(PaymentMethodType.BANKACCOUNT)) return bankAccounts.stream()
                .filter(bankAccount -> bankAccount.isIban(id))
                .map(bankAccount -> (PaymentMethod)bankAccount)
                .findAny();
        if(id.isType(PaymentMethodType.MASTERCARD)) return bankAccounts
                .stream()
                .filter(bankAccount -> bankAccount.isMasterCard(id))
                .map(bankAccount -> (PaymentMethod)bankAccount.getMasterCard())
                .findAny();
        return Optional.empty();
    }

    public boolean isId(int id) {
        return this.id==id;
    }
}
