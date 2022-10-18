package apiHelper.util;

import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.io.IOException;

import static apiHelper.constants.apiConstants.*;

public class commonUtil {

    public static String createURI(String env, String endPoint) {
        if (env.equals("stage")) {
            return BASE_URI + endPoint;
        } else {
            return BASE_URI_STAGE;
        }
    }

    public static String readRequestBody(String requestName) {
        String path = System.getProperty("user.dir") + "/src/test/java/apiHelper/data/" + requestName;
        File file = new File(path);
        String requestBody = null;
        try {
            requestBody = JsonPath.parse(file).jsonString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody;
    }

    public static String setJsonData(String path, Object value, String jsonString) {
        return JsonPath.parse(jsonString).set(path, value).jsonString();
    }



}
