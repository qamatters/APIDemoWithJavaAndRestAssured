package apiHelper.test.deleteBooking;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.reflect.Method;
import java.util.HashMap;

import static apiHelper.constants.endPoints.*;
import static apiHelper.services.createBooking.createBooking.createBookingAndGetBookingDetails;
import static apiHelper.services.createToken.getToken.getTokenValue;
import static apiHelper.services.deleteBooking.deleteBookingRequest.deleteBookingValidation;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class deletePostEndPoint extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void validateDeleteBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate delete booking end point");
        String requestBody = "createBooking.json";
        extentTest.log(Status.INFO, "Execution starts for " + method.getName());
        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");

        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, requestBody, extentTest);
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
        String bookingId = responseValuesFromPostRequest.get("bookingId").toString();
        extentTest.log(Status.PASS, "Newly created booking ID is :" + bookingId );
        deleteBookingValidation(env, DELETE_BOOKING, bookingId, token, extentTest);
        extentTest.log(Status.INFO, "Execution completed for " + method.getName());
    }
}
