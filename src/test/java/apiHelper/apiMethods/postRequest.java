package apiHelper.apiMethods;

import io.restassured.RestAssured;
import io.restassured.response.Response;


import static apiHelper.util.commonUtil.createURI;
import static apiHelper.util.commonUtil.readRequestBody;
import static io.restassured.RestAssured.given;

public class postRequest {

    public static Response postRequest(String env, String endPoint, String requestBody) {
        RestAssured.baseURI = createURI(env, endPoint);
        String body = readRequestBody(requestBody);
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given().
                header("Content-Type", "application/json").
                body(body).
                post().
                then().
                extract().
                response();
        return response;
    }

    public static Response postRequestWithEmptyBody(String env, String endPoint) {
        RestAssured.baseURI = createURI(env, endPoint);
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given().log().all().
                header("Content-Type", "application/json").
                post().
                then().
                extract().
                response();
        return response;
    }

    public static Response postRequestWithNoHeader(String env, String endPoint, String requestBody) {
        RestAssured.baseURI = createURI(env, endPoint);
        RestAssured.useRelaxedHTTPSValidation();
        String body = readRequestBody(requestBody);
        Response response = given().
                body(body).
                post().
                then().
                extract().
                response();
        return response;
    }

}
