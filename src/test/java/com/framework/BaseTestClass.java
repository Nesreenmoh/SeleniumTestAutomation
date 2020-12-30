package com.framework;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static com.testframework.DriverFactory.getChromeDriver;

public class BaseTestClass {

    public static final String BaseURL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll
    static void setup() {
      driver = getChromeDriver();
    }


    @AfterAll
    static void cleanAll() {
        driver.close();
    }
}
