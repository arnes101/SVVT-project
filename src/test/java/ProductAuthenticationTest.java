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

public class ProductAuthenticationTest {
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
    public void productTest() throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        webDriver.get("https://pennyshop.ba/shop/penny-plus");
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
        //Test header link and product relevancy

        WebElement headerLink= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/div/div/div[1]/div/div[20]/div")));
        headerLink.click();
        Thread.sleep(1000);

        WebElement productLink= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pjax-container\"]/div[1]/a")));
        productLink.click();

        Thread.sleep(1000);

        List<WebElement> filteredResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(" //*[@id=\"opis\"]"))
        );
        assertFalse(filteredResults.isEmpty(), "No results found after applying the filter.");
        Thread.sleep(1000);

        for (int i = 0; i < filteredResults.size(); i++) {

            WebElement product = filteredResults.get(i);


            String productCategory = product.getText();
            assertNotNull(productCategory, "Product category is null or not found.");
            assertEquals(
                    "Brand\n" +
                            "MASTER\n" +
                            "Bar kod\n" +
                            "3873515122157\n" +
                            "Tip proizvoda\n" +
                            "Kanta za smeće\n" +
                            "Model\n" +
                            "GT-240H\n" +
                            "Boja\n" +
                            "Zelena\n" +
                            "Zapremina\n" +
                            "240 l",productCategory,
                    "Product does not match the applied filter."
            );
        }

        //Test case 2 does the quick view description match the product

        WebElement headerLink2= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/div/div/div[1]/div/div[20]/div")));
        headerLink2.click();
        Thread.sleep(1000);

        WebElement hoverDiv = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"pjax-container\"]/div[1]/a")));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverDiv).perform();

        WebElement quickview= wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pjax-container\"]/div[1]/div[1]/div/button")));
        quickview.click();
        Thread.sleep(1000);

        List<WebElement> filterResults2 = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("productTabContent")));
        assertFalse(filterResults2.isEmpty()," Quick view not loaded correctly");
        for(int i=0;i<filterResults2.size();i++){
            WebElement prod=filterResults2.get(i);

            String text =prod.getText();
            assertNotNull(text, "Product category is null or not found.");
            assertEquals(
                    "Brand\n" +
                            "MASTER\n" +
                            "Bar kod\n" +
                            "3873515122157\n" +
                            "Tip proizvoda\n" +
                            "Kanta za smeće\n" +
                            "Model\n" +
                            "GT-240H\n" +
                            "Boja\n" +
                            "Zelena\n" +
                            "Zapremina\n" +
                            "240 l",text, // Replace with the actual filter condition
                    "Product does not match the applied filter."
            );

        }





    }
}
