import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class CarouselTest {
    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/Downloads/chromedriver-win64/chromedriver.exe"); // Update the path
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @Test
    public void carouselTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get("https://pennyshop.ba/shop/penny-plus"); // Replace with the actual URL
        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]"))); // Adjust selector
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/a"))); // Adjust selector
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1000);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        WebElement left= webDriver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/div/div[2]/button[1]"));
        WebElement right= webDriver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/div/div[2]/button[2]"));
        right.click();
        Thread.sleep(1000);
        right.click();

        Thread.sleep(1000);
        right.click();
        Thread.sleep(1000);
        WebElement product= webDriver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/div/div[1]/div/div[5]/div/a"));
        product.click();













    }
}