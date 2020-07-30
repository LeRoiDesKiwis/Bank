package fr.leroideskiwis.compiler.utils;

import java.util.Optional;
import java.util.Random;

public class Utils {

    private static Random random = new Random();

    public static String generateRandomString(int length, int minAscii, int maxAscii){

        StringBuilder finalString = new StringBuilder();

        for(int i = 0; i < length; i++){
            char asciiCode = (char)randomInt(minAscii, maxAscii);
            finalString.append(asciiCode);

        }

        return finalString.toString();

    }

    public static String generateRandomString(int length){

        return generateRandomString(length, 65, 90);
    }

    public static int randomInt(int min, int max){
        return (random.nextInt(max-min))+min;
    }

    public static void displaySeparator(String text){
        final String SEPARATOR = "--------------";
        System.out.println();
        System.out.print(String.format("%s %s %s", SEPARATOR, text, SEPARATOR));
        System.out.println();
    }

    public static Optional<Integer> parseInt(String string){
        try{
            return Optional.of(Integer.parseInt(string));
        }catch(Exception exception){
            return Optional.empty();
        }
    }

}
