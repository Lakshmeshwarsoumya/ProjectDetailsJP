package com.joom.automation.IntegrationTestCases;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.objectrepository.AdminLoginPage;
import com.joom.automation.objectrepository.AdminPage;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.SubCategoryPage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class CreateSubCategoryTest extends BaseClassForAdmin {

	@Test(groups = "Integration")
	public void insertSubcategory() throws Throwable {
		WebdriverUtility wlib = new WebdriverUtility();
		JsonForAdminUtility jad = new JsonForAdminUtility();
		ExcelUtility ela = new ExcelUtility();

		// Read credentials from JSON
		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		// Navigate to Admin Login
		HomePage hp = new HomePage(driver);
		hp.getAdminLoginLink().click();

		AdminLoginPage adlp = new AdminLoginPage(driver);
		adlp.adminLogin(USERNAME, PASSWORD);

		SubCategoryPage scp = new SubCategoryPage(driver);
		AdminPage adp = new AdminPage(driver);

		// Click on SubCategory Link
		WebElement subCategory = adp.getSubCategoryLink();
		wlib.waitForElementPresent(driver, subCategory, 20);
		subCategory.click();

		// Select Category from Dropdown
		WebElement category = scp.getCategoryDropdown();
		wlib.waitForElementPresent(driver, category, 20);
		category.click();

		String categoryList = ela.getDataFromExcel("Sheet1", 1, 0);
		wlib.select(scp.getCategoryDropdown(), categoryList);

		// Enter SubCategory Name
		String subCategoryList = ela.getDataFromExcel("Sheet1", 1, 2);
		scp.getSubCategoryDropdown().sendKeys(subCategoryList);

		// Click Create Button
		WebElement createButton = scp.getCreateButton();
		wlib.waitForElementPresent(driver, createButton, 20);
		createButton.click();

		// Validate Confirmation Message
		WebElement text = scp.getConfirmMsg();
		wlib.waitForElementPresent(driver, text, 20);
		String actualMsg = text.getText();

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualMsg, "Well done!", "Verification Failed: Message Mismatch");
		Reporter.log("Sub-category created successfully", true);

		// Logout
		adp.logout();

		// Assert all
		sa.assertAll();
	}
}
