package com.joom.automation.IntegrationTestCases;

import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
import com.joom.automation.objectrepository.InsertProductPage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class InsertProductTest extends BaseClassForAdmin {
	@Test(groups = "Integration")
	public void insertProduct() throws Throwable {
		adp = new AdminPage(driver);
		wlib = new WebdriverUtility();
		jad = new JsonForAdminUtility();

		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		hp = new HomePage(driver);
		hp.getAdminLoginLink().click();

		adlp = new AdminLoginPage(driver);
		adlp.adminLogin(USERNAME, PASSWORD);

		ela = new ExcelUtility();
		String category = ela.getDataFromExcel("Sheet1", 1, 0);
		adp.getInserProductLink().click();

		InsertProductPage ipp = new InsertProductPage(driver);
		wlib.waitForElementPresent(driver, ipp.getCategoryDropDown(), 20);
		wlib.select(ipp.getCategoryDropDown(), category);

		String subcategory = ela.getDataFromExcel("Sheet1", 1, 2);
		wlib.waitForElementPresent(driver, ipp.getSubCategoryDropDown(), 20);
		wlib.select(ipp.getSubCategoryDropDown(), subcategory);

		ipp.getProductNameTextField().sendKeys(ela.getDataFromExcel("Sheet1", 1, 3));
		ipp.getProductCompanyTextField().sendKeys(ela.getDataFromExcel("Sheet1", 1, 4));
		ipp.getProductPriceBd().sendKeys(ela.getDataFromExcel("Sheet1", 1, 5));
		ipp.getProductpriceAd().sendKeys(ela.getDataFromExcel("Sheet1", 1, 6));
		ipp.getDescriptionTextField().sendKeys(ela.getDataFromExcel("Sheet1", 1, 1));
		ipp.getProductShippingChargeTextField().sendKeys(ela.getDataFromExcel("Sheet1", 1, 7));
		wlib.select(ipp.getProductAvailabilityTF(), ela.getDataFromExcel("Sheet1", 1, 8));

		// Upload Product Images with file existence check
		File file = new File("D:\\HP1\\Desktop\\vivo-mobile-phone-1000x1000.webp");
		if (file.exists()) {
			ipp.getImage1().sendKeys(file.getAbsolutePath());
			ipp.getImage2().sendKeys(file.getAbsolutePath());
			ipp.getImage3().sendKeys(file.getAbsolutePath());
		} else {
			System.out.println("File not found: " + file.getAbsolutePath());
		}

		// Scroll to Insert Button using Actions class
		WebElement insertButton = driver.findElement(By.xpath("//button[text()='Insert']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(insertButton).perform(); // Scroll naturally

		insertButton.click();

		// Verify Product Insertion Success Message
		WebElement confirmationMsg = driver.findElement(By.xpath("//strong[contains(text(),'Well done!')]"));
		wlib.waitForElementPresent(driver, confirmationMsg, 20);
		String actualMsg = confirmationMsg.getText();

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualMsg, "Well done!", "Verification Failed: Message Mismatch");
		sa.assertAll();

		// Logout
		adp.getLogoutLink().click();
	}
}
