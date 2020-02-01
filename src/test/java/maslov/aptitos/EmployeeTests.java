package maslov.aptitos;

import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;


public class EmployeeTests {

	private static final String API_URL = "http://localhost:9000";

	@Test
	public void getEmployeeByName() {
		given()
				.baseUri(API_URL)
				.when()
				.get("/employee/3")
				.then()
				.body("name", is("Maslov"));
	}

	@Test
	public void getListOfEmployees() {
		given()
				.baseUri(API_URL)
				.when()
				.get("/employees")
				.then()
				.extract().response().prettyPrint();
	}
}