package com.dbs.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dbs.model.SystemContext;

public class WebDriverFactory {

    private static WebDriver driver;

    @SuppressWarnings("CallToPrintStackTrace")
    public static WebDriver getDriver(SystemContext ctx) {
        // System.setProperty(ctx.getDriver(), ctx.getDriverdir());
        if (driver == null) {
            switch (ctx.getDriver().toLowerCase()) {
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                case "chrome" -> driver = new ChromeDriver();
                case "remote" -> {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--headless");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--disable-dev-shm-usage");
                    options.addArguments("--disable-gpu");
                    // Initialize WebDriverm
                    driver = new ChromeDriver(options);
                }
                default -> driver = new ChromeDriver();
            }
        }
        return driver;
    }

    public static WebDriverWait getWebDriverWait() {
        return new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
