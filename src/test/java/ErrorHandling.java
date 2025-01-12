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

public class ErrorHandling {


    //Test case 1 no input to login field
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
    public void testLoginFunctionality() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");
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
        //test case 1 no inputs for login FAILED
        WebElement loginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-block")));
        loginIcon.click();



        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
        loginButton.click();

        List<WebElement> noInputMessage = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/header/nav[1]/div[2]/div[2]/div/div/form")));
        assertFalse(noInputMessage.isEmpty(), "Exception while logging in");
        for (int i = 0; i < noInputMessage.size(); i++) {
            // Re-locate each element dynamically
            WebElement product = noInputMessage.get(i);

            // Verify product category or other attributes
            String productCategory = product.getText(); // Adjust this based on your actual requirements
            assertNotNull(productCategory, "Product category is null or not found.");
            assertEquals(
                    "No input provided. Please enter a valid email and password",productCategory, // Replace with the actual filter condition
                    "Product does not match the applied filter."
            );
        }

        List<WebElement> mainscreen = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main")));
        assertFalse(mainscreen.isEmpty(), "Exception while logging in");


    }
    @Test
    public void testInvalidEmailLogin() {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        WebElement loginIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-block")));
        loginIcon.click();

        WebElement email = webDriver.findElement(By.id("user_mail"));
        email.sendKeys("invalid-email-without-at-sign");

        WebElement password = webDriver.findElement(By.id("user_pass"));
        password.sendKeys("projekat1234");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
        loginButton.click();

        List<WebElement> noAtEmail = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/header/nav[1]/div[2]/div[2]/div/div/form")));
        assertFalse(noAtEmail.isEmpty(), "Error message not displayed for invalid email.");

        String errorMessage = noAtEmail.get(0).getText();
        assertEquals("Invalid email. Please provide a valid email", errorMessage, "Incorrect error message for invalid email.");
    }
    @Test
    public void rrorHandling2() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");


        Thread.sleep(2000);





        Thread.sleep(1000);
        WebElement profile = webDriver.findElement(By.xpath("/html/body/header/nav[1]/div[2]/div[2]/div/a"));
        profile.click();

        WebElement email = webDriver.findElement(By.id("user_mail"));
        email.sendKeys("amar.garibovic@stu.ibu.edu.ba");

        WebElement pass = webDriver.findElement(By.id("user_pass"));
        pass.sendKeys("stuibueduba");

        WebElement prijava = webDriver.findElement(By.id("user_login"));
        prijava.click();
        Thread.sleep(1000);
        WebElement profile2 = webDriver.findElement(By.xpath("/html/body/header/nav[1]/div[2]/div[2]/div/a"));
        profile2.click();

        WebElement editProfile = webDriver.findElement(By.xpath("//a[text()='Uredi profil']"));
        editProfile.click();

        Thread.sleep(2000);
        WebElement fname = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[1]/input"));
        fname.clear();
        fname.sendKeys("");

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1000);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);


        WebElement lname = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[2]/input"));
        lname.clear();
        lname.sendKeys("");

        WebElement company = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[3]/input"));
        company.clear();
        company.sendKeys("");


        WebElement adress = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[4]/input"));
        adress.clear();
        adress.sendKeys("");

        WebElement city = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[5]/input"));
        city.clear();
        city.sendKeys("");

        WebElement zip = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[6]/input"));
        zip.clear();
        zip.sendKeys("");
        WebElement country = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[7]/input"));
        country.clear();
        country.sendKeys("");
        WebElement mobitel = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[8]/div/input"));
        mobitel.clear();
        mobitel.sendKeys("");




        WebElement saveChanges = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[2]/button"));
        saveChanges.click();

        WebElement errorMsg=webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[4]/div"));
        assertEquals("Molimo vas unesite adresu.",errorMsg.getText());

    }
}
