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

public class ProductDisplayTest {

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
    public void testProductDisplay() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));


        try {
            WebElement modalCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".modal-dialog .close")));
            modalCloseButton.click();
            System.out.println("Modal dialog closed successfully.");
        } catch (TimeoutException e) {
            System.out.println("No modal dialog displayed, proceeding with the test...");
        }
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/main")));

        assertFalse(products.isEmpty(), "No products found on the page.");

        for (WebElement product : products) {

            String textPage=product.getText();
            assertNotNull(textPage, "Product category is null or not found.");
            assertEquals(
                    "Sve što želiš, na klik!\n" +
                            "Pametan šoping za pametne telefone\n" +
                            "Više detalja\n" +
                            "Veliko sniženje igračaka\n" +
                            "Samo na PENNYshop\n" +
                            "Provjerite ponudu u\n" +
                            "West Gate Retail parku!\n" +
                            "Provjerite posebnu ponudu\n" +
                            "Izdvajamo TOP 5 ARTIKALA!",textPage,
                    "Product does not match the applied filter."
            );
        }





        List<WebElement> products2 = webDriver.findElements(By.xpath("/html/body/main/section[2]/div/div/div/div/div[1]/div/div[1]/div/a"));

        assertFalse(products2.isEmpty(), "No products found on the page.");
        for (WebElement product2 : products) {

            String textPage=product2.getText();
            assertNotNull(textPage, "Product category is null or not found.");
            assertEquals(
                    "Sve što želiš, na klik!\n" +
                            "Pametan šoping za pametne telefone\n" +
                            "Više detalja\n" +
                            "Veliko sniženje igračaka\n" +
                            "Samo na PENNYshop\n" +
                            "Provjerite ponudu u\n" +
                            "West Gate Retail parku!\n" +
                            "Provjerite posebnu ponudu\n" +
                            "Izdvajamo TOP 5 ARTIKALA!\n" +
                            "Aktuelne akcije\n" +
                            "MASTER PROFESSIONAL\n" +
                            "Klima zračna zavjesa 1800x190x160mm HFM-1218Q\n" +
                            "269,90 KM\n" +
                            "MASTER PROFESSIONAL\n" +
                            "Klima zračna zavjesa 1500x190x160mm HFM-1215Q\n" +
                            "229,90 KM\n" +
                            "MASTER PROFESSIONAL\n" +
                            "Klima zračna zavjesa 1200x190x160mm HFM-1212Q\n" +
                            "189,90 KM\n" +
                            "MASTER\n" +
                            "Mašina za sušenje veša 8kg DC80S5C\n" +
                            "649,90 KM\n" +
                            "MASTER\n" +
                            "Mašina za pranje-sušenje veša 10kg XQG100DT614WVE\n" +
                            "819,90 KM\n" +
                            "MASTER\n" +
                            "Mašina za pranje veša 7kg XQG70U412VE\n" +
                            "599,90 KM\n" +
                            "KARCHER\n" +
                            "Usisivač VC3\n" +
                            "359,95 KM\n" +
                            "Previous\n" +
                            "Next\n" +
                            "Savršeno svjetlo za savršen trenutak\n" +
                            "Sjaj u svakom kutku vašeg doma\n" +
                            "Novo u ponudi\n" +
                            "MASTER\n" +
                            "Grijalica kamin plinski H180cm 11KW FSD-H1\n" +
                            "449,95 KM\n" +
                            "MASTER\n" +
                            "Grijalica baštanska plinska H180cm 13KW FSD-E\n" +
                            "399,95 KM\n" +
                            "MASTER\n" +
                            "Grijalica baštanska plinska H136cm 13KW FSD-D\n" +
                            "299,95 KM\n" +
                            "MASTER\n" +
                            "Grijalica baštanska plinska H221cm 13KW FSD-A\n" +
                            "249,95 KM\n" +
                            "Akcija\n" +
                            "MASTER\n" +
                            "Laminat 8mm Mango Carbon MV8112\n" +
                            "9,75 KM\n" +
                            "EDEKA\n" +
                            "Medeno srce punjeno kajsijom preliveno čokoladom 150g\n" +
                            "1,95 KM\n" +
                            "REWE\n" +
                            "Medeno srce punjeno kajsijom preliveno čokoladom 150g\n" +
                            "1,95 KM\n" +
                            "Previous\n" +
                            "Next\n" +
                            "Izdvojeno iz ponude\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Dostupno\n" +
                            "već od\n" +
                            "72.90 KM\n" +
                            "Aparat za kafu\n" +
                            "Electrolux\n" +
                            "Dostupno\n" +
                            "već od\n" +
                            "13,90 KM\n" +
                            "Police žičane\n" +
                            "Master\n" +
                            "Dostupno\n" +
                            "već od\n" +
                            "4,50 KM\n" +
                            "Posuđe\n" +
                            "WMF\n" +
                            "Dostupno\n" +
                            "već od\n" +
                            "11,95 KM\n" +
                            "Elektro mašine\n" +
                            "Ingco Tools\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "\n" +
                            "Posebne ponude\n" +
                            "Unesite svježinu u svaki kutak doma\n" +
                            "Stotine novih rashladnih proizvoda.\n" +
                            "Sve za vaše mališane\n" +
                            "Uštedite na opremi i igračkama za djecu\n" +
                            "Vaš vrt Vaša priča\n" +
                            "Velika akcija i bogata ponuda za vrtni asortiman\n" +
                            "Popularne kategorije\n" +
                            "Alati i mašine\n" +
                            "Tekstil i galanterija\n" +
                            "Elektromaterijal\n" +
                            "Oprema i igračke za djecu\n" +
                            "Posuđe\n" +
                            "Vrtni program\n" +
                            "Kućanski aparati i bijela tehnika\n" +
                            "Boje i lakovi\n" +
                            "Sanitarna oprema\n" +
                            "Auto kozmetika\n" +
                            "Sport\n" +
                            "Grijanje\n" +
                            "Vodomaterijal\n" +
                            "Prikaži sve kategorije\n" +
                            "Novosti i savjeti za Vas\n" +
                            "Saznaj više\n" +
                            "Penny plus 12/24\n" +
                            "Preuzimanje kataloga",textPage,
                    "Product does not match the applied filter."
            );
        }



        List<WebElement> footer = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/footer/div[2]/div/div")));

        assertFalse(footer.isEmpty(), "No products found on the page.");

        for (WebElement product : footer) {

            String textPage=product.getText();
            assertNotNull(textPage, "Product category is null or not found.");
            assertEquals(
                    "PENNYshop.ba Newsletter\n" +
                            "Prijavom pristajete da vam povremeno šaljemo akcijske cijene i novitete iz naše ponude. Možete se odjaviti u bilo kojem trenutku. Informacije o načinu korištenja ličnih podataka dostupne su uslovima korištenja.\n" +
                            "Kupovina\n" +
                            "Česta pitanja\n" +
                            "Registracija\n" +
                            "Kako da naručite?\n" +
                            "Načini plaćanja\n" +
                            "Sigurnost\n" +
                            "Uslovi korištenja\n" +
                            "Servisi\n" +
                            "Sigurno plaćanje\n" +
                            "Garancija kvalitete\n" +
                            "Reklamacije i povrat\n" +
                            "Dostava\n" +
                            "Pošaljite nam svoj spisak\n" +
                            "Informacije\n" +
                            "O nama\n" +
                            "Kategorije\n" +
                            "Novo u ponudi\n" +
                            "Prodajni centri\n" +
                            "Novosti\n" +
                            "Katalozi\n" +
                            "Povežite se\n" +
                            "Facebook\n" +
                            "Linkedin\n" +
                            "Twitter\n" +
                            "Instagram\n" +
                            "Goolge Play\n" +
                            "Apple App Store",textPage,
                    "Product does not match the applied filter."
            );
        }

        System.out.println("Footer link tests passed!");

        System.out.println("Product display tests passed!");



        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1000);");
        System.out.println("Scrolled down");
        Thread.sleep(1000);

        WebElement left= webDriver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/div/div[2]/button[1]"));
        WebElement right= webDriver.findElement(By.xpath("/html/body/main/section[2]/div/div/div/div/div[2]/button[2]"));
        right.click();
        Thread.sleep(1000);
        right.click();

        Thread.sleep(1000);
        right.click();
        Thread.sleep(1000);
        left.click();
        Thread.sleep(1000);
        left.click();
        Thread.sleep(1000);
        left.click();




    }

}
