package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage {
	WebDriver driver;

	public UserHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//input[@name='fullname']")
	private WebElement Fullnametxtfield;

	@FindBy(xpath = "//input[@name='emailid']")
	private WebElement emailtxtfld;

	@FindBy(id = "contactno")
	private WebElement contactnotxtfld;

	@FindBy(id = "password")
	private WebElement passwordtxtfld;

	@FindBy(id = "confirmpassword")
	private WebElement confirmpasswordtxtfld;

	@FindBy(id = "submit")
	private WebElement submitBtn;

	public WebElement getFullnametxtfield() {
		return Fullnametxtfield;
	}

	public WebElement getEmailtxtfld() {
		return emailtxtfld;
	}

	public WebElement getContactnotxtfld() {
		return contactnotxtfld;
	}

	public WebElement getPasswordtxtfld() {
		return passwordtxtfld;
	}

	public WebElement getConfirmpasswordtxtfld() {
		return confirmpasswordtxtfld;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}

}
