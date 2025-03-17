package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserShoppingHomePage {
	WebDriver driver;

	public UserShoppingHomePage(WebDriver driver) {
	this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Home")
	private WebElement homebtn;
	
	@FindBy(xpath="//a[text()=' Books']")
	private WebElement Booksbtn;
	
	@FindBy(xpath = "//a[text()=' Fashion']")
	private WebElement Fashionbtn;
	
	@FindBy(xpath="//input[@class='search-field']")
	private WebElement seachtxtfld;
	
	@FindBy(xpath="(//a[contains(text(),'Login')])[1]")
	private WebElement loginlink;
	
	@FindBy(xpath="//button[@name='search']")
	private WebElement searchbtn;
	

	
	public WebElement getLoginbtn() {
		return searchbtn;
	}

	public WebElement getLoginlink() {
		return loginlink;
	}

	public WebElement getSeachtxtfld() {
		return seachtxtfld;
	}

	public WebElement getHomebtn() {
		return homebtn;
	}

	public WebElement getBooksbtn() {
		return Booksbtn;
	}

	public WebElement getFashionbtn() {
		return Fashionbtn;
	}
	
	

	


}
