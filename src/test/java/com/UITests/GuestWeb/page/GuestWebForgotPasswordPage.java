/**
 *  Guest Forgot Password Page
 */
package com.UITests.GuestWeb.page;

import com.UITests.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuestWebForgotPasswordPage extends Page {

	@FindBy(xpath = "//form[1]/div[2]/div[1]")
	private WebElement userNameMessage;

	@FindBy(xpath = "//form[2]/div/div[1]")
	private WebElement cardNumMessage;

	@FindBy(xpath = "//form[3]/div/div[1]")
	private WebElement emailMessage;

	@FindBy(linkText = "Login")
	private WebElement loginLink;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Forgot", "Password" };

	public GuestWebForgotPasswordPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public String getUserNameMessage() {
		return waitFor(userNameMessage).getText();
	}

	public String getCardNumMessage() {
		return waitFor(cardNumMessage).getText();
	}

	public String getEmailMessage() {
		return waitFor(emailMessage).getText();
	}

	public void clickLoginLink() {
		click(loginLink);
	}

}