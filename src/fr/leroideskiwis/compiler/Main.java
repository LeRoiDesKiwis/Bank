package fr.leroideskiwis.compiler;

import fr.leroideskiwis.compiler.bank.CentralBank;
import fr.leroideskiwis.compiler.bank.exceptions.TransactionException;
import fr.leroideskiwis.compiler.utils.CustomScanner;
import fr.leroideskiwis.compiler.utils.Utils;

public class Main {

    public static void main(String[] args) {
        CentralBank centralBank = new CentralBank();
        CustomScanner customScanner = new CustomScanner();

        centralBank.createBank("Crédit weebicol");
        centralBank.createBank("Crédit agricole");

        Utils.displaySeparator("kiwi");
        centralBank.createAccount(0, "LeRoiDesKiwis");
        Utils.displaySeparator("Guiwi");
        centralBank.createAccount(1, "Guiguim");

        boolean running = true;

        while(running) {

            String sender = customScanner.askString("Sender : ");
            String collector = customScanner.askString("Collector : ");
            int amount = customScanner.askInt("Amount : ", "Please give a number : ");

            try {
                centralBank.createTransaction(sender, collector, amount).valid();
            } catch (TransactionException e) {
                e.printStackTrace();
            }

            running = customScanner.askString("Continue ?").equals("y");
        }

    }
}
