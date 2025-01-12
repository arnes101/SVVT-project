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
public class WhislistFunctionality {
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
    public void carTest() throws InterruptedException {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get("https://pennyshop.ba/shop/penny-plus"); // Replace with the actual URL
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
        //test case correct login
        WebElement loginIcon=wait.until(ExpectedConditions.elementToBeClickable(By.id("login-block")));
        loginIcon.click();



        WebElement emailLogin = webDriver.findElement(By.id("user_mail"));
        emailLogin.sendKeys("amar.garibovic@stu.ibu.edu.ba");

        WebElement passwordLogin = webDriver.findElement(By.id("user_pass"));
        passwordLogin.sendKeys("stuibueduba");

        WebElement loginButton=wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
        loginButton.click();

        List<WebElement>  mainscreen=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main")));
        assertFalse(mainscreen.isEmpty(),"Exception while logging in");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1500);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        WebElement hoverDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section[4]/div/div/div/div/div[1]/div/div[1]/div/a")));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverDiv).perform();

        WebElement productSelection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/section[4]/div/div/div/div/div[1]/div/div[1]/div/div/div/div"))); // Adjust selector
        productSelection.click();
        Thread.sleep(1000);

        js.executeScript("window.scrollBy(0, -2000);");
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        WebElement userIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_my_profile")));
        userIcon.click();

        WebElement whishlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/div[2]/div[2]/div/div/a[3]"))); // Adjust selector
        whishlist.click();

        js.executeScript("window.scrollBy(0, 500);");
        System.out.println("Scrolled down");
        Thread.sleep(1000);


       List<WebElement>  removeW = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/div[3]/div/div/div[2]/div/div/div[2]/div/div[2]/span[2]")));
        assertFalse(removeW.isEmpty(),"no items added to whishlist");

        WebElement removeWhishlist = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" /html/body/main/div[3]/div/div/div[2]/div/div/div[2]/div/div[2]/span[2]")));
        removeWhishlist.click();

        // Test Case 1: Add a single product to the cart
    }
}
