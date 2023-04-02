package io.artbi.automation.api.swagger.pet_tests;

import io.artbi.automation.api.swagger.TestFixture;
import io.artbi.automation.api.swagger.models.Pet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PetTest extends TestFixture {

    @Test
    void createPetTest() {
        Pet pet = Pet.builder()
                .name("chappy")
                .status("available")
                .build();

        Pet created =
                given()
                        .spec(requestSpec)
                        .body(pet)
                .when()
                        .post("/pet")
                .then()
                        .extract()
                        .as(Pet.class);

        given()
                .spec(requestSpec)
        .when()
                .get("/pet/{id}", created.getId())
        .then()
                .statusCode(200)
                .body("name", equalTo(pet.getName()));

    }

    @Test
    void getPetWithInvalidIdTest() {
        given()
                .spec(requestSpec)
        .when()
                .get("/pet/{petId}", 1)
        .then()
                .assertThat()
                .statusCode(404)
                .time(lessThan(5000L));

    }
}
