package com.saucedemo.appmanager;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationManager {

    private String browser;
    public WebDriver driver;

    private NavigationHelper navigationHelper ;
    private ChecHelper checHelper ;

    JavascriptExecutor js;
    private String baseUrl;

    private StringBuffer verificationErrors = new StringBuffer();

    public ApplicationManager(String browser) {
        this.browser=browser;
    }


    public void start() {

        if (Objects.equals(browser, "CHROME")){
             ChromeOptions options = getChromeOptions();
             driver = new ChromeDriver(options);
         } else if (Objects.equals(browser, "FIREFOX")) {
             driver = new FirefoxDriver();
         } else if (Objects.equals(browser, "EDGE")) {
             driver = new EdgeDriver();
         } else {
             System.out.println("Не корректно выбран браузер");
         }

        driver.manage().window().maximize();

        navigationHelper = new NavigationHelper(driver);
        checHelper = new ChecHelper(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
    }

    public void login(String user, String password) {
        baseUrl = "https://www.saucedemo.com/";
        driver.get(baseUrl);
        driver.findElement(By.id("user-name")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    public void stop() {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public ChecHelper getChecHelper() {
        return checHelper;
    }

    private static @NonNull ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        // 1. Самый важный флаг: выключает проверку утечек паролей
        // Это отключает саму функцию безопасности браузера[citation:2]
        options.addArguments("--disable-features=PasswordLeakDetection");

        // 2. Настройки (prefs) для отключения менеджера паролей
        Map<String, Object> prefs = new HashMap<>();
        // Не предлагать сохранять пароли
        prefs.put("credentials_enable_service", false);
        // Отключить менеджер паролей
        prefs.put("profile.password_manager_enabled", false);
        // Явно отключить проверку на утечки в настройках профиля
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        // (Опционально) Отключаем режим автоматизации, чтобы страницы вели себя "как обычно"
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        return options;
    }

}