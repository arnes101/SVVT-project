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
public class CartFunctionalityTest {
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
    public void carTest() throws InterruptedException{

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

        // Test Case 1: Add a single product to the cart
        WebElement hoverDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section[2]/div/div/div/div/div[1]/div/div[1]/div/div/a")));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverDiv).perform();
            WebElement product = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section[2]/div/div/div/div/div[1]/div/div[1]/div/div/div/button")));
            product.click();

            List<WebElement> quickReview = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("modalQuickReview")));
            assertFalse(quickReview.isEmpty(),"Quick review not found");


            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.name("add_to_cart")));
            addToCart.click();

            WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalQuickReview\"]/div/div/div[1]/button")));
            cartIcon.click();


        js.executeScript("window.scrollBy(0, -1000);");
        System.out.println("Scrolled down");

            List<WebElement> cartItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dropdown_header_cart")));
            assertEquals(1, cartItems.size(), "Product not added to the cart correctly.");
        WebElement cartHeaderIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_cart")));
        cartHeaderIcon.click();
        List<WebElement> cartDropd = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"top\"]/div[2]/div[3]/div/a[1]")));
        assertFalse(cartDropd.isEmpty(), "Product not added to the cart correctly.");
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/div[2]/div[3]/div/a[1]")));
        cartButton.click();

        List<WebElement> cartPage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"my-cart-full\"]/div/div")));
        assertFalse(cartPage.isEmpty(), "Cart Page not found.");

        WebElement cartADD = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"my-cart-full\"]/div/div/div[1]/div[2]/div[1]/div[2]/div/div/div/span[1]")));
        cartADD.click();
        Thread.sleep(3000);

        WebElement cartRemove = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section/div/div/div[1]/div[2]/div/div[2]/span[3]")));
        cartRemove.click();

        Thread.sleep(3000);

        System.out.println("Add to Cart tests passed!");
        }
}
