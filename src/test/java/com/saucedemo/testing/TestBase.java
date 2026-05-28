package com.saucedemo.testing;

import com.saucedemo.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public class TestBase {


    public final ApplicationManager app = new ApplicationManager();

    @BeforeEach
    public void setUp() throws Exception {
        app.start();
    }

    @AfterEach
    public void tearDown() throws Exception {
        app.stop();
    }

}
