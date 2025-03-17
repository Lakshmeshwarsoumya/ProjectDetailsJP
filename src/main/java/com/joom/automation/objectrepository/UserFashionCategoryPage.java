package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserFashionCategoryPage {
	
	WebDriver driver;
	 public UserFashionCategoryPage(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver,this);
		}
	
	@FindBy(xpath="(//div[@class='image'])[1]")
	private WebElement sareeBtn;
	
	@FindBy(xpath=" //a[text()=' ADD TO CART']")
	private WebElement addtoCartBtn;
	
	@FindBy(xpath="//a[text()='Asian Casuals  (White, White)']")
	private WebElement shoebtn;
	
	public WebElement getShoebtn() {
		return shoebtn;
	}

	public WebElement getAddtoCartBtn() {
		return addtoCartBtn;
	}

	public WebElement getSareeBtn() {
		return sareeBtn;
	}
	
	

}
