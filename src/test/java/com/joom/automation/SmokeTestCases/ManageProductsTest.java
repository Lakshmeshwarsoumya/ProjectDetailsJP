package com.joom.automation.SmokeTestCases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.objectrepository.AdminLoginPage;
import com.joom.automation.objectrepository.AdminPage;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.ManageProductsPage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class ManageProductsTest extends BaseClassForAdmin {

	@Test(dataProvider = "productData", groups = "Smoke")
	public void deleteProducts(String ProductName) throws Throwable {

		// Initialize Required Pages
		ManageProductsPage mpp = new ManageProductsPage(driver);
		HomePage hp = new HomePage(driver);
		AdminLoginPage adlp = new AdminLoginPage(driver);
		AdminLoginPage adp = new AdminLoginPage(driver); // Initialize for Logout
		JsonForAdminUtility jad = new JsonForAdminUtility();
		ExcelUtility ela = new ExcelUtility();
		WebdriverUtility wlib = new WebdriverUtility();

		// Read Admin Credentials from JSON
		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		// Perform Admin Login
		hp.getAdminLoginLink().click();
		adlp.adminLogin(USERNAME, PASSWORD);

		// Click on Manage Products
		WebElement productLink = mpp.getManageProductsLink();
		wlib.waitForElementPresent(driver, productLink, 20);
		productLink.click();
		wlib.waitForPageToLoad(driver);

		// Construct XPath for Delete Icon
		String deleteXpath = "//td[contains(text(),'" + ProductName
				+ "')]/following-sibling::td[last()]//i[contains(@class,'icon-remove-sign')]";

		try {
			WebElement deleteIcon = driver.findElement(By.xpath(deleteXpath));
			deleteIcon.click();
			wlib.switchToAlertAndAccept(driver);
			System.out.println("Deleted product: " + ProductName);
		} catch (NoSuchElementException e) {
			System.out.println("❌ Product not found: " + ProductName);
			return; // Skip remaining steps if the product is not found
		}

		// Wait for the success message to appear
		WebElement deleteMsg = driver.findElement(By.xpath("//strong[contains(text(),'Oh snap!')]"));
		// Verify Delete Message
		Assert.assertTrue(deleteMsg.isDisplayed(), "Delete confirmation message not displayed!");
		String actualMsg = deleteMsg.getText();
		Assert.assertTrue(actualMsg.contains("Oh snap! Product deleted !!"), "Unexpected delete message: " + actualMsg);

		System.out.println("Product '" + ProductName + "' successfully deleted!");

		// Logout
		AdminPage adpp = new AdminPage(driver);
		adpp.logout();
		System.out.println(" Successfully logged out!");
	}

	@DataProvider(name = "productData")
	public Object[][] getProductData() throws EncryptedDocumentException, IOException {
		ExcelUtility ela = new ExcelUtility();
		int rowCount = ela.getRowCount("Sheet4");
		Object[][] data = new Object[rowCount][1];

		System.out.println("📄 Total Rows in Excel: " + rowCount);
		for (int i = 0; i < rowCount; i++) {
			data[i][0] = ela.getDataFromExcel("Sheet4", (i + 1), 0);
		}

		return data;
	}
}
