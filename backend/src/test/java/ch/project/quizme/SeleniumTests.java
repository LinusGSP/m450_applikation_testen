package ch.project.quizme;

import ch.project.quizme.controller.LearnWordController;
import ch.project.quizme.databases.LearnWord;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTests {

    // selen
    WebDriver driver;
    @BeforeAll
    static void setupClass() {
        // WebDriverManager.chromedriver().setup();
        // WebDriverManager.edgedriver().setup();
        WebDriverManager.firefoxdriver().setup();
        // per Selenium documentation Opera (02.03.2023) is not supported at the moment
        // WebDriverManager.safaridriver().setup();
    }
    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }
    @AfterEach
    void teardown() {
        driver.quit();
    }

    /**
     * Test Selenium #1
     */
    @Test
    public void basicTest() {
        driver.get("http://127.0.0.1:3000/");
        String title = driver.getTitle();
        assertEquals("Quiz Me", title);
    }

    /**
     * Test Selenium #2
     */




}
