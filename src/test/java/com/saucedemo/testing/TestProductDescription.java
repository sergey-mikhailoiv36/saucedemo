package com.saucedemo.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class TestProductDescription extends TestBase{



    @Test
    public void testProductDescription() throws Exception{
        app.login("standard_user", "secret_sauce");


        app.getNavigationHelper().goTo("allIteems");


        Object[] itemArray=new Object[3];
        Object[] itemArrayDescriptionPage=new Object[3];


        itemArray[0]=app.driver.findElement(By.className("inventory_item_name")).getText();
        itemArray[1]=app.driver.findElement(By.className("inventory_item_desc")).getText();
        itemArray[2]=app.driver.findElement(By.className("inventory_item_price")).getText();
        System.out.println(itemArray[0]);
        System.out.println(itemArray[1]);
        System.out.println(itemArray[2]);


        app.driver.findElement(By.className("inventory_item_name")).click();


        itemArrayDescriptionPage[0]=app.driver.findElement(By.cssSelector(".inventory_details_name.large_size")).getText();
        itemArrayDescriptionPage[1]=app.driver.findElement(By.cssSelector(".inventory_details_desc.large_size")).getText();
        itemArrayDescriptionPage[2]=app.driver.findElement(By.cssSelector(".inventory_details_price")).getText();
        System.out.println(itemArrayDescriptionPage[0]);
        System.out.println(itemArrayDescriptionPage[1]);
        System.out.println(itemArrayDescriptionPage[2]);


        Assertions.assertArrayEquals(itemArray,itemArrayDescriptionPage);


        app.getNavigationHelper().goTo("bactToProducts");
    }
}

