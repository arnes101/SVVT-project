import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Security {
    private static WebDriver webDriver;

    private static final String BASEURL = "https://pennyshop.ba";

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
    public void testForHttps() {
        webDriver.get(BASEURL + "/shop/penny-plus");
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https"), "Website should be using HTTPS");
    }


    @ParameterizedTest
    @CsvSource({
            "/user/profile",
            "/user/orders",
            "/user/wish-list/index",
            "/user/password",
            "/user/cart/notifications"
    })
    public void testProtectedRoutes(String protectedRoute) {
        webDriver.get(BASEURL + protectedRoute);
        String currentUrl = webDriver.getCurrentUrl();


        assertEquals(
                BASEURL + "/auth/login",
                currentUrl,
                "Unauthenticated users should be redirected to the login page."
        );
    }



}
