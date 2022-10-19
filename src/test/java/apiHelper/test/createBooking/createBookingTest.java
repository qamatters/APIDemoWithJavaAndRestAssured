package apiHelper.test.createBooking;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

import static apiHelper.constants.endPoints.CREATE_BOOKING;
import static apiHelper.services.createBooking.createBooking.createBookingAndGetBookingDetails;
import static apiHelper.services.createBooking.createBooking.createBookingEndPointResponseTimeValidation;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class createBookingTest extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate create booking end point");
        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, "createBooking.json", extentTest);
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
        String bookingId = responseValuesFromPostRequest.get("bookingId").toString();
        extentTest.log(Status.PASS, "Newly created booking ID is :" + bookingId );
    }

    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPointResponseTime(String env, Method method) {
        int responseTime = 5;
        ExtentTest extentTest = startTest(method.getName(), "Validate create booking end point Response Time");
        createBookingEndPointResponseTimeValidation(env, CREATE_BOOKING, "createBooking.json", responseTime,extentTest );
    }
}
