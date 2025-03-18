package com.joom.automation.IntegrationTestCases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.generic.fileutility.JsonForUserUtility;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.UserLogOutPage;
import com.joom.automation.objectrepository.UserLoginPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class UserProductSearchTest extends BaseClassForAdmin {

	@Test(groups = "Integration Test")
	public void userProductSearchTest()
			throws EncryptedDocumentException, IOException, InterruptedException, ParseException {

		// Initialize JSON utility
		jsd = new JsonForUserUtility();

		// Navigate to Login Page
		hp = new HomePage(driver);
		hp.getLoginLink().click();

		// Read User Credentials from JSON
		String USERNAME = jsd.readDataFromJsonFile("username");
		Thread.sleep(3000);
		String PASSWORD = jsd.readDataFromJsonFile("password");
		Thread.sleep(3000);

		// Perform Login
		UserLoginPage ulp = new UserLoginPage(driver);
		ulp.getEmailtxtfield().sendKeys(USERNAME);
		ulp.getPasswordtxtfield().sendKeys(PASSWORD);

		WebElement ele = ulp.getLoginbtn();

		// Scroll to Login Button and Click
		wlib = new WebdriverUtility();
		wlib.scrollByAmountt(driver, ele);
		ele.click();

		// Wait for Page Load
		wlib = new WebdriverUtility();
		wlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();

		// Search for Product
		UserShoppingHomePage ushp = new UserShoppingHomePage(driver);
		ExcelUtility el = new ExcelUtility();
		String category = el.getDataFromExcel("Users", 1, 5);
		Thread.sleep(1000);

		ushp.getSeachtxtfld().sendKeys(category);
		ushp.getLoginbtn().click();

		// Assertion for Search Results
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(driver.getCurrentUrl().contains("Online_Shopping_Application/search-result.php"),
				"Searched product not displayed successfully!");
		Reporter.log("Searched product displayed successfully.", true);
		sa.assertAll();

		// logout
		 ullp = new UserLogOutPage(driver);
		ullp.getLogoutlink().click();

	}
}
