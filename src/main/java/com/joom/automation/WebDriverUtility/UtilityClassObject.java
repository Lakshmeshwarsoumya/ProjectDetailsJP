package com.joom.automation.WebDriverUtility;

import org.openqa.selenium.WebDriver;
import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static ExtentTest getTest() {
        if (test.get() == null) {
            System.err.println("Warning: ExtentTest is null. Ensure it is initialized before use.");
        }
        return test.get();
    }

    public static void setTest(ExtentTest actTest) {
        test.set(actTest);
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            System.err.println("Warning: WebDriver is null. Ensure it is initialized before use.");
        }
        return driver.get();
    }

    public static void setDriver(WebDriver actDriver) {
        driver.set(actDriver);
    }

    public static void clear() {
        test.remove();
        driver.remove();
    }
}
