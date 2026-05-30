package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.fail;



public class ApplicationManager {

    private String browser;
    WebDriver driver;

    private NavigationHelper navigationHelper ;
    private ChecHelper checHelper ;

    JavascriptExecutor js;
    private String baseUrl;
    //private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public ApplicationManager(String browser) {
        this.browser=browser;
    }


    public void start() {

         if (browser == "CHROME"){
             driver = new ChromeDriver();
         } else if (browser =="FIREFOX") {
             driver = new FirefoxDriver();
         } else if (browser =="EDGE") {
             driver = new EdgeDriver();
         } else {
             System.out.println("Не корректно выбран браузер");
         }






        driver.manage().window().maximize();
        baseUrl = "https://www.saucedemo.com/";

        navigationHelper = new NavigationHelper(driver);
        checHelper = new ChecHelper(driver);

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



    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ChecHelper getChecHelper() {
        return checHelper;
    }
}

