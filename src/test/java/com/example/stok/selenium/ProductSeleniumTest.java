package com.example.stok.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {

        // 🔴 CI ortamında Selenium testlerini SKIP et
        Assumptions.assumeTrue(
                !Boolean.getBoolean("skip.selenium"),
                "Selenium testleri CI ortamında kapalı"
        );

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.get("http://localhost:8081");
    }

    // 1️⃣ ÜRÜN EKLEME
    @Test
    @Order(1)
    void urunEklemeCalisiyorMu() throws InterruptedException {

        driver.findElement(By.name("name")).sendKeys("Selenium Ürün");
        driver.findElement(By.name("stock")).sendKeys("5");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(1000);

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Selenium Ürün"));
    }

    // 2️⃣ ÜRÜN LİSTELEME
    @Test
    @Order(2)
    void urunListelemeCalisiyorMu() {

        String pageSource = driver.getPageSource();
        assertTrue(pageSource.contains("Selenium Ürün"));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
