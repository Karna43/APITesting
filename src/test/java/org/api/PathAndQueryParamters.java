package org.api;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class PathAndQueryParamters {
	
	@Test(priority = 1)
	void testQueryAndPathParamters() {
		given()
			.pathParam("mypath", "users")
			.queryParam("page", 2)
			.queryParam("id", 2)
		.when()
			.get("https://reqres.in/api/{mypath}")
		.then()
			.statusCode(200)
			.log().all();
	}
}
