package br.farias.rest.tests.refac;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;

import io.restassured.RestAssured;

public class ContasTest {
	
	@BeforeClass
	public static void login() {
		
		Map<String, String> login = new HashMap<>();
		login.put("email", "novo@novo.com");
		login.put("senha", "@123@");
		
		String TOKEN = given()
				.body(login)
			.when()
				.post("/signin")
			.then()
				.statusCode(200)
				.extract().path("token");
		
		RestAssured.requestSpecification.header("Authorization", "JWT "+ TOKEN);
		
		RestAssured.get("/reset").then().statusCode(200);
	}

}
