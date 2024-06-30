package org.api;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import org.api.Pojo_PostRequest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PostRequests {
	
	@Test(priority = 1)
	void testPostUsingHashMap() {
		Map<String, Object> data = new HashMap<>();
		data.put("name", "Vijay");
		data.put("phone", "56879");
		String[] course = {"C","C++"}; 
		data.put("courses", course);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Vijay"))
			.body("phone",equalTo("56879"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset = utf-8")
			.log().all();
	}
	
	@Test(priority = 2)
	void testPostUsingJson() {
		JSONObject json = new JSONObject();
		json.put("name", "Vijay");
		json.put("phone", "56879");
		String[] course = {"C","C++"}; 
		json.put("courses", course);
		
		given()
			.contentType("application/json")
			.body(json.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Vijay"))
			.body("phone",equalTo("56879"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset = utf-8")
			.log().all();
	}
	
	@Test(priority = 3)
	void testPostUsingPojoClass() {
		Pojo_PostRequest pojo = new Pojo_PostRequest();
		pojo.setName("Vijay");
		pojo.setPhone("22344");
		String[] course = {"C","C++"};
		pojo.setCourses(course);
		
		given()
			.contentType("application/json")
			.body(pojo)
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Vijay"))
			.body("phone",equalTo("56879"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset = utf-8")
			.log().all();
	}
	
	@Test(priority = 4)
	void testPostUsingExternalJson() throws FileNotFoundException {
		File f = new File("..\\src\\test\\resources\\body.json");
		FileReader read = new FileReader(f);
		JSONTokener token = new JSONTokener(read);
		JSONObject json = new JSONObject(token);
		
		given()
			.contentType("application/json")
			.body(json.toString())
		.when()
			.post("http://localhost:3000/students")
		.then()
			.statusCode(201)
			.body("name",equalTo("Vijay"))
			.body("phone",equalTo("56879"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset = utf-8")
			.log().all();
	}

	@AfterMethod
	void deleteUser() {
		given()
		.when()
			.put("http://localhost:3000/students/3")
		.then()
			.statusCode(200)
			.log().all();
	}
}
