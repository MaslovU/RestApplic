package maslov.aptitos;

import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;


public class ApiTest {

//	private static final String API_URL = "http://localhost:9000/message";

	@Test
	public void statusCodeMessage() {
		given()
				.baseUri("http://localhost:9000")
				.when()
				.get("/message")
				.then()
				.assertThat()
				.statusCode(200);
	}

	@Test
	public void getEmployeeByName() {
		given()
				.baseUri("http://localhost:9000")
				.when()
				.get("/employee/3")
				.then()
				.body("name", is("Maslov"));
	}

	@Test
	public void getListOfEmployees() {
		given()
				.baseUri("http://localhost:9000")
				.when()
				.get("/employees")
				.then()
				.extract().response().prettyPrint();
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
