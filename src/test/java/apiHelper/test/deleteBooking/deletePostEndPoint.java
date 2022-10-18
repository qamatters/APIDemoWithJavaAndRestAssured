package apiHelper.test.deleteBooking;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static apiHelper.constants.endPoints.CREATE_TOKEN;
import static apiHelper.constants.endPoints.DELETE_BOOKING;
import static apiHelper.services.createToken.getToken.getTokenValue;
import static apiHelper.services.deleteBooking.deleteBookingRequest.deleteBookingValidation;

public class deletePostEndPoint {
    @Test
    @Parameters({"env"})
    public static void validateDeleteBookingEndPoint(String env) {
        String token = getTokenValue(env, CREATE_TOKEN, "createAuth.json");
        deleteBookingValidation(env, DELETE_BOOKING, "3", token);
    }
}
