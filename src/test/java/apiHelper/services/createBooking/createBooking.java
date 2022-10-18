package apiHelper.services.createBooking;

import apiHelper.test.BaseTest;
import apiHelper.util.logs.Log;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.prefs.BackingStoreException;

import static apiHelper.apiMethods.postRequest.*;

public class createBooking extends BaseTest {

    public static HashMap<String, Object> createBookingAndGetBookingDetails(String env, String endPoint, String request) {
        Response response = postRequest(env, endPoint, request);
        HashMap<String, Object> responseValues = new HashMap<>();
        Assert.assertEquals(response.statusCode(), 200);
        if (response.statusCode() == 200) {
            String responseValuesAsString = response.asString();
            System.out.println("Response is :" + responseValuesAsString);
            String bookingId = JsonPath.read(responseValuesAsString, "$.bookingid").toString();
            String firstName = JsonPath.read(responseValuesAsString, "$.booking.firstname").toString();
            String lastName = JsonPath.read(responseValuesAsString, "$.booking.lastname").toString();
            String totalPrice = JsonPath.read(responseValuesAsString, "$.booking.totalprice").toString();
            Boolean depositPaid = JsonPath.read(responseValuesAsString, "$.booking.depositpaid");
            String checkinDate = JsonPath.read(responseValuesAsString, "$.booking.bookingdates.checkin").toString();
            String checkOutDate = JsonPath.read(responseValuesAsString, "$.booking.bookingdates.checkout").toString();
            String additionalNeeds = JsonPath.read(responseValuesAsString, "$.booking.additionalneeds").toString();
            responseValues.put("bookingId", bookingId);
            responseValues.put("firstName", firstName);
            responseValues.put("lastName", lastName);
            responseValues.put("totalprice", totalPrice);
            responseValues.put("depositpaid", depositPaid);
            responseValues.put("checkinDate", checkinDate);
            responseValues.put("checkOutDate", checkOutDate);
            responseValues.put("additionalneeds", additionalNeeds);
        } else {
            Log.info("request failed :" + response);
            Assert.assertEquals(response.statusCode(), 200);
        }
        return responseValues;
    }

    public static void createBookingWithEmptyBody(String env, String endPoint) {
        Response response = postRequestWithEmptyBody(env, endPoint);
        Log.info("Response for create booking with no request body is :" + response.asString());
        Assert.assertEquals(response.statusCode(), 500);
    }

    public static void createBookingWithNoHeader(String env, String endPoint, String request) {
        Response response = postRequestWithNoHeader(env, endPoint, request);
        Log.info("Response for create booking with no header is :" + response.asString());
        Assert.assertEquals(response.statusCode(), 500);
    }

}
