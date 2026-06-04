package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FillHelper {

    private WebDriver driver;
    public FillHelper (WebDriver driver) {
        this.driver=driver;
    }

    public void fillYourInfo(String firstName, String lastName, String code){
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(code);
        driver.findElement(By.id("continue")).click();
    }
}
