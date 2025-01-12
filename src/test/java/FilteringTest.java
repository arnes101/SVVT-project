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

public class FilteringTest {

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
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }





        WebElement categoryButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[2]/button")));
        categoryButton.click();
        Thread.sleep(1000);



        // Wait for search results to load and handle dynamic changes
        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/header/div")));
        assertFalse(searchResults.isEmpty(), "No search results found for a valid query.");

        WebElement category2Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/ul/li[2]/label")));
        category2Button.click();
        Thread.sleep(1000);

        List<WebElement> search2Results = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/header/div")));
        assertFalse(search2Results.isEmpty(), "No search results found for a valid query.");

        WebElement category3Button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/div/ul/li[2]/div/ul/li[2]/a")));
        category3Button.click();




        WebElement filterDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("headingFilterMobCategories")));
        filterDropdown.click();

        Thread.sleep(1000);

        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"collapseFilterMobCategories\"]/div/div[1]")));
        filterOption.click();

        Thread.sleep(1000);

        WebElement filterDropdown2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("headingFilterMobBrands")));
        filterDropdown2.click();

        WebElement filterDropdown3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"collapseFilterMobBrands\"]/div/div[37]")));
        filterDropdown3.click();

        Thread.sleep(1000);



        // Verify filtered results
        List<WebElement> filteredResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("pjax-container"))
        );
        assertFalse(filteredResults.isEmpty(), "No results found after applying the filter.");


        for (int i = 0; i < filteredResults.size(); i++) {
            // Re-locate each element dynamically
            WebElement product = filteredResults.get(i);

            // Verify product category or other attributes
            String productCategory = product.getText(); // Adjust this based on your actual requirements
            assertNotNull(productCategory, "Product category is null or not found.");
            assertEquals(
                    "MASTER\n" + "Ključ za gume X 17-19+21-23mm 17-19-21-23MM\n" + "11,95 KM\n" + "1\n" +
                            "+\n" + "-\n" + "DODAJ\n" + "MASTER\n" + "Ključ za gume 17-19+21-23mm 91401-2\n" + "16,95 KM\n" + "1\n" + "+\n" + "-\n" +
                            "DODAJ\n" + "INGCO TOOLS\n" + "Ključ francuski 150mm HADW131068\n" + "7,95 KM\n" + "1\n" + "+\n" + "-\n" + "DODAJ\n" + "INGCO TOOLS\n" +
                            "Uložak za valjak 100mm HRC3610016\n" + "0,50 KM\n" + "1\n" + "+\n" + "-\n" + "DODAJ\n" +
                            "INGCO TOOLS\n" + "Valjak sa ručkom 255mm HRHT092551\n" + "5,95 KM\n" + "1\n" + "+\n" + "-\n" + "DODAJ\n" +
                            "INGCO TOOLS\n" + "Žičana četka HKTWB2705\n" + "2,95 KM\n" + "1\n" + "+\n" + "-\n" +
                            "DODAJ\n" + "INGCO TOOLS\n" + "Stega štipaljka 110mm 12kg HQSC0204\n" + "1,50 KM\n" + "1\n" + "+\n" + "-\n" + "DODAJ\n" +
                            "INGCO TOOLS\n" + "Torba za alat HBP01028\n" + "34,95 KM\n" + "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Torba za alat HTBG10\n" +
                            "44,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Gedore set 27-1 1/4\" 14mm HKTS14271\n" +
                            "24,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Kliješta špicasta ravna 200mm HLNP08208\n" +
                            "6,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Kliješta špicasta zakrivljena 160mm HLNP08168\n" +
                            "4,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Kliješta sječice 180mm HHDCP08188\n" +
                            "6,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Kliješta sječice 160mm HDCP08168\n" +
                            "5,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Kliješta kombinerke 200mm 8\" HCP08208\n" +
                            "6,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Čekić 220g HRUH8908\n" +
                            "6,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Bitovi udarni S2 2/1 SDBIM71PH225\n" +
                            "1,50 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Borer 8x150mm DBM2110815\n" +
                            "2,50 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Borer 6x150mm DBM2110615\n" +
                            "2,20 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "INGCO TOOLS\n" +
                            "Borer stepenasti 10x45mm AKSDS10453\n" +
                            "34,95 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "DODAJ\n" +
                            "Prethodna\n" +
                            "1\n" +
                            "2\n" +
                            "3\n" +
                            "4\n" +
                            "5\n" +
                            "6\n" +
                            "7\n" +
                            "8\n" +
                            "...\n" +
                            "137\n" +
                            "138\n" +
                            "Naredna",productCategory, // Replace with the actual filter condition
                    "Product does not match the applied filter."
            );
        }
        System.out.println("Search functionality tests passed!");
    }

}