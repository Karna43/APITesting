package org.api;

import static io.restassured.RestAssured.given;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {
	
	@Test(priority = 1)
	void testHeaders() {
		given()
		.when()
			.get("https://www.google.com/")
		.then()
			.header("Content-Type", "text/html; charset=ISO-8859-1")
			.and()
			.header("Content-Encoding", "gzip")
			.and()
			.header("Server", "gws");
			//.log().headers()/cookies()/body()/all();
	}
	
	@Test(priority = 2)
	void getHeaders() {
		Response res = given()
				.when()
					.get("https://www.google.com/");
					String header_value = res.getHeader("Content-Type");
					System.out.println(header_value);
					
					Headers headers = res.getHeaders();
					for(Header i : headers) {
						System.out.println(i.getName()+": "+i.getValue());
					}
	}
}
