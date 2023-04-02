package io.artbi.automation.api.swagger.login_tests;

import io.artbi.automation.api.swagger.TestFixture;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends TestFixture {
    private static final String USERNAME = "test";
    private static final String PASSWORD = "abc123";

    @Test
    void loginTest() {

        String token =
                given()
                        .spec(requestSpec)
                        .auth().basic(USERNAME, PASSWORD)
                .when()
                        .get("/user/login")
                .then()
                        .statusCode(200)
                        .extract()
                        .path("message");


        given().spec(requestSpec)
                .contentType("application/json")
                .header("Authorization", "Bearer " + token)
        .when()
                .get("/pet/findByStatus?status=sold")
        .then()
                .statusCode(200);

    }

}
