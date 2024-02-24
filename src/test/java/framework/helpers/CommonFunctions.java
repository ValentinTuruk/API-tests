package framework.helpers;

import java.util.Random;

public class CommonFunctions {
    public static String generateRandomString(int stringLength) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890 ";
        StringBuilder randomText = new StringBuilder(stringLength);
        var random = new Random();
        
        for (int i = 0; i < stringLength; i++) {
            int index = random.nextInt(chars.length());
            randomText.append(chars.charAt(index));
        }
        return randomText.toString();
    }
}
