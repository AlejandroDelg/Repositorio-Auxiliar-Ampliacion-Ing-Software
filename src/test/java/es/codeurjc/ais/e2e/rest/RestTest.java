package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("REST tests")
public class RestTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    @DisplayName("get All Books")
	public void getAllBooks() throws Exception {

        when()
            .get("/api/books/?topic=drama").
        then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json");
    
    }

    @Test
    @DisplayName("sanity test")
    public void sanityTest() throws Exception
    {
        //espera de 30 segundos para que carge bien la aplicacion
        Thread.sleep(10000);
        String host = System.getProperty("host");
        RestAssured.baseURI = host;
        Thread.sleep(10000);
        when()
        .get("/api/books/?topic=drama")
        .then()
        .statusCode(200);
    }
    
}