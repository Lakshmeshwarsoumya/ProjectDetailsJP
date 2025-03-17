package com.joom.automation.IntegrationTestCases;

import org.openqa.selenium.By;
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
	@Test(groups="Integration")
	public void insertSubcategory() throws Throwable {
		wlib = new WebdriverUtility();
		jad = new JsonForAdminUtility();

		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		hp = new HomePage(driver);
		hp.getAdminLoginLink().click();

		adlp = new AdminLoginPage(driver);
		adlp.adminLogin(USERNAME, PASSWORD);

		ela = new ExcelUtility();
		scp = new SubCategoryPage(driver);
		adp = new AdminPage(driver);
		Thread.sleep(2000);
		WebElement subCategory = adp.getSubCategoryLink();
		subCategory.click();
		
		wlib.waitForElementPresent(driver,subCategory , 20);
		WebElement category = scp.getCategoryDropdown();
		Thread.sleep(2000);
		category.click();
		Thread.sleep(2000);
		String categoryList = ela.getDataFromExcel("Sheet1", 1, 0);

		WebElement selectDropdown = scp.getCategoryDropdown();
		wlib.select(selectDropdown, categoryList);
		String subCategoryList = ela.getDataFromExcel("Sheet1", 1, 2);
		Thread.sleep(2000);
		scp.getSubCategoryDropdown().sendKeys(subCategoryList);
		
		WebElement createButton = scp.getCreateButton();
		wlib.waitForElementPresent(driver,createButton , 20);
		createButton.click();
		WebElement text = scp.getConfirmMsg();
		wlib.waitForElementPresent(driver,text , 20);
		
		text.getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(text, true);
		Reporter.log("created sub category", true);

	}

}
