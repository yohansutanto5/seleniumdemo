package com.dbs;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.dbs.model.SystemContext;
import com.dbs.pages.Login;
import com.dbs.utils.WebDriverFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginTests {
    private WebDriver driver = null;
    private WebDriverWait wait = null;
    private SystemContext ctx = null;

    // Initiating driver and context
    @BeforeTest
    public void setUp() {
        ctx = new SystemContext("/home/yohan/portfolio/seleniumdemo/resources/application.properties");
        driver = WebDriverFactory.getDriver(ctx);
        wait = WebDriverFactory.getWebDriverWait();
    }

    // Happy flow. Expected success Login
    @Test
    public void testSuccessfulLogin() {
        Login loginPage = new Login();
        loginPage.OpenLoginPage(wait, driver);
        loginPage.setUsernameWithWait("tomsmith");
        loginPage.setPasswordWithWait("SuperSecretPassword!");
        loginPage.clickLogin();
        WebElement result = loginPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertTrue(result.getText().contains("You logged into a secure area!"), "Alert text is incorrect!");
    }

    // Negative Flow. Expected Login Failed due to wrong username and password
    @Test
    public void testFailedLogin() {
        Login loginPage = new Login();
        loginPage.OpenLoginPage(wait, driver);
        loginPage.setUsernameWithWait("tomsmaasith");
        loginPage.setPasswordWithWait("SuperSecretPassword!");
        loginPage.clickLogin();
        WebElement result = loginPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertFalse(result.getText().contains("You logged into a secure area!"), "Alert text is incorrect!");
    }

    // Negative Flow. Expected Login Failed due to wrong password
    @Test
    public void testFailedLoginWrongPassword() {
        Login loginPage = new Login();
        loginPage.OpenLoginPage(wait, driver);
        loginPage.setUsernameWithWait("tomsmith");
        loginPage.setPasswordWithWait("SuperSecretPcascasassword!");
        loginPage.clickLogin();
        WebElement result = loginPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertFalse(result.getText().contains("You logged into a secure area!"), "Alert text is incorrect!");
    }

    // Negative Flow. Expected Login Failed due to wrong username
    @Test
    public void testFailedLoginWrongUsername() {
        Login loginPage = new Login();
        loginPage.OpenLoginPage(wait, driver);
        loginPage.setUsernameWithWait("tomsmith");
        loginPage.setPasswordWithWait("SuperSecretPcascasassword!");
        loginPage.clickLogin();
        WebElement result = loginPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertFalse(result.getText().contains("You logged into a secure area!"), "Alert text is incorrect!");
    }

    // Batch testing using external data source
    // May trigger error due to DBS extension
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void testLoginUsingTestDataSource() {
        try {
            // Create ObjectMapper instance
            Login loginPage = new Login();
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File(ctx.getTestDatadir()+"/login.json");
            // Read JSON file and convert to HashMap
            List<Map<String, Object>> list = objectMapper.readValue(jsonFile,
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            // Print the List of Maps
            for (Map<String, Object> item : list) {
                String uname = (String) item.get("username");
                String pwd = (String) item.get("password");
                Boolean expected = (Boolean) item.get("expected");
                loginPage.OpenLoginPage(wait, driver);
                loginPage.setUsernameWithWait(uname);
                loginPage.setPasswordWithWait(pwd);
                loginPage.clickLogin();
                WebElement result = loginPage.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
                assertEquals(result.getText().contains("You logged into a secure area!"), expected);
                loginPage.clickLogout();
                loginPage.clickLogout();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void cleanUp() {
        driver.quit();
    }
}