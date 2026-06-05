package com.saucedemo.appmanager;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public class ApplicationManager {

    private String browser;
    public WebDriver driver;
    private boolean useDocker = false;

    private NavigationHelper navigationHelper ;
    private ChecHelper checHelper ;
    private FillHelper fillHelper ;

    JavascriptExecutor js;
    private String baseUrl;

    private StringBuffer verificationErrors = new StringBuffer();

    public ApplicationManager(String browser,boolean useDocker) {
        this.browser=browser;
        this.useDocker=useDocker;
    }


    public void start() {

        if (Objects.equals(browser, "CHROME")){
             ChromeOptions options = getChromeOptions();

            if (useDocker) {
                try {
                    // Запускаем в Docker-контейнере
                    options.addArguments("--headless=new");
                    driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                driver = new ChromeDriver(options);
            }
         } else if (Objects.equals(browser, "FIREFOX")) {
             driver = new FirefoxDriver();
         } else if (Objects.equals(browser, "EDGE")) {
             driver = new EdgeDriver();
         } else {
             System.out.println("Не корректно выбран браузер");
         }

        driver.manage().window().maximize();

        navigationHelper = new NavigationHelper(driver);
        checHelper = new ChecHelper(driver,this);
        fillHelper = new FillHelper(driver);

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

    public FillHelper getFillHelper() {
        return fillHelper;
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