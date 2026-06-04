package com.saucedemo.testing;

import com.saucedemo.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.Browser;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class TestBase {


    public static final ApplicationManager app = new ApplicationManager("CHROME"); // возможные значения FIREFOX/CHROME/EDGE-"не работает(проблемы в сети)"/

    @BeforeAll
    static void setUp() throws Exception {
        app.start();
    }

    @AfterAll

    static void tearDown() throws Exception {
        app.stop();
    }

}
