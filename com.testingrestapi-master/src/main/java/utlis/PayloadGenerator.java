package utlis;

import org.json.JSONObject;

public class PayloadGenerator {
String payload;

    public JSONObject testRequestBody(String test)
    {
        JSONObject testReqBody =  new JSONObject();
        testReqBody.put("test",test);
        return testReqBody;
    }
    public static JSONObject token()
    {
        JSONObject requestParams =  new JSONObject();
        requestParams.put("password", "password123");
        requestParams.put("username", "admin");
        return requestParams;
    }
    public static JSONObject userCreate()
    {
        JSONObject userCreate =  new JSONObject();
        userCreate.put("password", "Test@@123");
        userCreate.put("userName", Utils.generateGuid());
        return userCreate;

    }
}
