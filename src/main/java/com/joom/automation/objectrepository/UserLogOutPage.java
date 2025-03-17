package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserLogOutPage {
	
	WebDriver driver = null;
	public UserLogOutPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(xpath="//a[text()='Logout']")
	private WebElement logoutlink;
	
	
	public WebElement getLogoutlink() {
		return logoutlink;
	}
	
	

}
