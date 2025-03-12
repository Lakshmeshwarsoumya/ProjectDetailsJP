package com.joom.automation.IntegrationTestCases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtilityForAdmin;
import com.joom.automation.objectrepository.AdminPage;
import com.joom.automation.objectrepository.InsertProductPage;

public class InsertProduct extends BaseClassForAdmin {
	@Test
	public void insertProduct() throws EncryptedDocumentException, IOException, InterruptedException {
		adp = new AdminPage(driver);
		ela = new ExcelUtilityForAdmin();
		String category = ela.getDataFromExcel("Sheet1", 1, 0);
		adp.getInserProductLink().click();

		InsertProductPage ipp = new InsertProductPage(driver);
		wlib = new WebdriverUtility();
		wlib.select(ipp.getCategoryDropDown(), category);
		Thread.sleep(2000);
		String subcategory = ela.getDataFromExcel("Sheet1", 1, 2);

		wlib.select(ipp.getSubCategoryDropDown(), subcategory);
		String productName = ela.getDataFromExcel("Sheet1", 1, 3);

		ipp.getProductNameTextField().sendKeys(productName);

		String productCompany = ela.getDataFromExcel("Sheet1", 1, 4);

		ipp.getProductCompanyTextField().sendKeys(productCompany);

		String productPriceBeforeDiscount = ela.getDataFromExcel("Sheet1", 1, 5);

		ipp.getProductPriceBd().sendKeys(productPriceBeforeDiscount);

		String productPriceAfterDiscount = ela.getDataFromExcel("Sheet1", 1, 6);
		ipp.getProductpriceAd().sendKeys(productPriceAfterDiscount);

		String productDiscription = ela.getDataFromExcel("Sheet1", 1, 1);

		ipp.getDescriptionTextField().sendKeys(productDiscription);
		String productShippingCharge = ela.getDataFromExcel("Sheet1", 1, 7);
		WebElement insertButton = driver.findElement(By.xpath("//button[text()='Insert']"));
		
		Actions act=new Actions(driver);
		act.scrollToElement(insertButton);

		ipp.getProductShippingChargeTextField().sendKeys(productShippingCharge);
		String productAvailbility = ela.getDataFromExcel("Sheet1", 1, 8);
		wlib.select(ipp.getProductAvailabilityTF(), productAvailbility);
		
		
		WebElement fileInput1 = ipp.getImage1();
		fileInput1.sendKeys("D:\\HP1\\Desktop\\vivo-mobile-phone-1000x1000.webp");

        WebElement fileInput2 = ipp.getImage2();
        fileInput2.sendKeys("D:\\HP1\\Desktop\\vivo-mobile-phone-1000x1000.webp");

        WebElement fileInput3 = ipp.getImage3();
        fileInput3.sendKeys("D:\\HP1\\Desktop\\vivo-mobile-phone-1000x1000.webp");

        // Click the Insert butto/.n
       
        insertButton.click();

	}

}
