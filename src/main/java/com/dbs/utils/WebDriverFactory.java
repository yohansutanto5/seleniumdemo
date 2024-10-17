package com.dbs.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dbs.model.SystemContext;

public class WebDriverFactory {

    private static WebDriver driver;

    @SuppressWarnings("CallToPrintStackTrace")
    public static WebDriver getDriver(SystemContext ctx) {
        System.setProperty(ctx.getDriver(), ctx.getDriverdir());
        if (driver == null) {
            switch (ctx.getDriver().toLowerCase()) {
                case "firefox" -> driver = new FirefoxDriver();
                case "edge" -> driver = new EdgeDriver();
                case "chrome" -> driver = new ChromeDriver();
                case "remote" -> {
                    try {
                        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), new ChromeOptions());
                    } catch (MalformedURLException e) {
                        System.out.println("Remote URL is incorrect");
                    }
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
