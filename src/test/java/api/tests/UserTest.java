package api.tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.payloads.User;
import io.restassured.response.Response;
import api.endpoints.UserEndpoint;

public class UserTest {
	
	Faker faker;
	User userPayload;
	//public Logger logger; // for logs
	
	@BeforeClass
	public void setup() {
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		//System.out.println(userPayload.getId());
		
		//logs
		//logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority=1)
	public void testPostUser(){
		//logger.debug("*************** Creating user ***************");
		Response response = UserEndpoint.createUser(userPayload);
		response.then().log().all();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("*************** User is created ***************");

	}
	@Test(priority=2)
	public void testGetUserByName(){
		//logger.info("*************** Reading user info ***************");
		Response response = UserEndpoint.readUser(userPayload.getUsername());
		response.then().log().all();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("*************** User info is displayed ***************");
	}
	@Test(priority=3)
	public void testUpdateUserByName(){
		// update existing payload
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		//logger.info("*************** Updating user info ***************");
		
		Response response = UserEndpoint.updateUser(userPayload, userPayload.getUsername());
		response.then().log().body();
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		// checking data after update request
		Response responseAfterUpdate = UserEndpoint.readUser(userPayload.getUsername());
		Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
		
		//logger.info("*************** User info is updated ***************");
	}
	@Test(priority = 4)
	public void testDeleteUserByName(){
		
		//logger.info("*************** Deleting user info ***************");
		Response response = UserEndpoint.deleteUser(this.userPayload.getUsername());
		response.then().log().body();
		
		Assert.assertEquals(response.getStatusCode(), 200);
		//logger.info("*************** User info is deleted ***************");
	}
	
	

}
