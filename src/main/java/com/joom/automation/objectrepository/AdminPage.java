package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	@FindBy(xpath = "(//a[@data-toggle='collapse'])[2]")
	private WebElement orderMgmtLink;

	@FindBy(xpath = "//a[@href='todays-orders.php']")
	private WebElement todaysOrdersLink;

	public AdminPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public WebElement getOrderMgmtLink() {
		return orderMgmtLink;
	}

	public WebElement getTodaysOrdersLink() {
		return todaysOrdersLink;
	}

}
