package apiHelper.services.updateBooking;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

import static apiHelper.apiMethods.putRequest.putRequest;

public class updateBooking {
    public static HashMap<String, Object> updateBookingAndGetBookingDetails(String env, String endPoint, String bookingId, String requestNam, HashMap<String, Object> updatedValues, String token) {
        Response response = putRequest(env, endPoint, bookingId, requestNam, updatedValues, token);
        HashMap<String, Object> responseValues = new HashMap<>();
        Assert.assertEquals(response.statusCode(),200);
        if (response.statusCode() == 200) {
            String responseValuesAsString = response.asString();
            System.out.println("Response is :" + responseValuesAsString);
            String firstName = JsonPath.read(responseValuesAsString, "$.firstname").toString();
            String lastName = JsonPath.read(responseValuesAsString, "$.lastname").toString();
            String totalPrice = JsonPath.read(responseValuesAsString, "$.totalprice").toString();
            Boolean depositPaid = JsonPath.read(responseValuesAsString, "$.depositpaid");
            String checkinDate = JsonPath.read(responseValuesAsString, "$.bookingdates.checkin").toString();
            String checkOutDate = JsonPath.read(responseValuesAsString, "$.bookingdates.checkout").toString();
            String additionalNeeds = JsonPath.read(responseValuesAsString, "$.additionalneeds").toString();

            responseValues.put("firstname", firstName);
            responseValues.put("lastname", lastName);
            responseValues.put("totalprice", totalPrice);
            responseValues.put("depositpaid", depositPaid);
            responseValues.put("checkinDate", checkinDate);
            responseValues.put("checkOutDate", checkOutDate);
            responseValues.put("additionalneeds", additionalNeeds);
        } else {
            System.out.println("request failed :" + response);
        }
        return responseValues;
    }


}
