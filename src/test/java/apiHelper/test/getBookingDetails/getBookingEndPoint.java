package apiHelper.test.getBookingDetails;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import static apiHelper.constants.endPoints.GET_BOOKING_IDS;
import static apiHelper.services.getBookingDetails.getBooking.getBookingDetails;
import static apiHelper.services.getBookingDetails.getBooking.getSpecificBookingDetails;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class getBookingEndPoint extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void validateGetBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate Get booking end point");
        getBookingDetails(env,GET_BOOKING_IDS, extentTest);
    }

    @Test
    @Parameters({"env"})
    public static void validateGetSpecificBookingDetailEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate get specific booking end point");
        List<String> bookingIds = getBookingDetails(env,GET_BOOKING_IDS, extentTest);
        HashMap<String, Object> responseValuesFromPostRequest = getSpecificBookingDetails(env,GET_BOOKING_IDS, bookingIds.get(0), extentTest);
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
