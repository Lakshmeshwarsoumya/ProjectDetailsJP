package com.joom.automation.SystemTestCases;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.generic.fileutility.JsonForUserUtility;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.UserBooksCategoryPage;
import com.joom.automation.objectrepository.UserLoginPage;
import com.joom.automation.objectrepository.UserMyCartPage;
import com.joom.automation.objectrepository.UserPaymentMethodPage;
import com.joom.automation.objectrepository.UserProductDetailsPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;
@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class ShoppingCarttTest extends BaseClassForAdmin {
	
	@Test(groups="System Test")
	public void shoppingCarttTest() throws InterruptedException, FileNotFoundException, IOException, ParseException {
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
		wlib = new WebdriverUtility();

		wlib.waitForPageToLoad(driver);
		driver.manage().window().maximize();

		
		//click on books button
	 ushp = new UserShoppingHomePage(driver);
		ushp.getBooksbtn().click();
		
		//click on demo book
		 ubcp= new UserBooksCategoryPage(driver);
		WebElement demobook = ubcp.getDemobooklink();
		
	
		wlib.scrollToElements(driver,demobook);
		ubcp.getDemobooklink().click();
		Thread.sleep(2000);
		
		//click on add to cart
		updp= new UserProductDetailsPage(driver);
        WebElement addCart = updp.getAddtocartbtn();
      
  		wlib.scrollToElements(driver,addCart);
  		updp.getAddtocartbtn().click();
  		Thread.sleep(2000);
          
          //alert popup ie.product has been added to cart 
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  		try {
  		    wait.until(ExpectedConditions.alertIsPresent()); 
  		    Alert alert = driver.switchTo().alert();
  		    alert.accept();
  		} catch (Exception e) {
  		    System.out.println("No alert appeared.");
  		}
  		
  		//scroll to proceed to checkout
  		 umcp= new UserMyCartPage(driver);
  		WebElement checkout = umcp.getProceedToCheckoutBtn();
  		
  		wlib= new WebdriverUtility();
  		wlib.scrollToElements(driver, checkout);
  		umcp.getProceedToCheckoutBtn().click();
  		
  		//select mode of payment and clock on  submit button
  		upmp=new UserPaymentMethodPage(driver);
  		upmp.getCODradiobtn().click();
  		upmp.getSubmitBtn().click();
  		
  	SoftAssert sa= new SoftAssert();
  		sa.assertTrue(driver.getCurrentUrl().contains("Online_Shopping_Application/order-history.php"), "Payment was not processed successfully!");

        Reporter.log("Shopping cart test passed successfully.",true);
        sa.assertAll();

	}	
}	

		
		
		
		
		
		 
	


