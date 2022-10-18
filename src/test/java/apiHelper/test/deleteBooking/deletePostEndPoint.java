package apiHelper.test.deleteBooking;

import apiHelper.test.BaseTest;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.lang.reflect.Method;

import static apiHelper.constants.endPoints.CREATE_TOKEN;
import static apiHelper.constants.endPoints.DELETE_BOOKING;
import static apiHelper.services.createToken.getToken.getTokenValue;
import static apiHelper.services.deleteBooking.deleteBookingRequest.deleteBookingValidation;
import static apiHelper.util.extentreports.ExtentTestManager.startTest;

public class deletePostEndPoint extends BaseTest {
    @Test
    @Parameters({"env"})
    public static void validateDeleteBookingEndPoint(String env, Method method) {
        ExtentTest extentTest = startTest(method.getName(), "Validate delete booking end point");
        extentTest.log(Status.INFO, "Execution starts for " + method.getName());
        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");
        deleteBookingValidation(env, DELETE_BOOKING, "3", token, extentTest);
        extentTest.log(Status.INFO, "Execution completed for " + method.getName());
    }
}
