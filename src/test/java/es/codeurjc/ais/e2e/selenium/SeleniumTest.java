package es.codeurjc.ais.e2e.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.ais.Application;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

    @LocalServerPort
    int port;

    WebDriver driver;

    @BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	@BeforeEach
	public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
		driver = new ChromeDriver(options);
	}

	@AfterEach
	public void teardown() {
		if (this.driver != null) {
            this.driver.quit();
		}
    }
    
    
    @Test
    @DisplayName("Check that the default topic is fantasy")
	public void checkDefaultTopic() throws Exception {

        String topic = "fantasy";

        this.driver.get("http://localhost:"+this.port+"/");

        String title = driver.findElement(By.tagName("h1")).getText();
        
        assertEquals("Books (topic="+topic+")", title);
    }

    /* 
    @Test
    public void drama() throws InterruptedException 
    {
		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "drama";
        
        Thread.sleep(3000);
        driver.findElement(By.name("topic")).sendKeys(mensaje);
        Thread.sleep(300);
        driver.findElement(By.id("search-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("Pride and Prejudice")).click();
        Thread.sleep(300);
        String topic = driver.findElement(By.id("drama")).getText();
        assertEquals(mensaje,topic);
        
    } @Test
    public void crearReview() throws InterruptedException 
    {
		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "epic fantasy";
        
        Thread.sleep(3000);
        driver.findElement(By.name("topic")).sendKeys(mensaje);
        Thread.sleep(300);
        driver.findElement(By.id("search-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("The Way of Kings")).click();
        Thread.sleep(300);
        String titulo_review = "Esto es el titulo de la review";
        driver.findElement(By.name("nickname")).sendKeys(titulo_review);
        Thread.sleep(300);
        String descripcion_review = "Esto es la descripcion de la review";
        driver.findElement(By.name("content")).sendKeys(descripcion_review);
        Thread.sleep(3000);
        driver.findElement(By.id("add-review")).click();
        Thread.sleep(1000);
        String tituloRecibido = driver.findElement(By.className("author")).getText();
        assertEquals(titulo_review,tituloRecibido);
        
    }
    
    @Test
    public void borrarReview()throws InterruptedException
    {
		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "epic fantasy";
        
        Thread.sleep(1000);
        driver.findElement(By.name("topic")).sendKeys(mensaje);
        Thread.sleep(300);
        driver.findElement(By.id("search-button")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("The Way of Kings")).click();
        Thread.sleep(300);
        driver.findElement(By.id("add-review")).click();
        Thread.sleep(1000);
        String tituloRecibido = driver.findElement(By.id("error-message")).getText();
        Thread.sleep(1000);
        assertNotEquals(tituloRecibido, null);
    	
    }
    */
}
