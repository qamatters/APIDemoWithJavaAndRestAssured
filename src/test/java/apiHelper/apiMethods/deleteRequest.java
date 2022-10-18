package apiHelper.apiMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static apiHelper.util.commonUtil.createURI;
import static io.restassured.RestAssured.given;

public class deleteRequest {
    public static Response deleteRequest(String env, String endPoint, String bookingId, String tokenValue) {
            String token = "token="+ tokenValue;
            RestAssured.baseURI = createURI(env, endPoint);
            RestAssured.useRelaxedHTTPSValidation();
            Response response = given().log().all().
                    header("Content-Type", "application/json").
                    header("Cookie", token).
                    delete(bookingId).
                    then().
                    extract().
                    response();
            return response;
        }
}
