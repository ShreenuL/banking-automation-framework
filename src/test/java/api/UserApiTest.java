package api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserApiTest {

    @Test
    public void getUsersTest() {

        UserAPI userAPI = new UserAPI();

        Response response = userAPI.getUsers();

        int statusCode = response.getStatusCode();

        System.out.println("Status Code: " + statusCode);
        System.out.println(response.getBody().asPrettyString());

        Assert.assertEquals(statusCode, 200);

    }
    
    @Test
    public void createUserTest() {

        UserAPI userAPI = new UserAPI();

        Response response = userAPI.createUser("Shreenu", "QA Engineer");

		
		 int statusCode = response.getStatusCode();
		 
		 System.out.println("Status Code: " + statusCode);
		 System.out.println(response.getBody().asPrettyString());
		
		 Assert.assertEquals(statusCode, 201);
		 
        response.then().statusCode(201);
        
        String name = response.jsonPath().getString("name");
        String job = response.jsonPath().getString("job");

        System.out.println("Name: " + name);
        System.out.println("Job: " + job);

        Assert.assertEquals(name, "Shreenu");
        Assert.assertEquals(job, "QA Engineer");
    }
}