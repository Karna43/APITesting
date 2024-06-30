package org.api;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class ParsingJsonResponseData {
	
	@Test(priority = 1)
	void testJsonResponse() {
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students")
		.then()
			.statusCode(200)
			.header("Content-Type","application/json; charset = utf-8")
			.body("book[2].name",equalTo("Vijay"));
	}
	
	@Test(priority = 2)
	void testJsonResponseAssert() {
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students");
		Assert.assertEquals(res.getStatusCode(), 200);
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset = utf-8");
		String name = res.jsonPath().get("book[2].name").toString();
		Assert.assertEquals(name, "Vijay");
	}
	
	@Test(priority = 3)
	void testJsonResponseObject() {
		Response res = given()
			.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/students");
		boolean status = false;
		JSONObject json = new JSONObject(res.asString());
		for(int i=0;i<json.getJSONArray("students").length();i++) {
			String name = json.getJSONArray("students").getJSONObject(i).get("name").toString();
			System.out.println(name);
			if(name.equals("Vijay")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
	}
}
