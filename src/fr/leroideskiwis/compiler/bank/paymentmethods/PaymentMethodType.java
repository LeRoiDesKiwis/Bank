package fr.leroideskiwis.compiler.bank.paymentmethods;

import java.util.Optional;

public enum PaymentMethodType {

    MASTERCARD, BANKACCOUNT;

    public static Optional<PaymentMethodType> getIdByPrefixe(String prefixe){
        prefixe = prefixe.toUpperCase();
        for(PaymentMethodType paymentMethodType : values()){
            if(paymentMethodType.toString().substring(0,2).equals(prefixe)) return Optional.of(paymentMethodType);
        }
        return Optional.empty();
    }

}
