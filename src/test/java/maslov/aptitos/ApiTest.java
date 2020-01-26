package maslov.aptitos;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
//import static org.hamcrest.Matchers.equalTo;


public class ApiTest {

//	private static final String API_URL = "http://localhost:9000/message";

	@Test
	public void statusCode() {
		given()
				.baseUri("http://localhost:9000")
				.when()
				.get("/message")
				.then()
				.assertThat()
				.statusCode(200);
	}

	@Test
	public void evaluatesExpression() {
		int sum = 1+2+3;
		assertEquals(6, sum);
	}


//	@Test
//	public void dictionaryTest(){
//		RestAssured.useRelaxedHTTPSValidation();//SSL
//		given()
//				//.header("User-Agent", "Mozilla...")
//				//.header("JWT", "jwt_token")
//				.when()
//				.get(API_URL)
//				.then()
//				.statusCode(200);
//	}
}
