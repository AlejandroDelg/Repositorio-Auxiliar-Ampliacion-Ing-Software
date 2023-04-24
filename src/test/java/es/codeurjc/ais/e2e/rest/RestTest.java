package es.codeurjc.ais.e2e.rest;

import static io.restassured.RestAssured.when;


import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;
import es.codeurjc.ais.review.Review;

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
	public void getAllBooks() throws Exception {

        when()
            .get("/api/books/?topic=drama").
        then()
            .assertThat()
                .statusCode(200)
                .contentType("application/json");
    
    }

    @Test
    public void getDramaSize() throws Exception
    {
        Response response = given().contentType("application/json").when()
        .get("/api/books/?topic=drama").then().extract()
        .response();
        
        System.out.println(response.getBody().asString());
        int size = from(response.getBody().asString()).get("size()");
        assertEquals(size, 10);
    }

    
    @Test
    public void getFantasy() throws Exception
    {
        Response response = given().contentType("application/json").when()
        .get("/api/books/?topic=fantasy").then().extract()
        .response();
        System.out.println(response.getBody().asString());
        String id = from(response.getBody().asString()).get("[0].id");

        JSONObject body = new JSONObject();
        Review review = new Review();
        review.setId(1);
        review.setContent("Contenido de la review");
        review.setNickname("Nick de la review");
        
        //crea la review
        body.put("id", review.getBookId());
        body.put("content", review.getContent());
        body.put("nickname", review.getNickname());


        //inserta la review
         given().contentType("application/json").body(body).when()
        .post("/api/books/"+id+"/review").then().extract()
        .response();
        
         //recoge toda la respuesta del libro cuando la review se ha creado
        Response response3 = given().contentType("application/json").body(body).when()
        .get("/api/books/"+id).then().extract()
        .response();
        
        System.out.println("\n\n RESPUESTA CON REVIEW: " + response3.getBody().asString());
        System.out.flush();
        

        
        String noReview = response.getBody().toString();
        String addReview = response3.getBody().toString();
    
        //compara el libro cuando no tenia review a cuando tiene review
        assertNotEquals(noReview, addReview);
    }   
    
    


    @Test
    public void getFantasyFirst() throws Exception
    {
        Response response = given().contentType("application/json").when()
        .get("/api/books/?topic=fantasy").then().extract()
        .response();
        
        System.out.println(response.getBody().asString());
        String id = from(response.getBody().asString()).get("[0].id");
        assertEquals(id,"OL138052W" );

        JSONObject body = new JSONObject();
        Review review = new Review();
        review.setId(1);
        review.setContent("Contenido de la review");
        review.setNickname("Nick de la review");
        //crea la review
        body.put("id", review.getBookId());
        body.put("content", review.getContent());
        body.put("nickname", review.getNickname());


        //le pasa la review creada con un metodo post
         given().contentType("application/json").body(body).when()
        .post("/api/books/"+id+"/review").then().extract()
        .response();
        

        Response response3 = given().contentType("application/json").when()
        .get("/api/books/"+id).then().extract()
        .response();
        
        System.out.println("\n\n RESPUESTA CON REVIEW: " + response3.getBody().asString());
        System.out.flush();

        //borra la review con un metodo delete (recibe el id de la review)
         given().contentType("application/json").when()
        .delete("/api/books/"+id+"/review/"+review.getId()).then().extract()
        .response();
        
         //recibe la respuesta despues de haber borrado la review
        Response response5 = given().contentType("application/json").when()
        .get("/api/books/"+id).then().extract()
        .response();
        
        //compara el cuerpo cuando se le ha añadido la review y cuando se ha eliminado
        String addReview = response3.getBody().toString();
        String deleteReview = response5.getBody().toString();

        System.out.println("\n\nRESPUESTA SIN REVIEW: " + response5.getBody().asString());
        System.out.flush();
        
        assertNotEquals(addReview, deleteReview);

    }

    
}