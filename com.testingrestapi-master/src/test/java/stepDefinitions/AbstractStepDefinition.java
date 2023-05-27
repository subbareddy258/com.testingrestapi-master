package stepDefinitions;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;

public class AbstractStepDefinition {
    public static final  String COMPONENT_USER= "userget";
    public static final  String COMPONENT_BOOKING= "booking";

    //Request intilizaation to call
    static  String clientToken1,clientToken2,testId,userId,bookingRequestBody;
    //Response
    public static Response commonResponse;
    public static Response getListOfUsersResp,addUsersResp,getUsersResp,createBookingResp,bookingResp;
    static Response getswaggerValidationResp,genrateTokenResp;
    //to read values we will use JSONPATH
    static JsonPath orderResponse,getUsersRespJson;
    static WebDriver driver;
}
