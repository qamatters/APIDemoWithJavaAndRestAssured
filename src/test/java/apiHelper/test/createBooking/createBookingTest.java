package apiHelper.test.createBooking;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;

import static apiHelper.constants.endPoints.CREATE_BOOKING;
import static apiHelper.services.createBooking.createBooking.*;

public class createBookingTest {
    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPoint(String env) {
        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, "createBooking.json");
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPointWhenThereIsNoRequestBody(String env) {
        createBookingWithEmptyBody(env, CREATE_BOOKING);
    }

    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPointWhenThereIsNoHeader(String env) {
        createBookingWithNoHeader(env, CREATE_BOOKING, "createBooking.json");
    }

}
