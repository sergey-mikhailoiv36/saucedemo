package com.saucedemo.testing;

import com.saucedemo.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.junit.jupiter.api.Test;


public class TestBase {

    //false-запускаем тесты в локально
    //public static final ApplicationManager app = new ApplicationManager("CHROME",false); // возможные значения FIREFOX/CHROME/EDGE-"не работает(проблемы в сети)"/

    //true-запускаем тесты в докере
    public static final ApplicationManager app = new ApplicationManager("CHROME",true); // возможные значения FIREFOX/CHROME/EDGE-"не работает(проблемы в сети)"/
    public static Logger logger = LoggerFactory.getLogger(TestSauscedemoCart.class);


    @BeforeAll
    static void setUp() throws Exception {
        app.start();
    }

    @AfterAll

    static void tearDown() throws Exception {
        app.stop();
    }

}
