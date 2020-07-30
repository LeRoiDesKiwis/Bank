package fr.leroideskiwis.compiler.bank.paymentmethods;

import java.util.Objects;

public class PaymentMethodIdentifier {

    private String id;
    private PaymentMethodType type;

    public PaymentMethodIdentifier(String id, PaymentMethodType type) {
        this.id = id;
        this.type = type;
    }

    public boolean isType(PaymentMethodType type){
        return this.type == type;
    }

    public boolean isId(PaymentMethodIdentifier id){
        return this.id.equals(id.id);
    }
}
