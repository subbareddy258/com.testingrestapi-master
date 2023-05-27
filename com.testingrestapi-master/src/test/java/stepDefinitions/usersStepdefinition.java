package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import stepDefinationsImpl.BookingsImpl;
import stepDefinationsImpl.ReqresImpl;
import utlis.PayloadGenerator;
import utlis.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.testng.AssertJUnit.fail;
import static utlis.Utils.getXrequestId;
import static utlis.Utils.requestSpecification;

public class usersStepdefinition extends AbstractStepDefinition {
    ReqresImpl ReqresImpl = new ReqresImpl();
    BookingsImpl BookingsImpl = new BookingsImpl();
    SoftAssert softAssert = new SoftAssert();

    @When("client retrieve the get users with {string}")
    public void test(String scenario) {
        Map<String, String> queryParams = new HashMap<String, String>();
        switch (scenario) {
            case "valid":
                queryParams.put("page", "2");
                queryParams.put("per_page", "2");
                break;
        }
        ReqresImpl.getUsers(queryParams);
    }

    @Then("the status code of {string} Response is {int} and Response Body matches with {string} swagger schema")
    public void swaggerSchema(String action, int statusCode, String schema) throws IOException {
        Response response = null;
        String component = null;
        switch (action) {
            case "getusers":
                response = getUsersResp;
                component = COMPONENT_USER;
                break;
            case "createbooking":
                response = bookingResp;
                component = COMPONENT_BOOKING;
                break;
            default:
                fail("not founr");
        }
        Assert.assertEquals(response.statusCode(), statusCode, "\nx-request-id->" + response.getHeader("x-request-id") + "\nResponse Body is\n" + response.prettyPrint());
        if (component != null && Utils.swaggerValidationRequired()) {
            getswaggerValidationResp = Utils.getswaggerValidation(component, response, schema);
            Assert.assertEquals(getswaggerValidationResp.then().extract().jsonPath().get("validationStatus"), "Success", "\n[[CODE ISSUE]] Response Body :-\n" +
                    response.then().extract().response().asString()
                    + "\nIs not matching with swagger doc ." + "Error Returned is\n" +
                    getswaggerValidationResp.then().extract().jsonPath().get("reports").toString());
            System.out.print("[[INFO]] Swagger validation success for" + component + "\n");
        }
    }


    @Given("User is on Home page")
    public void userOnHomePage() {
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.demoqa.com");
    }

    @And("The Error code and Error Response while user {string} Displayed Correctly")
    public void theErrorCodeAndErrorResponseWhileUserGetusersDisplayedCorrectly(String useraction, DataTable errorArrayAsDataTable) {
        Response response = null;
        int i = 0;
        switch (useraction.toLowerCase()) {
            case "getusers":
                response = getUsersResp;
                break;
            default:
                fail("not founrd");

        }
        String expectedmessage, actualmessage;
        if (!Objects.isNull(errorArrayAsDataTable.asMaps(String.class, String.class).get(0).get("ErrorMessage"))
                && "Invalid Authz token".equalsIgnoreCase(errorArrayAsDataTable.asMaps(String.class, String.class).get(0).get("ErrorMessage"))) {
            Assert.assertEquals(response.then().extract().jsonPath().getString("error"), "Invalid Auth token");

        } else {
            Assert.assertEquals(response.then().extract().jsonPath().getString("errors.size()"), errorArrayAsDataTable.asMaps(String.class, String.class).size());
            if (!Objects.isNull(errorArrayAsDataTable.asMaps(String.class, String.class).get(0).get("ErrorMessageArray"))) {
                JSONAssert.assertEquals(Utils.getExpectedErrorArray(errorArrayAsDataTable, response), Utils.getActualErrorArray(response), JSONCompareMode.LENIENT);

            } else {
                for (Map<String, String> data : errorArrayAsDataTable.asMaps(String.class, String.class)) {
                    expectedmessage = data.get("ErrorMessage");
                    if (expectedmessage.contains("%s")) {
                        expectedmessage = getFormattedErrorMsg(data.get("ErrorMessage").toString(), response);
                    }
                    actualmessage = response.then().extract().jsonPath().getString("errors[" + i + "].message");
                    Assert.assertEquals(response.then().extract().jsonPath().getString("errors[" + i + "].code"), data.get("ErrorCode"));
                    Assert.assertTrue(actualmessage.contains(expectedmessage), "Actual Message:-" + actualmessage + " doesn't contain expected message:-" + expectedmessage);
                    i++;
                }

            }

        }
    }

    private String getFormattedErrorMsg(String errormessage, Response response) {
        if (errormessage.contains("id")) {
            errormessage = String.format(errormessage, userId, getXrequestId(response));
        } else {
            errormessage = String.format(errormessage, getXrequestId(response));
        }
        return errormessage;
    }


    @And("Verifies get users displayed properly")
    public void verifiesGetUsersDisplayedProperly() {
        getUsersRespJson = new JsonPath(getUsersResp.asString());

        softAssert.assertEquals(getUsersRespJson.getString("total"), "12");

        for (int i = 0; i < getUsersRespJson.getInt("data.size"); i++) {
            softAssert.assertNotNull(getUsersRespJson.getString("data[" + i + "].email"), "email not found" + i);
            softAssert.assertNotNull(getUsersRespJson.getString("data[" + i + "].first_name"), "first name not found" + i);
            softAssert.assertNotNull(getUsersRespJson.getString("data[" + i + "].last_name"), "last name not found" + i);
            softAssert.assertNotNull(getUsersRespJson.getString("data[" + i + "].id"), "id not found" + i);
            softAssert.assertNotNull(getUsersRespJson.getString("data[" + i + "].avatar"), "avatar not found" + i);

        }
       }

    @Given("token is generated")
    public static void Genrated() throws IOException {
        clientToken1 = given().spec(requestSpecification()).body(PayloadGenerator.token().toString()).when().post("/auth").then()
                .log().all().extract().jsonPath().getString("token");
       }

    @When("User token is generated")
    public void userTokenIsGenerated() throws IOException {
  Response response= given().spec(requestSpecification()).body(PayloadGenerator.userCreate().toString()).when().post("Account/v1/User").then().log().all().extract().response();
        System.out.println(response.toString());

    }

    @When("Creates a new booking {string}")
    public void createsANewBooking(String scenario) throws IOException {
bookingRequestBody = Utils.parseJSONFile("src/test/resources/testing/testingbook.json").getJSONObject(scenario).toString();
        BookingsImpl.createBookings(bookingRequestBody);
    }

    @And("Verifies booking details displayed properly")
    public void verifiesBookingDetailsDisplayedProperly() {
        JsonPath bookingRequestBodyRequest = new JsonPath(bookingRequestBody);
        createBookingResp.then().body("booking.totalprice",equalTo(bookingRequestBodyRequest.get("totalprice")));
        createBookingResp.then().statusCode(HttpStatus.SC_OK).statusCode(200);



    }


    @Then("the status codes of {string} Response is {int} and Response Body matches with {string} swagger schema")
    public void swaggerSchemas(String action, int statusCode, String schema) throws IOException {
        Assert.assertTrue(Arrays.stream(bookingsSchemas.values()).anyMatch((t) -> t.toString().equals(schema)), "schema not found" + schema);
   Response response = null;
   switch (action.toLowerCase()) {
       case "test":
           break;
       default:
   }

   Utils.swaggerSchema(response,statusCode, schema,COMPONENT_BOOKING);

    }

    @When("I get details with {string}")
    public void iGetDetailsWithValidId(String scenario) {
       String id= createBookingResp.then().extract().jsonPath().getString("bookingid");
       System.out.println(id);

    }

  }
