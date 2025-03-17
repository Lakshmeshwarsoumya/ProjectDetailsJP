package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserProductDetailsPage {
	WebDriver driver;
public UserProductDetailsPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
}

@FindBy(xpath = "//a[text()=' ADD TO CART']")
private WebElement addtocartbtn;

@FindBy(xpath="//a[@data-placement='right']")
private WebElement wishlistBtn;

public WebElement getWishlistBtn() {
	return wishlistBtn;
}

public WebElement getAddtocartbtn() {
	return addtocartbtn;
}


	

}
