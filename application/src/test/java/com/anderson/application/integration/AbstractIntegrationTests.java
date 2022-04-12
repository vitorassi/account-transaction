package com.anderson.application.integration;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.yml")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("IntegrationTest")
public class AbstractIntegrationTests {

    @LocalServerPort
    private Integer port;

    public RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(String.format("http://localhost:%d", port))
                .setContentType(ContentType.JSON)
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .build();
    }


    public void validResponseBody(String expected, String response, CustomComparator customComparator) {
        try {
            JSONAssert.assertEquals(expected, response, customComparator);
        } catch (JSONException e) {
            fail("Fail test");
        }
    }

    public String readFileJson(String name) {
        try {
            String pathFile = String.format("json-test/%s.json", name);
            ClassLoader classLoader =
                    Optional.ofNullable(Thread.currentThread().getContextClassLoader())
                            .orElseThrow(NoSuchFieldError::new);

            File file = new File(Objects.requireNonNull(classLoader.getResource(pathFile)).getFile());
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8.name());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Fail test");
            return null;
        }
    }

}