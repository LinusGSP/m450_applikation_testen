package ch.project.quizme;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SeleniumTest {

    private WebDriver driver;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    /**
     * Initializes the driver each time a test is executed.
     */
    @BeforeEach
    void setupTest() {
        driver = new FirefoxDriver();
    }

    /**
     * Shuts down the used browser session after every test.
     */
    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @Description("Der basic test soll testen ob der Driver geladen werden kann indem das Element 'Title' in der Quizme Seite gesucht wird.")
    void basicTest() {
        driver.get("http://localhost:3000");
        String title = driver.getTitle();
        assertEquals("Quiz Me", title);
    }


    @Test
    @Description("Create a new Learnset with name 'Learnset-1' DE -> GB")
    void CreateLearnset() throws InterruptedException {
        // Navigate to the application URL
        driver.get("http://localhost:3000/");

        // Click on the "Create" link
        driver.findElement(By.linkText("erstelle")).click();

        // Enter the Learnset name
        WebElement learnsetName = driver.findElement(By.id("learnsetname"));
        learnsetName.click();
        learnsetName.sendKeys("Learnset-1");

        // Select the language
        WebElement languageDropdown = driver.findElement(By.id("language1"));
        languageDropdown.click();
        languageDropdown.findElement(By.xpath("//option[. = 'Deutsch']")).click();

        // Click the "Create" button
        driver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > button")).click();

        driver.get("http://localhost:3000/");

        new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card"))
        );

        List<WebElement> cards = driver.findElements(By.cssSelector(".card"));
        WebElement x = cards.get(cards.toArray().length - 1);

        assertFalse(x.getText().isEmpty());
    }


    @Test
    @Description("Enter any learnset")
    void EnterLearnset() {
        driver.get("http://localhost:3000/");

        new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card"))
        );
        driver.findElements(By.cssSelector(".card")).get(0).click();

        String actualUrl = driver.getCurrentUrl();
        int result = Integer.parseInt(String.valueOf(actualUrl.charAt(actualUrl.length() - 1)));
        assertNotEquals(-1, result);
    }

    @Test
    @Description("Dieser Test prüft die Abfrage der Lernwörter und ob diese korrekt validiert werden.")
    void checkCorrectWordValidation() {
        driver.get("http://localhost:3000/");

        new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("strong"))
        ).click();

        new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                ExpectedConditions.presenceOfElementLocated(By.linkText("Answer Mode"))
        ).click();

        WebElement inpBox = driver.findElement(By.tagName("input"));
        inpBox.sendKeys("test 1");

        driver.findElement(By.xpath("//*[text()='Enter']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        assertEquals("Wrong, try again", alertText);
    }

    @Test
    @Description("Dieser Test prüft ob man sich mit dem admin passwort '12345' einloggen kann")
    void checkAdminPage() {
        driver.get("http://localhost:3000/");
        driver.findElement(By.linkText("Admin")).click();
        WebElement inputField = driver.findElement(By.id("password"));

        inputField.sendKeys("12345");
        driver.findElement(By.xpath("//*[text()='Submit']")).click();
        assertEquals("http://localhost:3000/admin", driver.getCurrentUrl());
    }


    @Test
    @Description("Dieser Test prüft ob man auf der Admin page die Lernsets deleten kann")
    void checkAdminDeletion() {
        driver.get("http://localhost:3000/");
        driver.findElement(By.linkText("Admin")).click();
        WebElement inputField = driver.findElement(By.id("password"));

        inputField.sendKeys("12345");
        driver.findElement(By.xpath("//*[text()='Submit']")).click();

        List<WebElement> children = driver.findElements(By.cssSelector(".card"));
        int numberOfChildren = children.size();

        driver.findElement(By.xpath("//*[text()='X']")).click();

        new WebDriverWait(driver, Duration.ofSeconds(1)).until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card"))
        );

        List<WebElement> NewChildren = driver.findElements(By.cssSelector(".card"));
        int numberOfChildrenNew = NewChildren.size();

        assertEquals(numberOfChildren, numberOfChildrenNew);
    }
}
