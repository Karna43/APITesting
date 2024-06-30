package org.api;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class TestCookies {
	
	@Test(priority = 1)
	void testCookies() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.cookie("ABC","danlvjNA:Fj,nfmndmnkd")
			.log().all();
	}
	
	@Test(priority = 2)
	void getCookiesInfo() {

		Response res = given()
		.when()
			.get("https://www.google.com/");
			String cookie_value = res.getCookie("ABC");
			System.out.println(cookie_value);
			
			Map<String, String> cookies = res.getCookies();
			for(String i : cookies.keySet()) {
				System.out.println(i+": "+res.getCookie(i));
			}
	}
}
