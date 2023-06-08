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

import static org.junit.jupiter.api.Assertions.assertEquals;


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
    public void basicTest() {
        driver.get("http://localhost:3000");
        String title = driver.getTitle();
        assertEquals("Quiz Me", title);
    }

    @Disabled
    @Test
    @Description("Create a new Learnset with name 'Learnset-1' DE -> GB")
    public void CreateLearnset() throws InterruptedException {
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
        driver.findElement(By.cssSelector(".btn")).click();

        driver.get("http://localhost:3000/");
        Thread.sleep(1000);
        assertEquals(
                "Learnset-1",
                driver.findElement(By.cssSelector("div.card:nth-child(1) > .name")));
    }

    @Disabled

    @Test
    @Description("Enter any learnset")
    public void EnterLearnset() {
        driver.get("http://localhost:3000/");

        driver.findElement(By.cssSelector("div.card:nth-child(1) > .name")).click();

        String expectedUrl = "http://localhost:3000/1";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl);
    }

    @Test
    @Description("Dieser Test prüft die Abfrage der Lernwörter und ob diese korrekt validiert werden.")
    public void checkCorrectWordValidation() {
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

        assertEquals(alertText, "Wrong, try again");
    }

    @Test
    @Description("Dieser Test prüft ob man sich mit dem admin passwort '12345' einloggen kann")
    public void checkAdminPage() {
        driver.get("http://localhost:3000/");
        driver.findElement(By.linkText("Admin")).click();
        WebElement inputField = driver.findElement(By.id("password"));

        inputField.sendKeys("12345");
        driver.findElement(By.xpath("//*[text()='Submit']")).click();
        assertEquals(driver.getCurrentUrl(), "http://localhost:3000/admin");
    }


    @Test
    @Description("Dieser Test prüft ob man auf der Admin page die Lernsets deleten kann")
    public void checkAdminDeletion() {
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
