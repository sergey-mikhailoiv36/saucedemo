package com.saucedemo.testing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class TestProductDescription extends TestBase{



    @Test
    public void testProductDescription() throws Exception{

        app.login("standard_user", "secret_sauce");
        app.getNavigationHelper().goTo("allIteems");
        app.getChecHelper().checProductDescription();
    }


}

