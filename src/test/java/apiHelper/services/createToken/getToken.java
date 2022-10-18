package apiHelper.services.createToken;

import com.jayway.jsonpath.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static apiHelper.apiMethods.postRequest.postRequest;

public class getToken {

    public static String getTokenValue(String env, String endPoint, String request) {
        String token = "";
        Response response = postRequest(env, endPoint, request);
        if (response.statusCode() == 200) {
            String responseValuesAsString = response.asString();
            System.out.println("Response from create token is :" +responseValuesAsString);
             token = JsonPath.read(responseValuesAsString, "$.token").toString();
        } else {
            System.out.println("request failed :" + response);
        }
        return token;
    }
}
