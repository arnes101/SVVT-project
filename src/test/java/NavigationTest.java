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

public class NavigationTest {
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
    public void testNavigation() throws InterruptedException {
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
        WebElement headerLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/div/div/div[1]/div/div[20]/div")));
        headerLink.click();
        Thread.sleep(1000);

        List<WebElement> results=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/div[5]/div/div[2]/div/div[1]")));
        assertFalse(results.isEmpty(),"can");
        for(int i=0;i<results.size();i++){
            WebElement element = results.get(i);
            String text= element.getText();
           assertNotNull(text,"Product category is null or not found.");
           assertEquals("MASTER\n" +
                   "Kanta za smeće 240l zelena GT-240H\n" +
                   "74,95 KM\n" +
                   "1\n" +
                   "+\n" +
                   "-\n" +
                   "DODAJ",text,"Product category is null or not found.");
        }

        WebElement link2= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" /html/body/header/nav[2]/div/div/div[1]/div/div[23]/div/a")));
        link2.click();

        Thread.sleep(1000);


        List<WebElement> results2=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/div[5]/div/div[2]/div/div[1]")));
        assertFalse(results2.isEmpty(),"can");

        for(int i=0;i<results2.size();i++){
            WebElement element = results2.get(i);
            String text= element.getText();
            assertNotNull(text,"Product category is null or not found.");
            assertEquals("MASTER\n" +
                    "Igračka set sportskih lopti W717\n" +
                    "4,95 KM\n" +
                    "1\n" +
                    "+\n" +
                    "-\n" +
                    "DODAJ",text,"Product category is null or not found.");
        }
        //Test case home verification
        WebElement home= wait.until(ExpectedConditions.elementToBeClickable(By.xpath(" /html/body/header/nav[1]/div[1]/a")));
        home.click();

        List<WebElement> results3=wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/div[2]/section")));
        assertFalse(results3.isEmpty(),"can");

        for(int i=0;i<results3.size();i++){
            WebElement element = results3.get(i);
            String text= element.getText();
            assertNotNull(text,"Product category is null or not found.");
            assertEquals("Veliko sniženje igračaka\n" +
                    "Samo na PENNYshop\n" +
                    "Provjerite ponudu u\n" +
                    "West Gate Retail parku!\n" +
                    "Provjerite posebnu ponudu\n" +
                    "Izdvajamo TOP 5 ARTIKALA!",text,"Product category is null or not found.");
        }


    }
}