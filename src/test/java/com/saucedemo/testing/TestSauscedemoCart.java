package com.saucedemo.testing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestSauscedemoCart extends  TestBase {



    @Test
    public void testAddCart() throws InterruptedException {
        logger.info("тест логов");
        app.login("standard_user","secret_sauce");
        int countProduct=app.driver.findElements(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory")).size();

        Object[][] itemArray = app.getChecHelper().allItemArray(countProduct,"product"); //получаем массив со всеми товарами на главной странице
        logger.info("Информация о всех товарах на странице: \n{}",Arrays.deepToString(itemArray));
        logger.info("Добавление товаров в корзину");
        for (int i = 0; i<countProduct;i++) {
            logger.info("добавляем товар в корзину с название "+itemArray[i][0]);
            app.getNavigationHelper().addCart((String) itemArray[i][3]); //добавляем в корзину все товары
        }

        logger.info("Переход в корзину");
        app.getNavigationHelper().goTo("cart"); //переход в корзину

        Object[][] itemInCartArray = app.getChecHelper().allItemArray((countProduct),"cart"); //получаем массив со всеми товарами в корзине


        logger.info("Информация о всех товарах в корзине: \n{}",Arrays.deepToString(itemInCartArray));


        app.getChecHelper().checProductLists(itemArray,itemInCartArray); // сравниваем количество товаров в массивах

        app.getChecHelper().checProductInCart(itemArray,itemInCartArray); // сравнение имен, описания и цены товаров в корзине

        app.getNavigationHelper().goTo("placeAnOrder"); //оформить заказ

        app.getFillHelper().fillYourInfo("as","ds","123");

        String elementPrice=app.driver.findElement(By.className("summary_subtotal_label")).getText();
        double tatalPrice = Double.parseDouble(elementPrice.replace("Item total: $", ""));
        double allItemPrace = 0;

        for (int i=0; i<countProduct;i++){
            allItemPrace = allItemPrace+(double)itemArray[i][2];
        }

        logger.info("Цена на странице="+tatalPrice);
        logger.info("Цена из массива="+allItemPrace);

        //        TimeUnit.SECONDS.sleep(10);

    }






}

