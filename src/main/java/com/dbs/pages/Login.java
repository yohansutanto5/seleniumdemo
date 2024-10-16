package com.dbs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.dbs.model.SystemContext;
import com.dbs.utils.WebDriverFactory;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Login {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login button");
    private final By logoutButton = By.xpath("/html/body/div[2]/div/div/a");
    private final By flash = By.id("flash");

    public WebDriverWait wait;
    public WebDriver driver;
    
    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        SystemContext ctx = new SystemContext("./resources/application.properties");
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = WebDriverFactory.getDriver(ctx);
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @When("the user enters valid credentials")
    public void theUserEntersValidCredentials() {
        this.driver.findElement(usernameField).click();
        this.driver.findElement(usernameField).sendKeys("tomsmith");
        this.driver.findElement(passwordField).click();
        this.driver.findElement(passwordField).sendKeys("SuperSecretPassword!");
        this.driver.findElement(loginButton).click();
    }

    @When("the user enters invalid credentials")
    public void theUserEntersInvalidCredentials() {
        this.driver.findElement(usernameField).click();
        this.driver.findElement(usernameField).sendKeys("tomsmafafith");
        this.driver.findElement(passwordField).click();
        this.driver.findElement(passwordField).sendKeys("SuperSecretfasfPassword!");
        this.driver.findElement(loginButton).click();
    }

    @Then("the user should be redirected to the secure page")
    public void theUserShouldBeRedirectedToSecurePage() {
        if (driver.getCurrentUrl().contains("/secure")) {
            System.out.println("Success Login");
        } else {
            System.out.println("Failed Login");
        }
        driver.quit();
    }

    @Then("an error message is displayed")
    public void anErrorMessageIsDisplayed() {
        if (driver.findElement(flash).getText().contains("Your username is invalid!")){
            System.out.println("Unsuccessful Login");
        }
        driver.quit();
    }

    public void OpenLoginPage(WebDriverWait wait,WebDriver driver){
        this.driver = driver;
        this.wait = wait;
        driver.get("https://the-internet.herokuapp.com/login");
    }

    public void setUsername(String username){
        this.driver.findElement(usernameField).sendKeys(username);
    }

    public void setUsernameWithWait(String username){
        WebElement elem = null;
        try {
            elem = wait.until(ExpectedConditions.visibilityOfElementLocated(this.usernameField));
        } catch (Exception e) {
            System.out.print(e);
        } 
        if (elem != null) {
            elem.click();
            setUsername(username);
        }
    }

    public void setPasswordWithWait(String password){
        WebElement elem = null;
        try {
            elem = wait.until(ExpectedConditions.visibilityOfElementLocated(this.passwordField));
        } catch (Exception e) {
            System.out.print(e);
        } 
        if (elem != null) {
            elem.click();
            setPasswordField(password);
        }
    }

    public void setPasswordField(String password){
        this.driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin(){
        this.driver.findElement(loginButton).click();
    }
    public void clickLogout(){
        try {
            WebElement elem = this.driver.findElement(logoutButton);
            elem.click();
        } catch (NoSuchElementException e) {
            // Element not found, do nothing
            // Optionally, you can log this event
            System.out.println("Logout button not found. No action taken.");
        }
    }
}