package api;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserAPI extends BaseAPI {

    public Response getUsers() {

        return RestAssured
                .given()
                .when()
                .get("/users");

    }
    
    public Response createUser(String name, String job) {

        Map<String, String> requestBody = new HashMap<>();

        requestBody.put("name", name);
        requestBody.put("job", job);

        return RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("/users");
    }
    
}