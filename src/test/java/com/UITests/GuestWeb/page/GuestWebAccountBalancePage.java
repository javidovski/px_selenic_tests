/**
 * Guest Account Balance page
 * 		This is the page that appears after the log in
 * 		Can view account info
 */
package com.UITests.GuestWeb.page;

import com.UITests.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuestWebAccountBalancePage extends Page {

	@FindBy(className = "no-border")
	private WebElement navCard;

	@FindBy(xpath = "/descendant::span[@class='userInfoParameter'][2]")
	private WebElement welcomeSectionCardField;

	@FindBy(linkText = "Logout")
	private WebElement logoutLink;

	@FindBy(xpath = "/descendant::span[@class='userInfoParameter'][6]")
	private WebElement manageSectionCardField;

	@FindBy(css = ".storedValue .col-md-2")
	private WebElement storedValue;

	@FindBy(css = ".reloadCard .btn")
	private WebElement reloadCardButton;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Account", "Balance" };

	public GuestWebAccountBalancePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	// getters
	public String getNavCardAccountNum() {
		return waitFor(navCard).getText();
	}

	public String getWelcomeSectionCard() {
		return waitFor(welcomeSectionCardField).getText();
	}

	
	public String getManageSectionCard() {
		return waitFor(manageSectionCardField).getText();
	}
	
	public String getStoredValue() {
		return waitFor(storedValue).getText();
	}
	


	// actions
	public void clickLogoutLink() {
		click(logoutLink);
	}

	public void clickReloadCardBtn() {
		click(reloadCardButton);
	}
}