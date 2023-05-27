package utlis;

import io.cucumber.datatable.DataTable;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Utils {
    public static String stack, browser;
    public static Response clientTokenResp;
    static Properties prop = new Properties();

    public static void swaggerSchema(Response response, int statusCode, String component, String schema) throws IOException {
        Assert.assertEquals(response.statusCode(), statusCode, "\nx-request-id->" + response.getHeader("x-request-id") + "\nResponse Body is\n" + response.prettyPrint());
        if (Utils.swaggerValidationRequired()) {
            Response getswaggerValidationResp = getswaggerValidation(component, response, schema);
            Assert.assertEquals(getswaggerValidationResp.then().extract().jsonPath().get("validationStatus"), "Success", "\n[[CODE ISSUE]] Response Body :-\n" +
                    response.then().extract().response().asString()
                    + "\nIs not matching with swagger doc ." + "Error Returned is\n" +
                    getswaggerValidationResp.then().extract().jsonPath().get("reports").toString());
            System.out.print("[[INFO]] Swagger validation success for" + component + "\n");
        }
    }

    public static String getStackForSim() {
        if (StringUtils.isNotBlank(System.getProperty("stack"))) {
            return System.getProperty("testing");
        }
        if (stack == null) {
            stack = System.getProperty("stack", "testing").toLowerCase();
        }
        if ("testers".equalsIgnoreCase(stack)) {
            return "testsre";
        } else {
            return stack;
        }
    }

    public static String getStack() {
        if (stack == null) {
            stack = System.getProperty("stack", "test").toLowerCase();
        }
        return stack;
    }

    public static RequestSpecification testRequestSpecification() throws IOException {
        if ("no".equalsIgnoreCase(System.getProperty("proxy"))) {
            return new RequestSpecBuilder().setBaseUri(Utils.getPropertyData("environment url"))
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", getPropertyData("agent")).setUrlEncodingEnabled(false).setRelaxedHTTPSValidation()
                    .build();
        } else {
            return new RequestSpecBuilder().setBaseUri(Utils.getPropertyData("environment url"))
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", getPropertyData("agent")).setUrlEncodingEnabled(false).setRelaxedHTTPSValidation()
                    .build();
        }
    }

    public static RequestSpecification RequestSpecification() throws IOException {
        if ("no".equalsIgnoreCase(System.getProperty("proxy"))) {
            return new RequestSpecBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", getPropertyData("agent")).setUrlEncodingEnabled(false).setRelaxedHTTPSValidation()
                    .build();
        } else {
            return new RequestSpecBuilder().setProxy(getProxyHostName())
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("User-Agent", getPropertyData("agent")).setUrlEncodingEnabled(false).setRelaxedHTTPSValidation()
                    .build();
        }
    }

    public static String getPropertyData(String getData) throws IOException {
        String path;
        switch (getStack()) {
            case "test":
                path = new File("src/main/java/config/test.properties").getAbsolutePath();
                break;
            default:
                throw new IllegalStateException("test fail");
        }
        FileInputStream in = new FileInputStream(path);
        prop.load(in);
        Assert.assertNotNull(prop.getProperty(getData), "[[TEST SCRIPT ISSUE]] There is no key=" + getData + "provided in the property file,kindly check");
        return prop.getProperty(getData);
    }

    public static boolean swaggerValidationRequired() {
        return "yes".equalsIgnoreCase(System.getProperty("swagger.validation"));
    }

    public static Response getswaggerValidation(String component, Response response, String schema) throws IOException {
        return given(RequestSpecification()).pathParam("component", component).pathParam("schemaType", schema)
                .body(response.then().extract().response().asString())
                .post(getPropertyData("endPoint") + "/swagger/validate/{component}/{SchemaType")
                .then().assertThat().statusCode(200).extract().response();
    }


    public static String getProxyHostName() {
        return System.getProperty("proxy", "test").toLowerCase();
    }

    public static String getXrequestId(Response response) {
        return response.getHeader("x-request-id");
    }

    public static JSONArray getExpectedErrorArray(DataTable errorArrayAsTable, Response response) {
        JSONArray array = new JSONArray();
        for (Map<String, String> data : errorArrayAsTable.asMaps(String.class, String.class)) {
            JSONObject jsonobject = new JSONObject();
            String errorMessage = data.get("ErrorMessageArray").toString();
            jsonobject.put("code", data.get("ErrorCode").toString());
            jsonobject.put("message", errorMessage.contains("%s") ? String.format(errorMessage, getXrequestId(response)) : errorMessage);
            array.put(jsonobject);
        }
        return array;
    }


    public static JSONArray getActualErrorArray(Response response) {
        return new JSONArray(response.jsonPath().getList("errors"));
    }


    public static JSONObject parseJSONFile(String filename) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filename)));
        return new JSONObject(content);

    }

    public static String generateGuid() {
        return UUID.randomUUID().toString();
    }

    public static int getProxyPortNumber() {
        return Integer.parseInt(System.getProperty("proxy.port", "8080"));
    }

    public static RequestSpecification requestSpecification() throws IOException {
        return new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").setContentType(ContentType.JSON).build();

    }

public static String getBrowser() {
    if (browser == null) {
        browser = System.getProperty("browser", "chrome").toLowerCase();
    }
    return browser;
}
   /* @Given("client token is generated")
    public void clientTokenIsGenerated() {
        Awaitility
                .await()
                .atMost(30, TimeUnit.SECONDS)
                .until(()-> {
                        clientTokenResp = genrateToken());
        return clientTokenResp.getStatusCode()==200;
    });
*/

}