package com.joom.automation.SmokeTestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.objectrepository.AdminLoginPage;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.ManageProductsPage;
@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class ManageProductsTest extends BaseClassForAdmin {
	@Test(dataProvider = "productData",groups="Smoke")
	public void deleteProducts(String ProductName) throws Throwable {
		mpp = new ManageProductsPage(driver);
		
		jad = new JsonForAdminUtility();

		String USERNAME = jad.readDataFromJson("username");
		String PASSWORD = jad.readDataFromJson("password");

		hp = new HomePage(driver);
		hp.getAdminLoginLink().click();

		adlp = new AdminLoginPage(driver);
		adlp.adminLogin(USERNAME, PASSWORD);
		
		ela = new ExcelUtility();
		wlib=new WebdriverUtility();
		WebElement productLink = mpp.getManageProductsLink();
		wlib.waitForElementPresent(driver,productLink , 20);
		productLink.click();
		//String productName1 = ela.getDataFromExcel("Sheet4", 1, 0);
		wlib.waitForPageToLoad(driver);
		driver.findElement(By.xpath("//td[contains(text(),'Asian Casuals (White, White)')]/following-sibling::td[last()]//i[contains(@class,'icon-remove-sign')]")).click();
		wlib.switchToAlertAndAccept(driver);
		
	}
	@DataProvider(name = "productData")
	public Object[][] getProductData() throws EncryptedDocumentException, IOException {
		ela=new ExcelUtility();
	    int rowCount = ela.getRowCount("Sheet4"); // Get total number of rows
	    Object[][] data = new Object[rowCount][1];
	    System.out.println("R:"+rowCount);
	    for (int i = 0; i <rowCount; i++) {
	        data[i][0] = ela.getDataFromExcel("Sheet4", (i+1), 0);
	    }
	    System.out.println(data);
	    return data;
	}

}
