package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper {

    private WebDriver driver;
    public NavigationHelper(WebDriver driver) {
        this.driver=driver;
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
