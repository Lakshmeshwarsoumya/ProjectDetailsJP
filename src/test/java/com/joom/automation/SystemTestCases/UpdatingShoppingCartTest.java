package com.joom.automation.SystemTestCases;

import java.time.Duration;



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
import com.joom.automation.objectrepository.UserFashionCategoryPage;
import com.joom.automation.objectrepository.UserMyCartPage;
import com.joom.automation.objectrepository.UserShoppingHomePage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class UpdatingShoppingCartTest extends  BaseClassForAdmin{
	
	@Test(groups="System Test")
	public void updatingShoppingCartTest() throws InterruptedException {
		System.out.println("Execute method");
		jad=new JsonForAdminUtility();
		wlib=new WebdriverUtility();
		//implicit wait
		
		driver.manage().window().maximize();
		
		//click on fashion
		 ushp= new UserShoppingHomePage(driver);
		ushp.getFashionbtn().click();
		
		//click on saree product
		ufcp=new UserFashionCategoryPage(driver);
		Thread.sleep(2000);
		//it is not scrolling
		WebElement shoe= ufcp.getShoebtn();
		wlib=new WebdriverUtility();
		//wb.scrollToElement(driver, saree);
		//saree.click();
		Thread.sleep(2000);
		//wb.scrollByAmountt(driver, saree);
		wlib.scrollToElements(driver,shoe);
		shoe.click();
		Thread.sleep(2000);
		//product has been added to the cart popup
		//wb.switchtoAlertAndAccept(driver);
		wlib.scrollToElements(driver, shoe);
		ufcp.getAddtoCartBtn().click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
		    wait.until(ExpectedConditions.alertIsPresent()); 
		    Alert alert = driver.switchTo().alert();
		    alert.accept();
		} catch (Exception e) {
		    System.out.println("No alert appeared.");
		}
		Thread.sleep(2000);
		
	
		//click on updateshopping cart button
	     umcp=new UserMyCartPage(driver);
		Thread.sleep(2000);
		WebElement update = umcp.getUpdateShoppingCartBtn();
		wlib= new WebdriverUtility();
		wlib.scrollByAmount(driver, update);
		umcp.getUpdateShoppingCartBtn().click();
		Thread.sleep(2000);
		//your cart has been updated popup
		wlib.switchtoAlertAndAccept(driver);
		
		SoftAssert sa= new SoftAssert();
  		sa.assertTrue(driver.getCurrentUrl().contains("Online_Shopping_Application/my-cart.php"), "updating was not processed successfully!");

        Reporter.log("Updating the cart test passed successfully.",true);

		
		
		
		
		
		
	}

}
