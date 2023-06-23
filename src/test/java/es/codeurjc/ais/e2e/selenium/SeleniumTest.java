package es.codeurjc.ais.e2e.selenium;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import es.codeurjc.ais.Application;
import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

	@LocalServerPort
	int port;

	WebDriver driver;
    private WebDriverWait wait;
	
	@BeforeAll
	public static void setupClass() {
		WebDriverManager.chromedriver().setup();
	}
	
	@BeforeEach
	public void setup() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
        //options.addArguments("no-sandbox");
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	@AfterEach
	public void teardown() {
		if(driver != null) {
			driver.quit();
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
}