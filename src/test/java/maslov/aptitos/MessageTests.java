package maslov.aptitos;

import io.restassured.common.mapper.TypeRef;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class MessageTests {

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
    public void checkParserJson() {
        List<Map<String, Object>> message = given()
                .baseUri("http://localhost:9000")
                .when()
                .get("/message")
                .as(new TypeRef<>() {});

        assertThat(message.get(0).get("id"), equalTo(4));
    }
}
