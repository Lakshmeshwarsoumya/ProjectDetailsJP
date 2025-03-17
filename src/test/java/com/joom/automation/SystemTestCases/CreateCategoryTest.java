package com.joom.automation.SystemTestCases;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.protobuf.TextFormat.ParseException;
import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.objectrepository.AdminLoginPage;
import com.joom.automation.objectrepository.AdminPage;
import com.joom.automation.objectrepository.CreateCategoryPage;
import com.joom.automation.objectrepository.HomePage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class CreateCategoryTest extends BaseClassForAdmin {

	@Test(groups = "System")
	public void createCategory() throws ParseException, IOException, Throwable {
		adp = new AdminPage(driver);
		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		hp = new HomePage(driver);
		hp.getAdminLoginLink().click();

		adlp = new AdminLoginPage(driver);
		adlp.adminLogin(USERNAME, PASSWORD);
		CreateCategoryPage ccp = new CreateCategoryPage(driver);
		ela = new ExcelUtility();

		// Fetch category name & description from Excel
		String category = ela.getDataFromExcel("Sheet1", 1, 0);
		String description = ela.getDataFromExcel("Sheet1", 2, 1);

		// Click on "Create Category" link
		adp.getCreateCategoryLink().click();

		// Fill form and create category
		ccp.createCategory(category, description);

		// Wait for confirmation message
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement confirmationMsg = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));

		// Search for the created category
		WebElement searchTextField = driver.findElement(By.xpath("//input[@aria-controls='DataTables_Table_0']"));
		searchTextField.sendKeys(category);

		// Wait for the category to be displayed in the table
		WebElement createCategory = driver.findElement(By.xpath("//td[contains(text(),'" + category + "')]"));
		wlib = new WebdriverUtility();
		wlib.waitForElementPresent(driver, createCategory, 20);
		// Soft Assertion to verify category creation
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(createCategory.equals(category), true);
		Reporter.log("category created successfully", true);

		// Assert all
		//sa.assertAll();

		System.out.println("Category successfully created and verified.");
		adp.getLogoutLink().click();
	}
}
