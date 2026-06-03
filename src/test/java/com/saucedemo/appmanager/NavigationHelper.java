package com.saucedemo.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

public class NavigationHelper {

    private WebDriver driver;
    public NavigationHelper(WebDriver driver) {
        this.driver=driver;
    }

    public void selectFilter(String filter) {     // принимает занчения "az/za/lohi/hilo"
        driver.findElement(By.cssSelector(".product_sort_container")).click();
        driver.findElement(By.cssSelector("option[value='" + filter + "']")).click();
    }

    public void goTo(String namePage) { // принимает занчения "cart/shop/allIteems/logout/bactToProducts"

        if (Objects.equals(namePage, "cart")) { //переход в казину
            if (checPage("title", "Your Cart")){
            return;
            } else driver.findElement(By.id("shopping_cart_container")).click();
        }
        else if (Objects.equals(namePage, "shop")) { //продолжить шопинг "Continue Shopping"
            if (checPage("title", "Products")){
                return;
            } else driver.findElement(By.id("continue-shopping")).click();
        }
        else if (Objects.equals(namePage, "bactToProducts")) { //вернутся к товарарам
            if (checPage("back-image", "Back to products")) {
                return;
            } else driver.findElement(By.id("back-to-products")).click();
        }
        else if (Objects.equals(namePage, "allIteems")) { //переход по кнопке "All Items"
            if (checPage("title", "Products")){
                return;
            } else {
            driver.findElement(By.id("react-burger-menu-btn")).click();
            driver.findElement(By.id("inventory_sidebar_link")).click();
            }
        }
        else if (Objects.equals(namePage, "logout")) { //выйти из акаунта
            if (checPage("login_logo", "Swag Labs")){
                return;
            } else {
                driver.findElement(By.id("react-burger-menu-btn")).click();
                driver.findElement(By.id("logout_sidebar_link")).click();
            }
        }
        else {
                System.out.println("Такой странице нет");
            }
        }


    private boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    private boolean checPage (String className,String namePage ){
        return isElementPresent(By.className(className)) &&
                driver.findElement(By.className(className)).getText().equals(namePage);
    }


}
