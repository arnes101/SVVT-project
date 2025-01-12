package org.example.question3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserSettings {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:/Downloads/chromedriver-win64/chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        webDriver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void question3() throws InterruptedException {
        webDriver.get("https://pennyshop.ba/shop/penny-plus");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));





        WebElement closenot = webDriver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]"));
        closenot.click();
        Thread.sleep(1000);
        WebElement cookie = webDriver.findElement(By.xpath("/html/body/div[4]/a"));
        cookie.click();

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
        fname.sendKeys("Amar");

        WebElement lname = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[2]/input"));
        lname.clear();
        lname.sendKeys("Garibovic");

        WebElement company = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[3]/input"));
        company.clear();
        company.sendKeys("Testing");


        WebElement adress = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[4]/input"));
        adress.clear();
        adress.sendKeys("Testing");

        WebElement city = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[5]/input"));
        city.clear();
        city.sendKeys("Testing");

        WebElement zip = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[6]/input"));
        zip.clear();
        zip.sendKeys("71000");
        WebElement country = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[7]/input"));
        country.clear();
        country.sendKeys("Bosna i Hercegovina");
        WebElement mobitel = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[1]/div[8]/div/input"));
        mobitel.clear();
        mobitel.sendKeys("062842251");

        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 1500);"); // Scroll down 1000 pixels
        System.out.println("Scrolled down");
        Thread.sleep(1000);


        WebElement saveChanges = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/form/div[2]/button"));
        saveChanges.click();
        Thread.sleep(2000);
        WebElement messageSucc = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[2]/div"));
        assertEquals("Uspjeh! Korisnički nalog je uspješno ažuriran.", messageSucc.getText());


        Thread.sleep(2000);

        //Test case 2 changing the password
        WebElement resetPassword = webDriver.findElement(By.xpath("/html/body/main/div[3]/div/div/div[1]/ul/li[4]"));
        resetPassword.click();

        WebElement password = webDriver.findElement(By.id("form-control-edit-password"));
        password.sendKeys("projekat123");

        WebElement password2 = webDriver.findElement(By.id("form-control-edit-password-confirm"));
        password2.sendKeys("projekat1234");




    }


}