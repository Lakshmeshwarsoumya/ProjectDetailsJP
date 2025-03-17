package com.joom.automation.WebDriverUtility;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class WebdriverUtility {
	public WebDriver driver=null;
	

	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(70));

	}
	public WebDriver launchBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			Reporter.log("Invalid browser specified Defaulting to Chrome.", true);
			// driver = new ChromeDriver();
		}
		return driver;
	}
	// Wait until an element is visible

	public void waitForElementPresent(WebDriver driver, WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();

	}
	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.visibilityOf(element));

	}
	
	public void switchtoAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	
	public void scrollByAmount(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.scrollByAmount(0,500).perform();
		
	}
	
	public void scrollToElement(WebDriver driver,WebElement ele) {
		Actions a = new Actions(driver);
		a.scrollToElement(ele).perform();
	}
	
	public void scrollByAmountt(WebDriver driver,WebElement element) {
		Actions act = new Actions(driver);
		act.scrollByAmount(0,2500).perform();
		
	}
	public  void scrollToElements(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0,2000)");
    }
}
