package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubCategoryPage {
	@FindBy(xpath = "//select[@name='category']")
	private WebElement categoryDropdown;

	@FindBy(xpath = "//input[@placeholder='Enter SubCategory Name']")
	private WebElement subCategoryDropdown;

	@FindBy(xpath = "//button[text()='Create']")
	private WebElement createButton;
	
	@FindBy(xpath="//strong[contains(text(),'Well done!')]")
	private WebElement confirmMsg;

	public SubCategoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public WebElement getCategoryDropdown() {
		return categoryDropdown;
	}

	public WebElement getSubCategoryDropdown() {
		return subCategoryDropdown;
	}

	public WebElement getCreateButton() {
		return createButton;
	}

	public WebElement getConfirmMsg() {
		return confirmMsg;
	}
	

}
