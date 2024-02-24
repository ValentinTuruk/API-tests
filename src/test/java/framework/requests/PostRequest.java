package framework.requests;

public class PostRequest extends BaseRequest{
    public void request(String path) {
        response = reqSpec.post(path);
    }
    
    public void setBody(String payload){
        reqSpec.body(payload);
    }
}
