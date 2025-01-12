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

public class SearchFunctionalityTest {

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
    public void testSearchFunctionality() throws InterruptedException {
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


        WebElement searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete")));
        assertNotNull(searchBar, "Search bar is not present on the page.");

        // Test Case 1: Valid Search Query
        String validQuery = "lampa";
        searchBar.clear();
        searchBar.sendKeys(validQuery);


        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[1]/div[1]/div/form/div/button")));
        searchButton.click();




        List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main/div[5]/div/div[2]/div/div[1]")));
        assertFalse(searchResults.isEmpty(), "No search results found for a valid query.");


        for (WebElement product : searchResults) {
            String productText = product.getText().toLowerCase();
            assertTrue(productText.contains(validQuery.toLowerCase()), "Search result does not match the query: " + validQuery);
        }

        // Test Case 2: Invalid Search Query
        String invalidQuery = "@#$%^";
        searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete")));
        searchBar.clear();
        searchBar.sendKeys(invalidQuery);


        searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[1]/div[1]/div/form/div/button")));
        searchButton.click();




        WebElement noResultsMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("no_results")));
        assertNotNull(noResultsMessage, "No 'no results' message found for invalid search query.");
        assertTrue(noResultsMessage.getText().contains("Trenutno nema proizvoda u ovoj kategoriji!"), "Incorrect or missing message for invalid search query.");


        searchBar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("autocomplete")));
        searchBar.clear();
        searchBar.sendKeys("kamera");


        searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/header/nav[1]/div[1]/div/form/div/button")));
        searchButton.click();


        WebElement filterDropdown = wait.until(ExpectedConditions.elementToBeClickable(By.id("headingFilterMobBrands")));
        filterDropdown.click();

        WebElement filterOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[5]/div/div[1]/div/div[1]/div[2]/div/div[1]/label"))); // Adjust text
        filterOption.click();
        Thread.sleep(1000);

        List<WebElement> filteredResults = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[@id=\"pjax-container\"]/div[1]"))
        );
        assertFalse(filteredResults.isEmpty(), "No results found after applying the filter.");


        for (int i = 0; i < filteredResults.size(); i++) {

            WebElement product = filteredResults.get(i);


            String productCategory = product.getText();
            assertNotNull(productCategory, "Product category is null or not found.");
            assertEquals(
                    "GRUNDIG\n" +
                            "Web kamera 5mpx 72818\n" +
                            "9,90 KM\n" +
                            "1\n" +
                            "+\n" +
                            "-\n" +
                            "RASPRODANO",productCategory,
                    "Product does not match the applied filter."
            );
        }
    }

}