package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {

    public static RequestSpecification req;

    public static RequestSpecification requestSpecification() throws IOException {

        req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).build();
        return req;

    }


    public static String getGlobalValue(String key) throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("C:\\Ahmedgaber\\APItask\\api\\src\\test\\java\\resources\\global.properties");
        prop.load(fis);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.get(key).toString();
    }

}
