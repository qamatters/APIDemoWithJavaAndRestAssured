package apiHelper.test.createBooking;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.HashMap;

import static apiHelper.constants.endPoints.CREATE_BOOKING;
import static apiHelper.services.createBooking.createBooking.*;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class createBookingTest extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate create booking end point");
        HashMap<String, Object> responseValuesFromPostRequest = createBookingAndGetBookingDetails(env, CREATE_BOOKING, "createBooking.json");
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPointWhenThereIsNoRequestBody(String env, Method method) {
        startTest(method.getName(), "Validate create booking end point without request body");
        createBookingWithEmptyBody(env, CREATE_BOOKING);
    }

    @Test
    @Parameters({"env"})
    public static void test_validateCreateBookingEndPointWhenThereIsNoHeader(String env, Method method) {
        startTest(method.getName(), "Validate delete booking end point without headers");
        createBookingWithNoHeader(env, CREATE_BOOKING, "createBooking.json");
    }

}
