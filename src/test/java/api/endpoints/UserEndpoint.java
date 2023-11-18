package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.response.Response;

public class UserEndpoint {
	// end-point --> create a new user
	public static Response createUser(User payload) {
		Response response; 
		response = given()
						.contentType("application/json")
						.accept("application/json")
						.body(payload)
					.when()
						.post(Route.postUrl);
		return response;
	}
	
	// end-point --> read user
	public static Response readUser(String userName) {
		Response response; 
		response = given()
						.pathParam("username", userName)
					.when()
						.get(Route.getUrl);
		return response;
	}
	
	// end-point --> update user
	public static Response updateUser(User payload, String userName) {
		Response response; 
		response = given()
						.accept("application/json")
						.contentType("application/json")
						.pathParam("username", userName)
						.body(payload)
					.when()
						.put(Route.updateUrl);
		return response;
	}
	
	// end-point --> delete user
	public static Response deleteUser(String userName) {
		Response response; 
		response = given()
						.pathParam("username", userName)
					.when()
						.get(Route.deleteUrl);
		return response;
	}
}
