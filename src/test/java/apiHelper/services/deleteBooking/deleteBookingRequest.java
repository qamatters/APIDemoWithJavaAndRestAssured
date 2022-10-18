package apiHelper.services.deleteBooking;
import apiHelper.test.BaseTest;
import apiHelper.util.logs.Log;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.response.Response;
import org.testng.Assert;
import static apiHelper.apiMethods.deleteRequest.deleteRequest;

public class deleteBookingRequest extends BaseTest {

    public static void deleteBookingValidation(String env, String endPoint, String bookingId, String tokenValue, ExtentTest Reporter) {
        Response response = deleteRequest(env, endPoint, bookingId, tokenValue,  Reporter);
        String responseValuesAsString = response.asString();
        if( response.statusCode() == 201) {
            Reporter.log(Status.PASS, "Delete booking ID API is successfully working. API Response is :" + responseValuesAsString);
        } else {
            Reporter.log(Status.FAIL, "Delete booking ID API Response got failed. Response is :" + responseValuesAsString);
            Assert.assertEquals(response.statusCode(), 201);
        }
    }
}
