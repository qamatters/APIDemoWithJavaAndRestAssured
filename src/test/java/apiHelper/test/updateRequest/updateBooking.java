package apiHelper.test.updateRequest;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

import static apiHelper.constants.endPoints.*;
import static apiHelper.services.createBooking.createBooking.createBookingAndGetBookingDetails;
import static apiHelper.services.createToken.getToken.getTokenValue;
import static apiHelper.services.updateBooking.updateBooking.updateBookingAndGetBookingDetails;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;


public class updateBooking extends BaseTest {

    @Test
    @Parameters({"env"})
    public static void validateUpdateBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate update booking end point");
        HashMap<String, Object> updatedValues = new HashMap<>();
        updatedValues.put("firstname", "testUser");
        updatedValues.put("lastname", "testuser1");
        updatedValues.put("totalprice", 220);
        updatedValues.put("additionalneeds", "dinner");
        String requestBody = "createBooking.json";
        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");

        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, requestBody, extentTest);
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
        String bookingId = responseValuesFromPostRequest.get("bookingId").toString();
        extentTest.log(Status.PASS, "Newly created booking ID is :" + bookingId);
        extentTest.log(Status.INFO, "Updating the field values and validating that the updated values are reflecting for the the booking id :" + bookingId);
        HashMap<String, Object> responseValuesFromUpdateRequest = updateBookingAndGetBookingDetails(env, UPDATE_BOOKING, bookingId, "createBooking.json", updatedValues, token);

        Assert.assertEquals(updatedValues.get("firstname").toString(), responseValuesFromUpdateRequest.get("firstname").toString());
        Assert.assertEquals(updatedValues.get("lastname"), responseValuesFromUpdateRequest.get("lastname"));
        Assert.assertEquals(updatedValues.get("totalprice").toString(), responseValuesFromUpdateRequest.get("totalprice").toString());
        Assert.assertEquals(updatedValues.get("additionalneeds"), responseValuesFromUpdateRequest.get("additionalneeds"));
        extentTest.log(Status.PASS, "Updated values are reflecting for the  :" + bookingId);

    }

}
