package apiHelper.apiMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.HashMap;

import static apiHelper.util.commonUtil.*;
import static io.restassured.RestAssured.given;

public class putRequest {

    public static Response putRequest(String env,String endPoint, String bookingId,  String requestName, HashMap<String, Object> updatedValue, String tokenValue) {
        RestAssured.baseURI = createURI(env, endPoint);
        String requestBody = readRequestBody(requestName);
        String token = "token="+ tokenValue;
        RestAssured.useRelaxedHTTPSValidation();
        if (updatedValue.size() > 0) {
            if ( updatedValue.get("firstname") != null && !updatedValue.get("firstname").toString().isEmpty()) {
                requestBody = setJsonData("$.firstname", updatedValue.get("firstname"), requestBody);
            }
            if (updatedValue.get("lastname") != null && !updatedValue.get("lastname").toString().isEmpty()) {
                requestBody = setJsonData("$.lastname", updatedValue.get("lastname"), requestBody);
            }
            if (updatedValue.get("totalprice") != null && !updatedValue.get("totalprice").toString().isEmpty()) {
                requestBody = setJsonData("$.totalprice", updatedValue.get("totalprice"), requestBody);
            }
            if (updatedValue.get("depositpaid") != null && !updatedValue.get("depositpaid").toString().isEmpty()) {
                requestBody = setJsonData("$.depositpaid", updatedValue.get("depositpaid"), requestBody);
            }
            if (updatedValue.get("additionalneeds") != null && !updatedValue.get("additionalneeds").toString().isEmpty()) {
                requestBody = setJsonData("$.additionalneeds", updatedValue.get("additionalneeds"), requestBody);
            }
        }
        System.out.println("put request body is :" + requestBody);
        Response response = given().log().all().
                header("Content-Type", "application/json").
                header("Accept", "application/json").
                header("Cookie", token).
                body(requestBody).
                put(bookingId).
                then().
                extract().
                response();
        return response;
    }

}
