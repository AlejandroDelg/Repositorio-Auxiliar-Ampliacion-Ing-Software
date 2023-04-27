package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("REST tests")
public class smokeTest {

    
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
	public void getAllBooks() throws Exception {

        when()
            .get("/api/books/?topic=adventure").
        then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json");
    
    }
}
