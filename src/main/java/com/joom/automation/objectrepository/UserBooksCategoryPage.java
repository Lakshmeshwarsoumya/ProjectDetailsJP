package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserBooksCategoryPage {
	WebDriver driver;
	public UserBooksCategoryPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
	
		this.driver=driver;
		PageFactory.initElements(driver,this);
		
		
	}
	@FindBy(xpath="//a[text()='Demo book']")
	private WebElement Demobooklink;
	
	@FindBy(xpath = "//a[text()='Thea Stilton and the Tropical Treasure '] ")
	private WebElement TheaStiltonlink;
	
	@FindBy(xpath="(//button[text()='Add to cart'])[1]")
	private WebElement demobookaddToCartbtn;
	
	
	public WebElement getDemobooklink() {
		return Demobooklink;
	}

	public WebElement getTheaStiltonlink() {
		return TheaStiltonlink;
	}
	
	
	
	

}
