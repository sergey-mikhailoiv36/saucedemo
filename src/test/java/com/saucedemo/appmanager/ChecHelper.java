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

    public void checFilterName(String filter){

        List<WebElement> elementList = driver.findElements(By.className("inventory_item_name")); //ищем все елементы в которых хронятся название товара
        List<String> valueElement = new ArrayList<>(); //создаем список для названий товара

        for (WebElement element : elementList) {
            valueElement.add(element.getText()); //записываем названия товаров
        }
        List<String> expectedElement = new ArrayList<>(valueElement); // копируем список товаров

        if (Objects.equals(filter, "za")){
            Collections.sort(expectedElement,Collections.reverseOrder()); //сортируем копиию списка от большего к меньшему
            Assertions.assertEquals(valueElement,expectedElement); //сравниваем списки
        } else if (Objects.equals(filter, "az")) {
            Collections.sort(expectedElement); //от меньшему к большему
            Assertions.assertEquals(valueElement,expectedElement);
        } else {
            System.out.println("не выбран фильтр");
        }

    }

    public void checProductDescription() {
        int countProduct=driver.findElements(By.className("inventory_item_name")).size();

        System.out.println("Всего таваров на странице " + countProduct);

        for (int i=0; i<countProduct;i++){

            System.out.println("Товар №"+(i+1));

            Object[] itemArray=new Object[3];
            Object[] itemArrayDescriptionPage=new Object[3];

            itemArray[0]=driver.findElements(By.className("inventory_item_name")).get(i).getText();
            itemArray[1]=driver.findElements(By.className("inventory_item_desc")).get(i).getText();
            itemArray[2]=driver.findElements(By.className("inventory_item_price")).get(i).getText();
            System.out.println("ОР:");
            System.out.println(itemArray[0]);
            System.out.println(itemArray[1]);
            System.out.println(itemArray[2]);

            driver.findElements(By.className("inventory_item_name")).get(i).click();

            itemArrayDescriptionPage[0]=driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
            itemArrayDescriptionPage[1]=driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText();
            itemArrayDescriptionPage[2]=driver.findElement(By.cssSelector(".inventory_details_price")).getText();
            System.out.println("ФР:");
            System.out.println(itemArrayDescriptionPage[0]);
            System.out.println(itemArrayDescriptionPage[1]);
            System.out.println(itemArrayDescriptionPage[2]);

            Assertions.assertArrayEquals(itemArray,itemArrayDescriptionPage);

            System.out.println("");

            driver.findElement(By.id("back-to-products")).click();
        }
    }



}
