package fr.leroideskiwis.bank.bank;

import fr.leroideskiwis.bank.bank.exceptions.TransactionException;
import fr.leroideskiwis.bank.bank.paymentmethods.PaymentMethod;
import fr.leroideskiwis.bank.bank.paymentmethods.PaymentMethodIdentifier;
import fr.leroideskiwis.bank.bank.paymentmethods.PaymentMethodType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CentralBank {

    private List<Bank> banks = new ArrayList<>();
    private int nextId = 0;

    public void createBank(String name){
        banks.add(new Bank(nextId++, name));
    }

    public void createAccount(int bankId, String name){
        getBankByid(bankId).ifPresent(bank -> bank.createAccount(name));
    }

    private Optional<Bank> getBankByid(int id){
        return banks.stream().filter(bank -> bank.isId(id)).findAny();
    }

    private Optional<PaymentMethod> getMethodById(PaymentMethodIdentifier id){
        for(Bank bank : banks){
            Optional<PaymentMethod> paymentMethod = bank.getPaymentMethod(id);
            if(paymentMethod.isPresent()) return paymentMethod;
        }
        return Optional.empty();
    }

    public Transaction createTransaction(PaymentMethodIdentifier senderId, PaymentMethodIdentifier collectorId, float amount) throws TransactionException {
        PaymentMethod sender = getMethodById(senderId).orElseThrow(() -> new TransactionException("Sender not found !"));
        PaymentMethod collector = getMethodById(collectorId).orElseThrow(() -> new TransactionException("Collector not found !"));
        return new Transaction(sender, collector, amount);
    }

    public Transaction createTransaction(String senderId, String collectorId, int amount) throws TransactionException{

        return createTransaction(makeId(senderId), makeId(collectorId), amount);
    }

    public PaymentMethodIdentifier makeId(String methodId) throws TransactionException{
        Optional<PaymentMethodType> method = PaymentMethodType.getIdByPrefixe(methodId.substring(0, 2));

        if(!method.isPresent()) throw new TransactionException("The given id is invalid !");

        String id = methodId.substring(3);

        return new PaymentMethodIdentifier(id, method.get());
    }
}
