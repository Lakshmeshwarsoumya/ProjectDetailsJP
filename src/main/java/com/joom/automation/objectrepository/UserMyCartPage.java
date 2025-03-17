package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserMyCartPage {
	WebDriver driver;
	public UserMyCartPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//button[text()='PROCCED TO CHEKOUT']")
	private WebElement proceedToCheckoutBtn;
	
	@FindBy(xpath="//input[@name='submit']")
	private WebElement updateShoppingCartBtn;
	
	
	public WebElement getUpdateShoppingCartBtn() {
		return updateShoppingCartBtn;
	}


	public WebElement getProceedToCheckoutBtn() {
		return proceedToCheckoutBtn;
	}
	
	
	
	

}
