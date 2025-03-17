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

import com.joom.automation.objectrepository.UserLoginPage;
import com.joom.automation.objectrepository.UserProductDetailsPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class UserWishlistTest extends BaseClassForAdmin {
	@Test(groups = "Integration Test")
	public void userWishlistTest() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		jsd = new JsonForUserUtility();
		hp = new HomePage(driver);
		hp.getLoginLink().click();

		String USERNAME = jsd.readDataFromJsonFile("username");
		Thread.sleep(3000);
		String PASSWORD = jsd.readDataFromJsonFile("password");
		Thread.sleep(3000);

		UserLoginPage ulp = new UserLoginPage(driver);
		ulp.getEmailtxtfield().sendKeys(USERNAME);
		ulp.getPasswordtxtfield().sendKeys(PASSWORD);
		WebElement ele = ulp.getLoginbtn();
		// wb.scrollToElement(driver, ele);
		wlib = new WebdriverUtility();
		wlib.scrollByAmountt(driver, ele);
		ele.click();

		wlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();

		// click on books link
		ushp = new UserShoppingHomePage(driver);
		ushp.getBooksbtn().click();

		// scroll down and click on theastilton book
		ubcp = new UserBooksCategoryPage(driver);
		WebElement book = ubcp.getDemobooklink();
		wlib.scrollToElements(driver, book);
		ubcp.getDemobooklink().click();
		Thread.sleep(2000);

		// to click on wishlist button
		updp = new UserProductDetailsPage(driver);
		updp.getWishlistBtn().click();

		Thread.sleep(5000);

		SoftAssert sa = new SoftAssert();
		sa.assertTrue(driver.getCurrentUrl().contains("Online_Shopping_Application/my-wishlist.php"),
				"mywishlist page hasnot been displayed");

		Reporter.log("mywishlist page has been displayed.", true);
		sa.assertAll();

	}

}
