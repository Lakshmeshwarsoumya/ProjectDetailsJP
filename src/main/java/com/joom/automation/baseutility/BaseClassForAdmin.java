package com.joom.automation.baseutility;

import java.io.IOException;
import java.sql.SQLException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.protobuf.TextFormat.ParseException;
import com.joom.automation.WebDriverUtility.UtilityClassObject;
import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.generic.fileutility.ExcelUtility;

import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.generic.fileutility.JsonForUserUtility;
import com.joom.automation.objectrepository.AdminLoginPage;
import com.joom.automation.objectrepository.AdminPage;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.ManageProductsPage;
import com.joom.automation.objectrepository.SubCategoryPage;
import com.joom.automation.objectrepository.UserBooksCategoryPage;
import com.joom.automation.objectrepository.UserFashionCategoryPage;
import com.joom.automation.objectrepository.UserLogOutPage;
import com.joom.automation.objectrepository.UserMyCartPage;
import com.joom.automation.objectrepository.UserPaymentMethodPage;
import com.joom.automation.objectrepository.UserProductDetailsPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;

/**
 * CrossBrowserBaseClass manages test setup and teardown across multiple
 * browsers. Supports cross-browser execution using TestNG parameters.
 * 
 * Author: Soumya
 */

public class BaseClassForAdmin {
	public WebDriver driver = null;
	public JsonForAdminUtility jad;
	public AdminLoginPage adlp;
	public HomePage hp;
	public AdminPage adp;
	public WebdriverUtility wlib;
	public ExcelUtility ela ;
	public ManageProductsPage mpp;
	public SubCategoryPage scp;
	public static WebDriver sdriver=null;
	public UserShoppingHomePage ushp;
	public UserFashionCategoryPage ufcp;
	public UserMyCartPage umcp;
	public UserBooksCategoryPage ubcp;
	public UserProductDetailsPage updp;
	public UserPaymentMethodPage upmp;
	public JsonForUserUtility  jsd;
	public UserLogOutPage ullp;

	@BeforeSuite(groups={"Integration","System","Smoke"})
	public void configBS() {
		Reporter.log("=== Connecting to Database and Configuring Reports ===", true);
	}
	@Parameters("Browser")
	@BeforeClass(alwaysRun = true,groups={"Integration","System","Smoke"})
	public void configBC(@Optional("chrome") String browser)throws Throwable {
	    Reporter.log("=== Launching Browser ===", true);
	    jad = new JsonForAdminUtility();
	    //String browser = jad.readDataFromJson("browser");
	    String URL = jad.readDataFromJson("url");

	    if (browser.equalsIgnoreCase("chrome")) {
	        driver = new ChromeDriver();
	    } else if (browser.equalsIgnoreCase("firefox")) {
	        driver = new FirefoxDriver();
	    } else if (browser.equalsIgnoreCase("edge")) {
	        driver = new EdgeDriver();
	    } else {
	        Reporter.log("Invalid browser specified. Defaulting to Chrome.", true);
	        driver = new ChromeDriver();
	    }
	    sdriver = driver;
		UtilityClassObject .setDriver(driver);
		driver.get(URL);
	    driver.manage().window().maximize();
	   
	}

	@BeforeMethod(groups={"Integration","System","Smoke"})
	public void configBM() throws ParseException, IOException, Throwable {
		Reporter.log("=== Logging into Application ===", true);

		/*
		 * jad = new JsonForAdminUtility();
		 * 
		 * String USERNAME = jad.readDataFromJson("username"); String PASSWORD =
		 * jad.readDataFromJson("password");
		 * 
		 * hp = new HomePage(driver); hp.getAdminLoginLink().click();
		 * 
		 * adlp = new AdminLoginPage(driver); adlp.adminLogin(USERNAME, PASSWORD);
		 */
	}

	/**
	 * Logs out of the application after each test method.
	 */
	@AfterMethod(groups={"Integration","System","Smoke"})
	public void configAM() {
		Reporter.log("=== Logging out of Application ===", true);
		

	}

	@AfterClass(groups={"Integration","System","Smoke"})
	public void configAC() {
		driver.quit();

	}

	@AfterSuite(groups={"Integration","System","Smoke"})
	public void configAS() throws SQLException {
		Reporter.log("=== Closing DB Connection and Generating Reports ===", true);

	}

}
