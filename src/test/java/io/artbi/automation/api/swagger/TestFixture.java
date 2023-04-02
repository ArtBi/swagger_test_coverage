package io.artbi.automation.api.swagger;

import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class TestFixture {
    protected RequestSpecification requestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .addHeader("Accept", "application/json")
            .addHeader("Content-Type", "application/json")
            .addFilter(new  SwaggerCoverageRestAssured())
            .build();

    @BeforeAll
    public static void setFilter() {
        RestAssured.baseURI = "https://petstore.swagger.io/";
        RestAssured.basePath = "/v2";

        RestAssured.filters(
                new SwaggerCoverageRestAssured(),
                new AllureRestAssured(),
                new RequestLoggingFilter());
    }


}
