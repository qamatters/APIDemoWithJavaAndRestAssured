package apiHelper.test.getBookingDetails;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static apiHelper.constants.endPoints.GET_BOOKING_IDS;
import static apiHelper.services.getBookingDetails.getBooking.getBookingDetails;
import static apiHelper.services.getBookingDetails.getBooking.getSpecificBookingDetails;

public class getBookingEndPoint {
    @Test
    @Parameters({"env"})
    public static void validateGetBookingEndPoint(String env) {
        getBookingDetails(env,GET_BOOKING_IDS);
    }

    @Test
    @Parameters({"env"})
    public static void validateGetSpecificBookingDetailEndPoint(String env) {
        List<String> bookingIds = getBookingDetails(env,GET_BOOKING_IDS);
        HashMap<String, Object> responseValuesFromPostRequest = getSpecificBookingDetails(env,GET_BOOKING_IDS, bookingIds.get(0));
        responseValuesFromPostRequest.forEach((key, value) -> System.out.println(key + " : " + value));
    }

}
