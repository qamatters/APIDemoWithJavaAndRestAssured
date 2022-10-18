package apiHelper.apiMethods;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.print.attribute.standard.RequestingUserName;

import static apiHelper.util.commonUtil.createURI;
import static io.restassured.RestAssured.given;

public class deleteRequest {
    public static Response deleteRequest(String env, String endPoint, String bookingId, String tokenValue, ExtentTest extentReports) {
        String token = "token=" + tokenValue;
        RestAssured.baseURI = createURI(env, endPoint);
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpecification = given().log().all().
                header("Content-Type", "application/json").
                header("Cookie", token);
        Response response = requestSpecification.delete().then().extract().response();
        return response;
    }
}
