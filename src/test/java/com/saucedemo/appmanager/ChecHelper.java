package com.saucedemo.appmanager;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Random;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ChecHelper {

    private WebDriver driver;
    private ApplicationManager app;

    public ChecHelper(WebDriver driver,ApplicationManager app) {
        this.driver=driver;
        this.app = app;
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

        List<WebElement> elementList = driver.findElements(By.className("inventory_item_name")); //ищем все элементы в которых хранятся название товара
        List<String> valueElement = new ArrayList<>(); //создаем список для названий товара

        for (WebElement element : elementList) {
            valueElement.add(element.getText()); //записываем названия товаров
        }
        List<String> expectedElement = new ArrayList<>(valueElement); // копируем список товаров

        if (Objects.equals(filter, "za")){
            expectedElement.sort(Collections.reverseOrder()); //сортируем копию списка от большего к меньшему
            Assertions.assertEquals(valueElement,expectedElement); //сравниваем списки
        } else if (Objects.equals(filter, "az")) {
            Collections.sort(expectedElement); //от меньшего к большему
            Assertions.assertEquals(valueElement,expectedElement);
        } else {
            System.out.println("не выбран фильтр");
        }

    }

    public void checAllProductDescription() { //Проверка описания товара (Сравнение главной страницы со страницей товара) проверка по всем товарам на странице
        app.getNavigationHelper().goTo("allIteems"); // переход на главную страницу, если уже на главной, то ничего не делаем
        int countProduct=driver.findElements(By.className("inventory_item_name")).size(); //считаем количество товаров
        List<Integer> faileProduct = new ArrayList<>();
        app.getNavigationHelper().selectFilter("hilo"); //выбираем фильтр

        for (int i=0; i<countProduct;i++){ //выбираем каждый товар по очереди


            Object[] itemArray = (Object[]) itemArray(i); //создаем массив для описания товара на главной странице
            app.getNavigationHelper().selectProductNumber(i); //переходим на страницу товара
            Object[] itemArrayDescriptionPage =  (Object[]) itemArrayDescriptionPage(i); //создаем массив для описания товара на странице товара

        if (!compareArrays(itemArray, itemArrayDescriptionPage)) { //сравниваем массивы и выводим товары которые не совпадают
            faileProduct.add(i+1); // номер товара который не совпадает
            outText(itemArray[0],itemArray[1],itemArray[2],itemArrayDescriptionPage[0],itemArrayDescriptionPage[1],itemArrayDescriptionPage[2]);
            }
            app.getNavigationHelper().goTo("bactToProducts"); //возвращаемся на главную
        }
        if (!faileProduct.isEmpty()){ //если НЕ пустой (есть ошибки)
            Assertions.fail("ОР не совпадает с ФР");
        }
    }

    public void checRandomProductDescription() { //Проверка описания товара (Сравнение главной страницы со страницей товара) проверка по всем товарам на странице
        app.getNavigationHelper().goTo("allIteems");
        int countProduct=driver.findElements(By.className("inventory_item_name")).size(); //считаем количество товаров
        List<Integer> faileProduct = new ArrayList<>();
        Random random = new Random();
        int i = random.nextInt(countProduct);

        app.getNavigationHelper().selectFilter("hilo"); //выбираем фильтр

            Object[] itemArray = (Object[]) itemArray(i); //создаем массив для описания товара на главной странице
            app.getNavigationHelper().selectProductNumber(i); //переходим на страницу товара
            Object[] itemArrayDescriptionPage =  (Object[]) itemArrayDescriptionPage(i); //создаем массив для описания товара на странице товара

            if (!compareArrays(itemArray, itemArrayDescriptionPage)) { //сравниваем массивы и выводим товары которые не совпадают
                faileProduct.add(i); // номер товара который не совпадает
                outText(itemArray[0],itemArray[1],itemArray[2],itemArrayDescriptionPage[0],itemArrayDescriptionPage[1],itemArrayDescriptionPage[2]);
            }
            app.getNavigationHelper().goTo("bactToProducts"); //возвращаемся на главную

         if (!faileProduct.isEmpty()){ //если НЕ пустой (есть ошибки)
            Assertions.fail("ОР не совпадает с ФР");
        }
    }

    public Object[][] allItemArray(int countProduct,String namePage) {
        Object[][] itemArray = new Object[countProduct][4];
        for (int i = 0; i< countProduct; i++) {

            //создаем массив для описания товара на главной странице

            itemArray[i][0] = driver.findElements(By.className("inventory_item_name")).get(i).getText(); //записываем имя товара на главной странице
            itemArray[i][1] = driver.findElements(By.className("inventory_item_desc")).get(i).getText(); //записываем описание товара на главной странице

            String elementPrice = driver.findElements(By.className("inventory_item_price")).get(i).getText();
            double price = Double.parseDouble(elementPrice.replace("$", "")); //получаем цену и обрезаем "$"

            itemArray[i][2] = price; //записываем цены
            if (Objects.equals(namePage, "product")) {
                itemArray[i][3] = driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).get(i).getAttribute("id"); //запоминаем ид кнопки
            } else if (Objects.equals(namePage, "cart")) {
                itemArray[i][3] = driver.findElements(By.cssSelector(".btn.btn_secondary.btn_small.cart_button")).get(i).getAttribute("id"); //запоминаем ид кнопки
            }
        }
        return itemArray;
    }

    public void checProductInCart(Object @NonNull [][] itemArray, Object @NonNull [][]itemInCartArray){

        for (int i=0;i<itemArray.length;i++){
            Assertions.assertEquals(itemArray[i][0],itemInCartArray[i][0],
                    String.format("Название товара %s не совпадает", itemArray[i][0]));

            Assertions.assertEquals(itemArray[i][1],itemInCartArray[i][1],
                    String.format("Описание товара %s не совпадает", itemArray[i][1]));

            Assertions.assertEquals(itemArray[i][2],itemInCartArray[i][2],
                    String.format("Цена товара %s не совпадает", itemArray[i][2] ));

        }

    }

    public void checProductLists(Object @NonNull [] itemArray, Object @NonNull []itemInCartArray){
        Assertions.assertEquals(itemArray.length,itemInCartArray.length,"Количество товаров на главной странице и в корзине не совпадает");
    } // сравниваем количество товаров в массивах

    public void checCardSumm(){

    }

    private boolean compareArrays(Object[] expected, Object[] actual) {
        return Arrays.equals(expected, actual);
    }

    private void outText(Object nameOP, Object descriptionOP, Object praceOP, Object nameFP, Object descriptionFP, Object praceFP){
        System.out.println("Товар с названием '"+nameOP+"' имеет ошибку");
        System.out.println("ОР:");
        System.out.println(nameOP);
        System.out.println(descriptionOP);
        System.out.println(praceOP);

        System.out.println("ФР:");
        System.out.println(nameFP);
        System.out.println(descriptionFP);
        System.out.println(praceFP);
        System.out.println();
    }

    private @NonNull Object itemArray(int i){
        Object[] itemArray=new Object[3]; //создаем массив для описания товара на главной странице

        itemArray[0]=driver.findElements(By.className("inventory_item_name")).get(i).getText(); //записываем имя товара на главной странице
        itemArray[1]=driver.findElements(By.className("inventory_item_desc")).get(i).getText(); //записываем описание товара на главной странице
        itemArray[2]=driver.findElements(By.className("inventory_item_price")).get(i).getText(); //записываем цену товара на главной странице
        return itemArray;
    }

    private @NonNull Object itemArrayDescriptionPage(int i){
        Object[] itemArrayDescriptionPage=new Object[3]; //оздаем массив для описания товара на странице товара

        itemArrayDescriptionPage[0]=driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText(); //записываем имя товара на странице товара
        itemArrayDescriptionPage[1]=driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText(); //записываем описание товара на странице товара
        itemArrayDescriptionPage[2]=driver.findElement(By.cssSelector(".inventory_details_price")).getText(); //записываем цену товара на странице товара
        return itemArrayDescriptionPage;
    }



}
