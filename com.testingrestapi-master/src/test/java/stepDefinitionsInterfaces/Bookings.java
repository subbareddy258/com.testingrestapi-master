package stepDefinitionsInterfaces;

import io.restassured.response.Response;

public interface Bookings {

    Response createBookings(String bookingRequestBody);
}
