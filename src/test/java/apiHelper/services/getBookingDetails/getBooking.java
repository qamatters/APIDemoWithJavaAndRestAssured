package apiHelper.services.getBookingDetails;

import apiHelper.test.BaseTest;
import apiHelper.util.logs.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static apiHelper.apiMethods.getRequest.getRequest;
import static apiHelper.apiMethods.getRequest.getSpecificRequest;

public class getBooking extends BaseTest {
    public static List<String> getBookingDetails(String env, String endPoint, ExtentTest extentTest) {
        List<String> bookings = new ArrayList<>();
        Response response = getRequest(env,endPoint);
        if (response.statusCode() == 200) {
            String responseValuesAsString = response.asString();
            extentTest.log(Status.PASS,"Response is :" +responseValuesAsString);
            String bookingId = JsonPath.read(responseValuesAsString, "$.[*].bookingid").toString().
                    replaceAll("\\[|\\]", "").trim();
           bookings = Arrays.asList(bookingId.split(","));
        } else {
            extentTest.log(Status.FAIL,"request failed :" + response);
        }
        return  bookings;
    }

    public static HashMap<String, Object> getSpecificBookingDetails(String env, String endPoint, String bookingId, ExtentTest extentTest) {
        HashMap<String, Object> responseValues = new HashMap<>();
        Response response = getSpecificRequest(env, endPoint, bookingId);
        Assert.assertEquals(response.statusCode(),200);
        if (response.statusCode() == 200) {
            String responseValuesAsString = response.asString();
            extentTest.log(Status.PASS,"Response is :" +responseValuesAsString);

            String firstNameFromResponse = JsonPath.read(responseValuesAsString, "$.firstname").toString();
            String lastNameFromResponse = JsonPath.read(responseValuesAsString, "$.lastname").toString();
            String totalPriceFromResponse = JsonPath.read(responseValuesAsString, "$.totalprice").toString();
            Boolean depositPaidFromResponse = JsonPath.read(responseValuesAsString, "$.depositpaid");
            String checkinDateFromResponse = JsonPath.read(responseValuesAsString, "$.bookingdates.checkin").toString();
            String checkOutDateFromResponse = JsonPath.read(responseValuesAsString, "$.bookingdates.checkout").toString();
            String additionalNeedsFromResponse = JsonPath.read(responseValuesAsString, "$.additionalneeds").toString();

            responseValues.put("firstName", firstNameFromResponse);
            responseValues.put("lastName", lastNameFromResponse);
            responseValues.put("totalprice", totalPriceFromResponse);
            responseValues.put("depositpaid", depositPaidFromResponse);
            responseValues.put("checkinDate", checkinDateFromResponse);
            responseValues.put("checkOutDate", checkOutDateFromResponse);
            responseValues.put("additionalneeds", additionalNeedsFromResponse);
        } else {
            extentTest.log(Status.FAIL,"request failed :" + response);
        }
        return responseValues;
    }

}
