
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
public class RegistrationFunctionality {
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
    public void registrationFunctionality() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));

        // Handle Modal Dialog (if present)
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
            System.out.println("Coockies closed.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        //Test case 1    PASSED
        WebElement loginIcon=wait.until(ExpectedConditions.elementToBeClickable(By.id("login-block")));
        loginIcon.click();

        Thread.sleep(1000);

        WebElement registratrion=wait.until(ExpectedConditions.elementToBeClickable(By.id("register-btn")));
        registratrion.click();

        Thread.sleep(1000);
        WebElement email = webDriver.findElement(By.id("user_mail_register"));
        email.sendKeys("arnesrastoder@gmail.com");

        WebElement pass = webDriver.findElement(By.id("user_pass_register"));
        pass.sendKeys("project123");

        WebElement pass2 = webDriver.findElement(By.id("user_pass_confirm_register"));
        pass2.sendKeys("project123");

        WebElement regbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_user_register_form\"]/div/div[2]/button")));
        regbtn.click();
        Thread.sleep(1000);
        //Test 3 empty values PASSED
        WebElement email3 = webDriver.findElement(By.id("user_mail_register"));
        email3.clear();

        WebElement pass5 = webDriver.findElement(By.id("user_pass_register"));
        pass5.clear();

        WebElement pass6 = webDriver.findElement(By.id("user_pass_confirm_register"));
        pass6.clear();




        WebElement regbtn3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_user_register_form\"]/div/div[2]/button")));
        regbtn3.click();

        List<WebElement> filteredResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"new_user_register_form\"]/div/div[2]"))
        );
        assertFalse(filteredResults.isEmpty(), "No results found after applying the filter.");


        for (int i = 0; i < filteredResults.size(); i++) {

            WebElement product = filteredResults.get(i);


            String productCategory = product.getText();
            assertNotNull(productCategory, "Product category is null or not found.");
            assertEquals(
                    "E-mail adresa\n" +
                            "Molimo unesite ispravan email.\n" +
                            "Šifra\n" +
                            "Ovo polje je obavezno.\n" +
                            "Šifra (Potvrda)\n" +
                            "Ovo polje je obavezno.\n" +
                            "Pročitao/la sam\n" +
                            "uslove korištenja\n" +
                            "i slažem se.\n" +
                            "Kreiraj novi nalog",productCategory,
                    "Product does not match the applied filter."
            );
        }
        Thread.sleep(3000);
        //test case 2 email without @   passed

        WebElement email2 = webDriver.findElement(By.id("user_mail_register"));
        email2.clear();
        email2.sendKeys("arnesrastodergmail.com");

        WebElement pass3 = webDriver.findElement(By.id("user_pass_register"));
        pass3.sendKeys("project123");

        WebElement pass4 = webDriver.findElement(By.id("user_pass_confirm_register"));
        pass4.sendKeys("project123");


        WebElement regbtn2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"new_user_register_form\"]/div/div[2]/button")));
        regbtn2.click();
        Thread.sleep(2000);











    }
}
