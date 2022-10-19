package apiHelper.test.CRUDScenario;

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
import static apiHelper.services.deleteBooking.deleteBookingRequest.deleteBookingValidation;
import static apiHelper.services.getBookingDetails.getBooking.getSpecificBookingDetails;
import static apiHelper.services.updateBooking.updateBooking.updateBookingAndGetBookingDetails;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class RegressionTest extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void test_validateCrudWFForBookingEndPoint(String env, Method method) {
        ExtentTest extentTest =  startTest(method.getName(), "Create booking end point Testing Started");
        HashMap<String, Object> updatedValues = new HashMap<>();
        String requestBody = "createBooking.json";
        String updatedName = "deepak";

        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");
        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, requestBody);
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
        String bookingId = responseValuesFromPostRequest.get("bookingId").toString();

        extentTest.log(Status.PASS, "Newly created booking ID is :" + bookingId );
        HashMap<String, Object> responseValuesFromGetSpecificRequest = getSpecificBookingDetails(env,GET_BOOKING_IDS, bookingId);
        responseValuesFromGetSpecificRequest.forEach((key, value) -> System.out.println(key + " : " + value));

        extentTest.log(Status.INFO, "Updating the user name and validating that the user name is updated for the booking id :" + bookingId);
        updatedValues.put("firstname", updatedName);
        HashMap<String, Object> responseValuesFromUpdateRequest = updateBookingAndGetBookingDetails(env, UPDATE_BOOKING, bookingId,requestBody, updatedValues, token);
        Assert.assertEquals(updatedValues.get("firstname").toString(), responseValuesFromUpdateRequest.get("firstname").toString());
        extentTest.log(Status.PASS, "Updated value "+updatedName+" is reflecting for the  :" + bookingId );

        extentTest.log(Status.INFO, "Deleting the newly created booking id :" + bookingId);
        deleteBookingValidation(env, DELETE_BOOKING, bookingId, token, extentTest);
        extentTest.log(Status.PASS, "Booking id " + bookingId + " is successfully deleted");

        extentTest.log(Status.INFO, "Execution completed for " + method.getName());

    }


}
