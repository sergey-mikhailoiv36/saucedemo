package com.saucedemo.testing;

import com.saucedemo.appmanager.ApplicationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.Browser;


public class TestBase {


    public final ApplicationManager app = new ApplicationManager("CHROME"); // возможные значения FIREFOX/CHROME/EDGE-"не работате(проблемы в сети)"/

    @BeforeEach
    public void setUp() throws Exception {
        app.start();
    }

    @AfterEach
    public void tearDown() throws Exception {
        app.stop();
    }

}
