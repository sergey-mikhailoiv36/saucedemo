package com.saucedemo.appmanager;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class ChecHelper {

    private WebDriver driver;
    public ChecHelper(WebDriver driver) {
        this.driver=driver;
    }

     public void checFilterPrice(String filter){


         List<WebElement> elementList = driver.findElements(By.className("inventory_item_price"));
         List<Double> meaningElement = new ArrayList<>();

         for (WebElement element : elementList) {
             String elementText = element.getText();
             double price = Double.parseDouble(elementText.replace("$", ""));
             meaningElement.add(price);
         }
            List<Double> expectedElement = new ArrayList<>(meaningElement);

         if (Objects.equals(filter, "hilo")){
            Collections.sort(expectedElement,Collections.reverseOrder()); //от большего к меньшему
            Assertions.assertEquals(meaningElement,expectedElement);
            } else if (Objects.equals(filter, "lohi")) {
             Collections.sort(expectedElement); //от меньшему к большему
             Assertions.assertEquals(meaningElement,expectedElement);
         }

     }
}
