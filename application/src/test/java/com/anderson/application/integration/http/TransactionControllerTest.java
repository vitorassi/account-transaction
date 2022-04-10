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

@DisplayName("Test Integration Transaction")
@Sql({"/scripts/delete_account.sql", "/scripts/insert_account.sql"})
public class TransactionControllerTest extends AbstractIntegrationTests {


    private String pathModel = "transaction/";

    private static Stream<Arguments> postTransactionParam() {
        Integer index = 1;
        return Stream.of(
                Arguments.of("body_created_transaction_success_negative", HttpStatus.SC_OK,
                        "response_created_transaction_success_negative", index++),
                Arguments.of("body_created_transaction_account_invalid", HttpStatus.SC_CONFLICT,
                        "response_created_transaction_account_invalid", index++),
                Arguments.of("body_created_transaction_amount_invalid", HttpStatus.SC_BAD_REQUEST,
                        "response_created_transaction_amount_invalid", index++),
                Arguments.of("body_created_transaction_operation_invalid", HttpStatus.SC_BAD_REQUEST,
                        "response_created_transaction_operation_invalid", index++),
                Arguments.of("body_created_transaction_account_uuid_invalid", HttpStatus.SC_BAD_REQUEST,
                        "response_created_transaction_account_uuid_invalid", index++),
                Arguments.of("body_created_transaction_success_positive", HttpStatus.SC_OK,
                        "response_created_transaction_success_positive", index++)
        );
    }

    @ParameterizedTest(name = "postTransactionTest body name: {0} statusCode: {1} json response expected:{2}  index: {3}")
    @MethodSource("postTransactionParam")
    @DisplayName("Test POST new Transaction")
    public void postAccountTest(String nameBody, Integer statusCode, String nameResponseExpected, Integer index) {

        String bodyPost = readFileJson(nameBody);
        String expected = readFileJson(nameResponseExpected);

        Response response = given()
                .spec(getSpecification())
                .body(bodyPost)
                .when()
                .post("/v1/transaction")
                .then()
                .statusCode(statusCode)
                .extract().response();

        validResponseBody(expected, response.getBody().prettyPrint(),
                new CustomComparator(JSONCompareMode.STRICT,
                        new Customization("**.transactionId", (a1, a2) -> ObjectUtils.allNotNull(a1, a2)),
                        new Customization("**.eventDate", (a1, a2) -> ObjectUtils.allNotNull(a1, a2)),
                        new Customization("**.time", (a1, a2) -> ObjectUtils.allNotNull(a1, a2))
                )
        );

    }

    @Override
    public String readFileJson(String name) {
        return super.readFileJson(pathModel + name);
    }
}
