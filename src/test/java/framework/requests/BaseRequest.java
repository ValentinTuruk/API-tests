package framework.requests;

import com.google.gson.Gson;
import framework.utils.ResponseBodyParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BaseRequest {
    RequestSpecification reqSpec;
    Response response;
    List<Integer> valuesList;
    
    public BaseRequest() {
        this.reqSpec = given();
    }
    
    private void getResponseValues(String key) {
        valuesList = response.path(key);
    }
    
    public void setHeader(String[][] headers) {
        for (String[] header : headers)
            for (int col = 0; col < header.length; col++)
                reqSpec.header(header[col], header[col + 1]);
    }
    
    public void setHeader(String head, String val) {
        reqSpec.header(head, val);
    }
    
    public void verifyStatusCode(int statusCode) {
//        response.then().onFailMessage(String.format("Status code should be %d", statusCode)).statusCode(statusCode);
        response.then().statusCode(statusCode);
    }
    
    public void verifyResponseDataOrder(String key, String orderType) {
        getResponseValues(key);
        ResponseBodyParser.orderVerification(valuesList, orderType.toLowerCase());
    }
    
    public void verifyResponseBodyValue(String key, String value) {
        response.then().body(key, equalTo(value));
    }
    
    public void verifyResponseBodyValue(String key, int value) {
        response.then().body(key, equalTo(value));
    }
    
    public void verifyResponseValueNotEmpty(String key) {
        response.then().body(key, not(empty()));
    }
    
    public void verifyResponseHasProperty(String property) {
        response.then().body(property, notNullValue());
    }
    
    public void verifyResponseBodyEmptyCollection() {
        response.then().body(is("{}"));
    }
    
    public void verifyContentType(String contentType) {
        response.then().contentType(contentType);
    }
    
    public void verifyResponseBySample(String objectFilter, Object sample) {
        Gson gson = new Gson();
        String expectedJson = gson.toJson(sample);
        
        JsonPath jsonPath = response.jsonPath();
        Object userObject = jsonPath.get(objectFilter);
        String actualJson = gson.toJson(userObject);
        
        Assert.assertEquals(actualJson, expectedJson);
    }
}
