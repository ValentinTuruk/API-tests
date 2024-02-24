package framework.utils;

import org.testng.Assert;

import java.util.List;

public class ResponseBodyParser {
    public static void orderVerification(List<Integer> valuesList, String orderType){
        for (int i = 1; i < valuesList.size(); i++) {
            int previousId = valuesList.get(i - 1);
            int currentId = valuesList.get(i);
            switch (orderType){
                case "asc" -> Assert.assertTrue(currentId >= previousId, "Ids are NOT in ASC order");
                case "desc" -> Assert.assertTrue(currentId <= previousId, "Ids are NOT in DESC order");
                default -> Assert.fail("orderType is NOT defined");
            }
        }
    }
}
