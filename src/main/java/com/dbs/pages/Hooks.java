package com.dbs.pages;

import org.openqa.selenium.WebDriver;

import com.dbs.model.SystemContext;
import com.dbs.utils.WebDriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    
    private WebDriver driver;

    @Before
    public void setUp() {
        SystemContext ctx = new SystemContext("./resources/application.properties");
        driver = WebDriverFactory.getDriver(ctx);
    }

    @After
    public void tearDown() {
        WebDriverFactory.quitDriver();
    }
}
