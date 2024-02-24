package framework;

import framework.utils.SoftAsserts;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static framework.utils.PropertiesReader.getConfigProperty;
import static io.restassured.RestAssured.baseURI;

public class Setup {
    
    @BeforeTest
    protected void start() {
        baseURI = getConfigProperty("base.url");
        SoftAsserts.cleanSoftAsserts();
    }
    
    @AfterTest
    protected void finish() {
        SoftAsserts.checkSoftAsserts();
    }
}
