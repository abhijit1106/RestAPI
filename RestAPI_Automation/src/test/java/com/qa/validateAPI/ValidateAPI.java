package com.qa.validateAPI;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.path.json.JsonPath;


public class ValidateAPI {

	
	@Test
	public void getAPI() {
		RestAssured.baseURI="https://api.spacexdata.com/";
		String response= given().header("Content-Type","application/json")
		.when().get("v4/launches/latest/")
		.then().log().all().assertThat().statusCode(200).body("name",equalTo("Turksat 5A"))
		.header("Server", "cloudflare").extract().response().asString();
		//System.out.println(response);
		JsonPath js = new JsonPath(response);
		String Ships = js.getString("ships");
		System.out.println(Ships);
	}
	
	
	@Test
	public void getAPI1() {
		String response=given()
		.when().get("https://api.spacexdata.com/v4/launches/latest/")
		.then().log().all()
		.statusCode(200)
		.statusLine("HTTP/1.1 200 OK")
		.assertThat().body("name",equalTo("Turksat 5A"))
		.header("Server", "cloudflare").extract().response().getBody().asString();
//		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String Ships = js.getString("ships");
		System.out.println(Ships);
	}
}
