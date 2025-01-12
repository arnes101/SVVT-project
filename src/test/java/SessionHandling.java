import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class SessionHandling {
    private static WebDriver webDriver;


    @BeforeAll
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "D:/Downloads/chromedriver-win64/chromedriver.exe"); // Update the path
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @BeforeEach
    public void setup() {
        webDriver.manage().window().maximize();
        webDriver.get("https://pennyshop.ba/shop/penny-plus");

        closeModals();
    }

    @AfterEach
    public void cleanup() {
        webDriver.manage().deleteAllCookies();
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    private void closeModals() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
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
    }


    @Test
    public void testSessionDataClearedOnLogout() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        performLogin();


        WebElement logoutButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"dropdown_header_my_profile\"]")));
        logoutButton.click();

        Thread.sleep(1000);

        WebElement logoutButton2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[1]/div[2]/div[2]/div/div/a[6]")));
        logoutButton2.click();



        WebElement loginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_user")));
        assertNotNull(loginIcon, "Session data was not cleared upon logout.");


        webDriver.get("https://pennyshop.ba/user/profile");
        WebElement loginPage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/main/section/div/form")));
        assertNotNull(loginPage, "User was not redirected to the login page after logout.");
        System.out.println("Session data cleared on logout.");
    }

    @Test
    public void testMaintainSessionDuringNavigation() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        performLogin();


        WebElement categoryLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/div/div/div[1]/div/div[22]/div")));
        categoryLink.click();


        WebElement userIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdown_header_my_profile")));
        assertNotNull(userIcon, "Session was not maintained during navigation.");
        System.out.println("Session maintained during navigation.");
    }





    private void performLogin() {

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement loginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_user")));
        loginIcon.click();

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_mail")));
        email.sendKeys("amar.garibovic@stu.ibu.edu.ba"); // Replace with a valid email

        WebElement password = webDriver.findElement(By.id("user_pass"));
        password.sendKeys("stuibueduba"); // Replace with a valid password

        WebElement loginButton = webDriver.findElement(By.id("user_login"));
        loginButton.click();

        // Verify login success
        WebElement userIcon = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("dropdown_header_my_profile"))); // Adjust locator
        assertNotNull(userIcon, "Login failed.");
        System.out.println("Login successful.");
    }
}
