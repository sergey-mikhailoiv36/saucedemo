package com.saucedemo.testing;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestProductDescription extends TestBase{


    @Test
    @Order(1)
    public void testAllProductDescription() {
        app.login("standard_user", "secret_sauce");
        app.getNavigationHelper().goTo("allIteems");
        app.getChecHelper().checAllProductDescription();
        app.getNavigationHelper().goTo("logout");
    }

    @Test
    @Order(2)
    public void testRandomProductDescription() {
        app.login("standard_user", "secret_sauce");
        app.getNavigationHelper().goTo("allIteems");
        app.getChecHelper().checRandomProductDescription();
        app.getNavigationHelper().goTo("logout");
    }


}

