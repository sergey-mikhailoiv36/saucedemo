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

         List<WebElement> elementList = driver.findElements(By.className("inventory_item_price")); //ищем все елементы в которых хронятся цены
         List<Double> valueElement = new ArrayList<>(); //создаем список для значений элкментов

         for (WebElement element : elementList) {
             String elementText = element.getText(); //записывем цены построчно из списка
             double price = Double.parseDouble(elementText.replace("$", "")); //получаем цену и обрезаем "$"
             valueElement.add(price); //записываем цены
         }
            List<Double> expectedElement = new ArrayList<>(valueElement);

         if (Objects.equals(filter, "hilo")){
            Collections.sort(expectedElement,Collections.reverseOrder()); //от большего к меньшему
            Assertions.assertEquals(valueElement,expectedElement);
            } else if (Objects.equals(filter, "lohi")) {
             Collections.sort(expectedElement); //от меньшему к большему
             Assertions.assertEquals(valueElement,expectedElement);
         } else {
             System.out.println("не выбран фильтр");
         }

     }
}
