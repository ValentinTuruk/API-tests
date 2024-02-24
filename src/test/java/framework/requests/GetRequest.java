package framework.requests;

public class GetRequest extends BaseRequest {
    public void request(String path) {
        response = reqSpec.get(path);
    }
}