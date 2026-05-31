package com.saucedemo.testing;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;


public class TestSaucedemoFilter extends TestBase {


    @Test
    public void testUntitledTestCase() throws Exception {
        app.login("standard_user", "secret_sauce");
        app.getNavigationHelper().selectFilter("hilo");
        TimeUnit.SECONDS.sleep(5);
        app.getChecHelper().checFilterPrice("hilo");

        app.getNavigationHelper().selectFilter("lohi");
        TimeUnit.SECONDS.sleep(5);
        app.getChecHelper().checFilterPrice("lohi");

       // TimeUnit.SECONDS.sleep(3);
        app.getNavigationHelper().goTo("cart");
        app.getNavigationHelper().goTo("cart");
       // TimeUnit.SECONDS.sleep(3);
        app.getNavigationHelper().goTo("shop");
        app.getNavigationHelper().goTo("shop");
      //  TimeUnit.SECONDS.sleep(3);
        app.getNavigationHelper().goTo("cart");
        app.getNavigationHelper().goTo("cart");
        app.getNavigationHelper().goTo("cart");
       // TimeUnit.SECONDS.sleep(3);
        app.getNavigationHelper().goTo("allIteems");
        //TimeUnit.SECONDS.sleep(5);
        app.getNavigationHelper().goTo("logout");
       // TimeUnit.SECONDS.sleep(5);
        //  app.getNavigationHelper().goTo("logout");
     //   TimeUnit.SECONDS.sleep(5);
    }


}
