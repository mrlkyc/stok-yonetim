package com.example.stok.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductSeleniumTest {

    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
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
