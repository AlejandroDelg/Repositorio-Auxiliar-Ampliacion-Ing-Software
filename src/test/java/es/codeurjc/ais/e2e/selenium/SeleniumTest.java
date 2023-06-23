package es.codeurjc.ais.e2e.selenium;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;

import org.junit.jupiter.api.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.ais.Application;


@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest 
{
    @LocalServerPort
    int port;

	private WebDriver driver;

	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void setupTest() {
		driver = new ChromeDriver();
	}

	@AfterEach
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
	
    @Test
    public void theTwoTowers() throws InterruptedException 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "The Lord of the Rings";
        
        wait.until(presenceOfElementLocated(By.name("topic")));
        driver.findElement(By.name("topic")).sendKeys(mensaje);

        wait.until(presenceOfElementLocated(By.id("search-button")));
        driver.findElement(By.id("search-button")).click();

        wait.until(presenceOfElementLocated(By.id("The Two Towers")));
        driver.findElement(By.id("The Two Towers")).click();

        wait.until(presenceOfElementLocated(By.id("bookTitle")));
        String topic = driver.findElement(By.id("bookTitle")).getText();

        assertEquals("The Two Towers",topic);
        
    }
    
    @Test
    public void theReturnOfTheKingError()throws InterruptedException
    {   
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "The Lord of the Rings";
        wait.until(presenceOfElementLocated(By.name("topic")));

        driver.findElement(By.name("topic")).sendKeys(mensaje);
        wait.until(presenceOfElementLocated(By.id("search-button")));
        driver.findElement(By.id("search-button")).click();

        wait.until(presenceOfElementLocated(By.id("The Return of the King")));
        driver.findElement(By.id("The Return of the King")).click();
        
        wait.until(presenceOfElementLocated(By.id("error-message")));
        String tituloRecibido = driver.findElement(By.id("error-message")).getText();
        assertEquals("Error when retrieving a book: unsupported format",tituloRecibido);
    	
    }
    
    @Test
    public void createReviewAndDelete() throws InterruptedException 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("http://localhost:"+this.port+"/");
        String mensaje = "epic fantasy";
        wait.until(presenceOfElementLocated(By.name("topic")));
        driver.findElement(By.name("topic")).sendKeys(mensaje);
        
        wait.until(presenceOfElementLocated(By.id("search-button")));
        driver.findElement(By.id("search-button")).click();
        
        
        wait.until(presenceOfElementLocated(By.id("A Game of Thrones")));
        driver.findElement(By.id("A Game of Thrones")).click();

        wait.until(presenceOfElementLocated(By.name("nickname")));
        String titulo_review = "Esto es el titulo de la review";
        driver.findElement(By.name("nickname")).sendKeys(titulo_review);

        wait.until(presenceOfElementLocated(By.name("content")));
        String descripcion_review = "Esto es la descripcion de la review";
        driver.findElement(By.name("content")).sendKeys(descripcion_review);

        
        wait.until(presenceOfElementLocated(By.id("add-review")));
        driver.findElement(By.id("add-review")).click();

        
        String idReview = titulo_review + "-delete";
        wait.until(presenceOfElementLocated(By.id(idReview)));
        driver.findElement(By.id(idReview)).click();
        

        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.id(idReview)).getText();
        });
    }
    
}
