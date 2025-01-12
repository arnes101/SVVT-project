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
public class CheckoutFunct {
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
    public void carTest() throws InterruptedException {

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

        List<WebElement> quickReview = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("modalQuickReview"))); // Replace with the actual ID or locator
        assertFalse(quickReview.isEmpty(),"Quick review not found");


        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.name("add_to_cart"))); // Replace with the actual ID or locator
        addToCart.click();

        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"modalQuickReview\"]/div/div/div[1]/button"))); // Replace with the actual ID or locator
        cartIcon.click();


        js.executeScript("window.scrollBy(0, -1000);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");


        List<WebElement> cartItems = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("dropdown_header_cart"))); // Replace with the actual class name
        assertEquals(1, cartItems.size(), "Product not added to the cart correctly.");

        WebElement cartHeaderIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_cart"))); // Replace with the actual ID or locator
        cartHeaderIcon.click();

        List<WebElement> cartDropd = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/section/div"))); // Replace with the actual class name
        assertFalse(cartDropd.isEmpty(), "Product not added to the cart correctly.");

        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/div[2]/div[3]/div/a[1]"))); // Replace with the actual ID or locator
        cartButton.click();


        WebElement cartPayOut = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //*[@id=\"my-cart-full\"]/div/div/div[2]/a"))); // Replace with the actual ID or locator
        cartPayOut.click();




        WebElement username = webDriver.findElement(By.xpath("//*[@id=\"user_mail_register\"]"));
        username.sendKeys("arnes.rastoder@gmail.com");

        WebElement password = webDriver.findElement(By.xpath("//*[@id=\"user_pass_register\"]"));
        password.sendKeys("sa_aki_03");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //*[@id=\"new_user_register_form\"]/div/div[1]/button"))); // Replace with the actual ID or locator
        loginButton.click();

        WebElement firstname = webDriver.findElement(By.id("buyer_firstname"));
        firstname.sendKeys("amar");

        WebElement lastname = webDriver.findElement(By.id("buyer_lastname"));
        lastname.sendKeys("garibovic");

        WebElement company = webDriver.findElement(By.id("buyer_company"));
        company.sendKeys("company");

        WebElement address = webDriver.findElement(By.id("buyer_address"));
        address.sendKeys("address");

        WebElement city = webDriver.findElement(By.id("buyer_city"));
        city.sendKeys("sarajevo");

        WebElement postalCode = webDriver.findElement(By.id("buyer_postal_code"));
        postalCode.sendKeys("71210");

        WebElement country = webDriver.findElement(By.id("buyer_country"));
        country.sendKeys("BiH");

        WebElement phone = webDriver.findElement(By.id("buyer_phone"));
        phone.sendKeys("061705478");

        js.executeScript("window.scrollBy(0, 3000);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");

        List<WebElement> checkout = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"delivery-time\"]"))); // Replace with the actual class name
        assertFalse(checkout    .isEmpty(), "Product not added to the cart correctly.");

        js.executeScript("window.scrollBy(0, 3000);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);


        WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" //*[@id=\"checkout_step1_form\"]/div[4]/button"))); // Replace with the actual ID or locator
        checkoutBtn.click();

    }
}
