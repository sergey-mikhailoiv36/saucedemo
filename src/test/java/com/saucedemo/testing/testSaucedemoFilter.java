package com.saucedemo.testing;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


public class testSaucedemoFilter extends TestBase {


    @Test
    public void testUntitledTestCase() throws Exception {
        app.login("standard_user", "secret_sauce");
        app.selectFilter("hilo");
        TimeUnit.SECONDS.sleep(3);
        app.goTo("cart");
        TimeUnit.SECONDS.sleep(3);
        app.goTo("shop");
        TimeUnit.SECONDS.sleep(3);
        app.goTo("cart");
        TimeUnit.SECONDS.sleep(3);
        app.goTo("allIteems");
        TimeUnit.SECONDS.sleep(5);
        app.goTo("logout");
        TimeUnit.SECONDS.sleep(5);
    }


}
