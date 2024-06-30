package org.api;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

/*
given()
	content type, set cookies, add auth, add param, set header info etc
when()
	get,post,put,delete
then()
	validate status code, extract response, headers, cookies, response body
*/


public class HttpRequests {
	int id;
	@Test(priority = 1)
	void getUser() {
		when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();
	}
	
	@Test(priority = 2)
	void createUser() {
		Map<String, String> m = new HashMap<>();
		m.put("name", "karna");
		m.put("job", "tester");
		id = given()
			.contentType("application/json")
			.body(m)
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
//		.then()
//			.statusCode(201)
//			.log().all()
	}
	
	@Test(priority = 3, dependsOnMethods = {"createUser"})
	void updateUser() {
		Map<String, String> m = new HashMap<>();
		m.put("name", "karna");
		m.put("job", "developer");
		given()
			.contentType("application/json")
			.body(m)
		.when()
			.put("https://reqres.in/api/users"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test(priority = 4)
	void deleteUser() {
		when()
			.put("https://reqres.in/api/users"+id)
		.then()
			.statusCode(200)
			.log().all();
	}
}
