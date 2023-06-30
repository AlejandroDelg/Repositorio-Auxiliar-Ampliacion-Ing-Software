package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;

import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("REST tests")
public class RestTest {
    @LocalServerPort
    int port;
    @Test
    @DisplayName("get All Books")
	public void getAllBooks() throws Exception {

        RestAssured.port = port;
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
        String host = System.getProperty("host");
        Thread.sleep(30000);
        // Realiza la solicitud REST utilizando Rest Assured
        Response response = RestAssured.get(host + "api/books/OL27479W");
        int statusCode = response.getStatusCode();
        assertEquals(200, statusCode);

        String responseBody = response.getBody().asString();
        String description = response.jsonPath().get("description");
        int descriptionLength = description.length();
        int maxLength = 953; // Longitud máxima esperada
        System.out.println("descripcion de la respuesta: " + responseBody);
        System.out.println("push");
        assertTrue("La descripción del libro es mayor a 953 caracteres.", descriptionLength <= maxLength);

    }
    
}