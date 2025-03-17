package com.joom.automation.SmokeTestCases;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.joom.automation.WebDriverUtility.WebdriverUtility;
import com.joom.automation.baseutility.BaseClassForAdmin;
import com.joom.automation.generic.fileutility.ExcelUtility;
import com.joom.automation.generic.fileutility.JsonForAdminUtility;
import com.joom.automation.objectrepository.HomePage;
import com.joom.automation.objectrepository.UserHomePage;
import com.joom.automation.objectrepository.UserShoppingHomePage;
import com.joom.automation.objectrepository.UserSignUpPage;

@Listeners(com.joom.automation.listenerutility.ListenerImplementation.class)
public class UserNewContactTest extends BaseClassForAdmin { // Extend BaseClassForAdmin to use driver

    public WebdriverUtility wb = new WebdriverUtility();
    public JsonForAdminUtility js = new JsonForAdminUtility();
    public ExcelUtility ela = new ExcelUtility();
    public UserSignUpPage usp;
    
    @Test(priority = 1)
    public void userNewContactTest() throws EncryptedDocumentException, IOException, InterruptedException {
        WebDriver driver = super.driver; // Get initialized WebDriver

        HomePage hp = new HomePage(driver);
        WebElement login = hp.getLoginLink();
        wb.waitForElementPresent(driver, login);
        login.click();
        

        UserHomePage uhp = new UserHomePage(driver);
        
        // Get data from Excel
        String Fullname = ela.getDataFromExcel("Users", 2, 0);
        uhp.getFullnametxtfield().sendKeys(Fullname);

        String Email = ela.getDataFromExcel("Users", 2, 1);
        uhp.getEmailtxtfld().sendKeys(Email);

        usp = new UserSignUpPage(driver);
        WebElement signup = usp.getSignUpbtn();
        wb.scrollToElements(driver, signup);
        signup.click();

        String Contactno = ela.getDataFromExcel("Users", 2, 2);
        uhp.getContactnotxtfld().sendKeys(Contactno);

        String password = ela.getDataFromExcel("Users", 2, 3);
        uhp.getPasswordtxtfld().sendKeys(password);

        String confirmpassword = ela.getDataFromExcel("Users", 2, 4); // Fixed Sheet Name
        uhp.getConfirmpasswordtxtfld().sendKeys(confirmpassword);

        uhp.getSubmitBtn().click();
        wb.switchtoAlertAndAccept(driver);

        SoftAssert sa = new SoftAssert();
        sa.assertTrue(driver.getCurrentUrl().contains("AppServer/Online_Shopping_Application/login.php"),
                "New Contact was not processed successfully!");

        Reporter.log("New Contact passed successfully.", true);

        sa.assertAll(); // Ensure assertion failures are reported
    }
}
