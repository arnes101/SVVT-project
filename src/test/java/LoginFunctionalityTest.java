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

public class LoginFunctionalityTest {
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
    public void testLoginFunctionality() throws InterruptedException {
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
        //Test case 2 incorrect credentials

        WebElement userIcon=wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_my_profile")));
        userIcon.click();

        WebElement logOut=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"top\"]/div[2]/div[2]/div/div/a[6]")));
        logOut.click();

        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")));
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }

        WebElement user2Icon=wait.until(ExpectedConditions.elementToBeClickable(By.id("dropdown_header_user")));
        user2Icon.click();

        WebElement email2Login = webDriver.findElement(By.name("email"));
        email2Login.sendKeys("amar.x@stu.ibu.edu.ba");

        WebElement password2Login = webDriver.findElement(By.id("user_pass"));
        password2Login.sendKeys("stuibueduba");
        Thread.sleep(1000);

        WebElement user2Button=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"user_login\"]" )));
        user2Button.click();


        //test case 3 new password

        List<WebElement > loginScreen=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/section/div/form/div/div[1]/button")));
        assertFalse(mainscreen.isEmpty(),"Exception while logging in");

        WebElement forgotPassword=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_user_register_form\"]/div/div[1]/a")));
        forgotPassword.click();

        WebElement forgotPassword2 = webDriver.findElement(By.id("user_mail_register"));
        forgotPassword2.sendKeys("amar.garibovic@stu.ibu.edu.ba");

        WebElement newPassBtn=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_user_register_form\"]/button")));
        newPassBtn.click();



    }
}
