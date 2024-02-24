package framework.utils;

import org.testng.asserts.SoftAssert;

public class SoftAsserts {
    public static SoftAssert softAssert = new SoftAssert();
    
    public static void checkSoftAsserts() {
        softAssert.assertAll();
    }
    
    public static void cleanSoftAsserts() {
        softAssert = new SoftAssert();
    }
}
