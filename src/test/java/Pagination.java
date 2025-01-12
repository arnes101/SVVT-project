import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Pagination {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/Downloads/chromedriver-win64/chromedriver.exe");
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
    public void testPaginationFunctionality() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")));
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[4]/a")));
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        WebElement headerLink= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/div/div/div[1]/div/div[20]/div")));
        headerLink.click();
        Thread.sleep(1000);

        // Test Case 1: Navigate between pages
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1500);");
        System.out.println("Scrolled down");
        Thread.sleep(1000);


        WebElement nextPageButton = webDriver.findElement(By.xpath("//*[@id=\"pjax-container\"]/div[21]/nav/ul/li[3]/a/span"));
        nextPageButton.click();
        String nextPageUrl = webDriver.getCurrentUrl();
        assertTrue(nextPageUrl.contains("page=2"), "Did not navigate to the second page.");

        js.executeScript("window.scrollBy(0, 1500);");
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        WebElement previousPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pjax-container\"]/div[21]/nav/ul/li[1]/a")));
        previousPageButton.click();
        String firstPageUrl = webDriver.getCurrentUrl();
        assertTrue(firstPageUrl.contains("page=1"), "Did not navigate back to the first page.");

        js.executeScript("window.scrollBy(0, 1500);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        // Test Case 2: Validate correct number of products displayed per page
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("pjax-container")));
        int productsPerPage = products.size();
        assertTrue(productsPerPage > 0, "No products found on the page.");
        System.out.println("Products on current page: " + productsPerPage);

        // Test Case 3: Navigate to the last page
        WebElement lastPageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[5]/div/div[2]/div/div[21]/nav/ul/li[2]/ul/li[12]/a")));
        lastPageButton.click();
        String lastPageUrl = webDriver.getCurrentUrl();
        assertTrue(lastPageUrl.contains("page="), "Did not navigate to the last page.");

        products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("pjax-container")));
        assertFalse(products.isEmpty(), "No products found on the last page.");



    }
}
