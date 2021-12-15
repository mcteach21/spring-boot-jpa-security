package mc.apps.spring;

import io.restassured.RestAssured;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

// @SpringBootTest // load complete application context for end to end integration testing
public class RestApiTestsWithRestAssured {
    // private static final String BASE_URL = "http://localhost:8082";

    @BeforeEach
    public void setup() {
        RestAssured.baseURI = "http://localhost:8082";
        RestAssured.port = 8082;
    }

    @Test
    public void givenUrl_whenRequestGet_thenOK(){
        when().request("GET", "/rest/articles").then().statusCode(200);
    }

    @Test
    public void givenUrl_whenValidateResponseTime_thenSuccess() {
        when().get("/rest/articles").then().time(lessThan(50L));
    }

    @Test
    public void givenUrl_whenLogResponse_thenOK() {
        when().get("/rest/articles")
                .then().log().body().statusCode(200);
    }

    @Test
    public void givenUrl_whenValidateResponseSize_thenCorrect() {
        when().get("/rest/articles")
                .then().assertThat()
                .body("size()", is(2));
                //.body(".", hasSize(2));
    }

    @Test
    public void givenUrl_whenValidateFirstItem_thenCorrect() {
        when().get("/rest/articles")
                .then().assertThat()
                .body("[0].name", equalTo("LENOVO"));
    }
}
