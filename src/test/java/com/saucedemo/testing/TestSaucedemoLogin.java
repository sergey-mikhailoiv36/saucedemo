package com.saucedemo.testing;

import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;





public class TestSaucedemoLogin extends TestBase {


    @Test
    public void testUntitledTestCase() throws Exception {
        app.login("standard_user", "secret_sauce");
        TimeUnit.SECONDS.sleep(5);
    }


}
