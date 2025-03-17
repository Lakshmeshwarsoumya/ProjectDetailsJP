package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPaymentMethodPage {
	WebDriver driver;
 public UserPaymentMethodPage(WebDriver driver) {
	 this.driver=driver;
	 PageFactory.initElements(driver,this);
	
}
 
 @FindBy(xpath="//input[@checked=\"checked\"]")
 private WebElement CODradiobtn;
 
 @FindBy(xpath="//input[@value='Internet Banking']")
 private WebElement InternetBankingRadioBtn;
 
 @FindBy(xpath="//input[@value=\'Debit / Credit card\']")
 private WebElement DebitcreditRadiobtn;
 
 @FindBy(xpath="//input[@type='submit']")
 private WebElement SubmitBtn;
 
 
 
public WebElement getSubmitBtn() {
	return SubmitBtn;
}

public WebElement getCODradiobtn() {
	return CODradiobtn;
}

public WebElement getInternetBankingRadioBtn() {
	return InternetBankingRadioBtn;
}

public WebElement getDebitcreditRadiobtn() {
	return DebitcreditRadiobtn;
}
 
 
 
}
