package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationManager {
    protected WebDriver driver;
    JavascriptExecutor js;
    private String baseUrl;
    //private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public void start() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        baseUrl = "https://www.saucedemo.com/";
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

    public void selectFilter(String filter) {     // принимает занчения "az/za/lohi/hilo"
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='" + filter + "']")).click();
    }

    public void goTo(String namePage) {

        if (namePage == "cart") { //переход в казину
            driver.findElement(By.id("shopping_cart_container")).click();
        } 
        else if (namePage == "shop") { //продолжить шопинг
            driver.findElement(By.id("continue-shopping")).click();
        }
        else if (namePage == "allIteems") { //переход по кнопке
            driver.findElement(By.id("react-burger-menu-btn")).click();
            driver.findElement(By.id("inventory_sidebar_link")).click();
        }
        else if (namePage == "logout") { //выйти из акаунта
            driver.findElement(By.id("react-burger-menu-btn")).click();
            driver.findElement(By.id("logout_sidebar_link")).click();
        }
        else {
                System.out.println("Такой странице нет");
            }
        }
}

