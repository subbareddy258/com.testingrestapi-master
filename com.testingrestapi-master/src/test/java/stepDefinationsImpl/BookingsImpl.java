package stepDefinationsImpl;

import io.restassured.response.Response;
import stepDefinitions.AbstractStepDefinition;
import stepDefinitionsInterfaces.Bookings;
import utlis.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingsImpl extends AbstractStepDefinition implements Bookings {

    @Override
    public Response createBookings(String bookingRequestBody) {
        try {
            commonResponse = given(Utils.requestSpecification()).body(bookingRequestBody).log().all().when().post("/booking").then().log().all().extract().response();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createBookingResp = commonResponse;
        return commonResponse;
    }
}
