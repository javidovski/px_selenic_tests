/**
 * Guest Login Page
 */
package com.UITests.GuestWeb.page;

import com.UITests.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuestLoginPage extends Page {

	@FindBy(xpath = "//input[@placeholder='Username']")
	private WebElement userNameField;

	@FindBy(xpath = "//input[@placeholder='Password']")
	private WebElement passWordField;

	@FindBy(id = "loginFormSubmitButton")
	private WebElement loginButton;

	@FindBy(linkText = "Click here to request a new password.")
	private WebElement requestNewPasswordLink;

	@FindBy(id = "lift__noticesContainer___error")
	private WebElement loginErrorElement;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Login" };

	public GuestLoginPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	// setters
	public void setUsernameField(String text) {
		waitFor(userNameField).clear();
		userNameField.sendKeys(text);
	}
	
	public void setPasswordField(String text) {
		waitFor(passWordField).clear();
		passWordField.sendKeys(text);
	}


	// getters
	public String getLoginErrorMessage() {
		return waitFor(loginErrorElement).getText();
	}

	// actions
	public void clickLoginButton() throws InterruptedException {
		sleep(500);
		click(loginButton);
		sleep(500);
	}

	public void clickRequestNewPassword() {
		click(requestNewPasswordLink);
	}


	/**
	 * This method will login to the Guest Site and requires correct credentials to be passed in.
	 * 
	 * @param userName the username {@code String} 
	 * @param password the password {@code String} 
	 * @throws InterruptedException
	 */
	public void login(String userName, String password) throws InterruptedException {
		setUsernameField(userName);
		setPasswordField(password);
		clickLoginButton();
	}
}