package com.joom.automation.IntegrationTestCases;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.JsonForUserUtility;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.UserBooksCategoryPage;
import com.joom.automation.objectrepository.UserLogOutPage;
import com.joom.automation.objectrepository.UserLoginPage;
import com.joom.automation.objectrepository.UserProductDetailsPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class UserWishlistTest extends BaseClassForAdmin {

	@Test(groups = "Integration Test")
	public void userWishlistTest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		// Initialize JSON Utility
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
		wlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();

		// Click on Books Link
		ushp = new UserShoppingHomePage(driver);
		ushp.getBooksbtn().click();

		// Scroll Down and Click on the Book
		ubcp = new UserBooksCategoryPage(driver);
		WebElement book = ubcp.getDemobooklink();
		wlib.scrollToElements(driver, book);
		book.click();
		Thread.sleep(2000);

		// Click on Wishlist Button
		updp = new UserProductDetailsPage(driver);
		updp.getWishlistBtn().click();
		Thread.sleep(5000);

		// Assertion for Wishlist Page
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(driver.getCurrentUrl().contains("Online_Shopping_Application/my-wishlist.php"),
				"My Wishlist page has not been displayed");

		Reporter.log("My Wishlist page has been displayed.", true);
		sa.assertAll();

		ullp = new UserLogOutPage(driver);
		ullp.getLogoutlink().click();
	}
}
