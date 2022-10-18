package apiHelper.services.deleteBooking;
import apiHelper.test.BaseTest;
import apiHelper.util.logs.Log;
import io.restassured.response.Response;
import org.testng.Assert;
import static apiHelper.apiMethods.deleteRequest.deleteRequest;

public class deleteBookingRequest extends BaseTest {

    public static void deleteBookingValidation(String env, String endPoint, String bookingId, String tokenValue) {
        Response response = deleteRequest(env, endPoint, bookingId, tokenValue);
        String responseValuesAsString = response.asString();
        Log.info("Delete booking ID API Response is :" + responseValuesAsString);
        Assert.assertEquals(response.statusCode(), 201);
    }
}
