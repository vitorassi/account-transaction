package com.anderson.application.integration.http;

import com.anderson.application.integration.AbstractIntegrationTests;
import io.restassured.response.Response;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@DisplayName("Test Integration Account")
@Sql({"/scripts/delete_account.sql", "/scripts/insert_account.sql"})
public class AccountControllerTest extends AbstractIntegrationTests {


    private String pathModel = "account/";

    private static Stream<Arguments> getAccountParam() {
        Integer index = 1;

        return Stream.of(
                Arguments.of("cb0c4b6e-e100-42d8-bbb6-a223e2326eca", HttpStatus.SC_NOT_FOUND, "response_not_found", index++),
                Arguments.of("3fa85f64-5717-4562-b3fc-2c963f66afa6", HttpStatus.SC_OK, "response_get_account_success", index++),
                Arguments.of("invalid_accountId", HttpStatus.SC_BAD_REQUEST, "response_account_invalid_uuid", index++)
        );
    }

    private static Stream<Arguments> postAccountParam() {
        Integer index = 1;
        return Stream.of(
                Arguments.of("body_created_account_document_invalid", HttpStatus.SC_BAD_REQUEST,
                        "response_created_account_document_invalid", index++),
                Arguments.of("body_created_account_success", HttpStatus.SC_OK,
                        "response_created_account_success", index++),
                Arguments.of("body_created_account_duplicate", HttpStatus.SC_CONFLICT,
                        "response_created_account_duplicate", index++)
        );
    }

    @ParameterizedTest(name = "getAccountTest accountId: {0} statusCode: {1} index: {2}")
    @MethodSource("getAccountParam")
    @DisplayName("Test all response GET Account")
    public void getAccountTest(String accountId, Integer statusCode, String nameResponseExpected, Integer index) {

        Response response = given()
                .spec(getSpecification())
                .when()
                .get("/v1/account/{accountId}", accountId)
                .then()
                .statusCode(statusCode)
                .extract().response();

        String expected = readFileJson(nameResponseExpected);

        validResponseBody(expected, response.getBody().prettyPrint(),
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("**.time", (a1, a2) -> ObjectUtils.allNotNull(a1, a2))
                )

        );
    }

    @ParameterizedTest(name = "postAccountTest body name: {0} statusCode: {1} json response expected:{2}  index: {3}")
    @MethodSource("postAccountParam")
    @DisplayName("Test POST new Account")
    public void postAccountTest(String nameBody, Integer statusCode, String nameResponseExpected, Integer index) {

        String bodyPost = readFileJson(nameBody);
        String expected = readFileJson(nameResponseExpected);

        Response response = given()
                .spec(getSpecification())
                .body(bodyPost)
                .when()
                .post("/v1/account")
                .then()
                .statusCode(statusCode)
                .extract().response();

        validResponseBody(expected, response.getBody().prettyPrint(),
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("**.id", (a1, a2) -> ObjectUtils.allNotNull(a1, a2)),
                        new Customization("**.time", (a1, a2) -> ObjectUtils.allNotNull(a1, a2))
                )
        );

    }

    @Override
    public String readFileJson(String name) {
        return super.readFileJson(pathModel + name);
    }
}
