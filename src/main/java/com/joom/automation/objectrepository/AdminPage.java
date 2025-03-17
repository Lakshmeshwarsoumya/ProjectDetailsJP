package com.joom.automation.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage {
	WebDriver driver;

	@FindBy(xpath = "(//a[@data-toggle='collapse'])[2]")
	private WebElement orderMgmtLink;

	@FindBy(xpath = "//a[@href='todays-orders.php']")
	private WebElement todaysOrdersLink;

	@FindBy(xpath = "//a[text()=' Create Category ']")
	private WebElement createCategoryLink;

	@FindBy(xpath = "//a[@href='subcategory.php']")
	private WebElement subCategoryLink;

	@FindBy(xpath = "//a[text()='Insert Product ']")
	private WebElement inserProductLink;

	@FindBy(xpath = "//img[@class='nav-avatar']")
	private WebElement logoutIcon;

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	public AdminPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	public WebElement getOrderMgmtLink() {
		return orderMgmtLink;
	}

	public WebElement getTodaysOrdersLink() {
		return todaysOrdersLink;
	}

	public WebElement getCreateCategoryLink() {
		return createCategoryLink;
	}

	public WebElement getSubCategoryLink() {
		return subCategoryLink;
	}

	public WebElement getInserProductLink() {
		return inserProductLink;
	}

	public WebElement getLogoutIcon() {
		return logoutIcon;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public void todaysOrders() {

		getOrderMgmtLink().click();
		getTodaysOrdersLink().click();
	}

	public void logout() {
		logoutIcon.click();
		logoutLink.click();
	}

}
