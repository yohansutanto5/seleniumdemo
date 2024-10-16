package com.dbs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector("#login button");
    private final By logoutButton = By.xpath("/html/body/div[2]/div/div/a");

    public WebDriverWait wait;
    public WebDriver driver;
    
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