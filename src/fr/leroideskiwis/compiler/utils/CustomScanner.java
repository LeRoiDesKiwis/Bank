package fr.leroideskiwis.compiler.utils;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

public class CustomScanner {

    private Scanner scanner;

    public CustomScanner(InputStream inputStream){
        this.scanner = new Scanner(inputStream);
    }

    public CustomScanner(){
        this(System.in);
    }

    public String askString(String ask){
        System.out.print(ask);
        String response = scanner.next();
        System.out.println();
        return response;
    }

    public String askCode(String ask, String reAsk, String retry){
        String code = askString(ask);
        String retapeCode = askString(reAsk);
        while(!code.equals(retapeCode)){
            code = askString(retry);
            retapeCode = askString(reAsk);
        }
        return code;
    }

    public int askInt(String ask, String error) {
        Optional<Integer> integer = Utils.parseInt(askString(ask));

        while(!integer.isPresent()){
            integer = Utils.parseInt(askString(error));
        }
        return integer.get();
    }
}
