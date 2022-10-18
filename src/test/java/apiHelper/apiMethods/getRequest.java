package apiHelper.apiMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;



import static apiHelper.util.commonUtil.createURI;
import static io.restassured.RestAssured.given;

public class getRequest {

    public static Response getRequest(String env, String endPoint) {
        RestAssured.baseURI = createURI(env, endPoint);
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given().
                header("Content-Type", "application/json").
                get().
                then().
                extract().
                response();
        return response;
    }

    public static Response getSpecificRequest(String env, String endPoint, String id) {
        RestAssured.baseURI = createURI(env, endPoint);
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given().
                header("Content-Type", "application/json").
                get(id).
                then().
                extract().
                response();
        return response;
    }

}

