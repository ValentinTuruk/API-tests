package framework.utils;

import com.google.gson.JsonObject;

public class PayloadFactory {
    public static String getPayload(String[][] payload) {
        var jsonObject = new JsonObject();
        
        for (String[] entry : payload) {
            if (entry.length != 2) {
                throw new IllegalArgumentException("Payload entries must consist of key and value");
            }
            String key = entry[0];
            Object value = entry[1];
            jsonObject.addProperty(key, value.toString());
        }
        return jsonObject.toString();
    }
}
