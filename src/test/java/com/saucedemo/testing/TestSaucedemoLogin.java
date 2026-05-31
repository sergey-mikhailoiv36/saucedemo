package com.saucedemo.testing;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeUnit;




//@Disabled // <-- Все тесты в этом классе будут пропущены

public class TestSaucedemoLogin extends TestBase {


    @Disabled // <-- Этот тест будет пропущен
    @Test
    public void testUntitledTestCase() throws Exception {
        app.login("standard_user", "secret_sauce");
        TimeUnit.SECONDS.sleep(5);
    }


}
