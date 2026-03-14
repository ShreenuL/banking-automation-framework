package api;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseAPI {

    public BaseAPI() {

//        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    	RestAssured.baseURI = ConfigReader.getProperty("apiURL");

    }
}