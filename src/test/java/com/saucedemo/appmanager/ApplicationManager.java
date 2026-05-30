package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.fail;



public class ApplicationManager {

    WebDriver driver;

    private NavigationHelper navigationHelper ;
    JavascriptExecutor js;
    private String baseUrl;
    //private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public void start() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://www.saucedemo.com/";
        navigationHelper = new NavigationHelper(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    public void login(String user, String password) {
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public NavigationHelper getNavigationHaloer() {
        return navigationHelper;
    }
}

