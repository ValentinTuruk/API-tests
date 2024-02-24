package typicode.tests;

import framework.Setup;
import framework.helpers.CommonFunctions;
import framework.requests.GetRequest;
import framework.requests.PostRequest;
import framework.utils.PayloadFactory;
import org.testng.annotations.Test;
import typicode.responseobjects.UserFive;

import static framework.utils.PropertiesReader.getConfigProperty;

public class APITests extends Setup {
    
    @Test
    public void getAllPosts() {
        var get = new GetRequest();
        get.request(getConfigProperty("posts.url"));
        get.verifyStatusCode(200);
        get.verifyResponseDataOrder("id", "ASC");
    }
    
    @Test
    public void getDefinedPost() {
        var get = new GetRequest();
        var postNumber = 99;
        get.request(getConfigProperty("posts.url") + postNumber);
        get.verifyStatusCode(200);
        get.verifyResponseBodyValue("userId", 10);
        get.verifyResponseBodyValue("id", 99);
        get.verifyResponseValueNotEmpty("title");
        get.verifyResponseValueNotEmpty("body");
    }
    
    @Test
    public void getNonExistentItem() {
        var get = new GetRequest();
        var postNumber = 150;
        get.request(getConfigProperty("posts.url") + postNumber);
        get.verifyStatusCode(404);
        get.verifyResponseBodyEmptyCollection();
    }
    
    @Test
    public void postItem() {
        String title = CommonFunctions.generateRandomString(10);
        String body = CommonFunctions.generateRandomString(20);
        String userId = "1";
        
        String[][] dataToPost = {
                {"userId", userId},
                {"title", title},
                {"body", body}
        };
        
        var payload = PayloadFactory.getPayload(dataToPost);
        
        var post = new PostRequest();
        post.setHeader("Content-Type", "application/json");
        post.setBody(payload);
        post.request(getConfigProperty("posts.url"));
        
        post.verifyStatusCode(201);
        post.verifyResponseHasProperty("id");
        post.verifyResponseBodyValue("userId", userId);
        post.verifyResponseBodyValue("title", title);
        post.verifyResponseBodyValue("body", body);
    }
    
    @Test
    public void getUser() {
        var userNumber = 5;
        var get = new GetRequest();
        get.request(getConfigProperty("users.url"));
        get.verifyStatusCode(200);
        get.verifyContentType("application/json");
        
        UserFive userFive = new UserFive();
        get.verifyResponseBySample(String.format("find { it.id == %d }", userNumber), userFive);
    }
    
    @Test
    public void getDefinedUser() {
        var userNumber = 5;
        var get = new GetRequest();
        get.request(getConfigProperty("users.url") + userNumber);
        get.verifyStatusCode(200);
        
        UserFive userFive = new UserFive();
        get.verifyResponseBySample("", userFive);
    }
}
