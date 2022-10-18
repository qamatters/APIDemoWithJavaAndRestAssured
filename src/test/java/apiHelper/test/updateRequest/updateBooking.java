package apiHelper.test.updateRequest;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.HashMap;
import static apiHelper.constants.endPoints.*;
import static apiHelper.services.createToken.getToken.getTokenValue;
import static apiHelper.services.updateBooking.updateBooking.updateBookingAndGetBookingDetails;


public class updateBooking {

    @Test
    @Parameters({"env"})
    public static void validateUpdateBookingEndPoint(String env) {
        HashMap<String, Object> updatedValues = new HashMap<>();
        updatedValues.put("firstname", "testUser");
        updatedValues.put("lastname", "testuser1");
        updatedValues.put("totalprice", 220);
        updatedValues.put("additionalneeds", "dinner");
        String bookingIdToUpdate = "2";
        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");
        HashMap<String, Object> responseValuesFromPostRequest = updateBookingAndGetBookingDetails(env, UPDATE_BOOKING, bookingIdToUpdate,"createBooking.json", updatedValues, token);

        Assert.assertEquals(updatedValues.get("firstname").toString(), responseValuesFromPostRequest.get("firstname").toString());
        Assert.assertEquals(updatedValues.get("lastname"), responseValuesFromPostRequest.get("lastname"));
        Assert.assertEquals(updatedValues.get("totalprice").toString(), responseValuesFromPostRequest.get("totalprice").toString());
        Assert.assertEquals(updatedValues.get("additionalneeds"), responseValuesFromPostRequest.get("additionalneeds"));
    }


}
