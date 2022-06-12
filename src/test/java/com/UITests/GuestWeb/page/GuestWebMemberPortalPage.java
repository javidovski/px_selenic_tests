/**
 * Guest Page where users can either `Register My Card` OR `Account Login`
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class GuestWebMemberPortalPage extends Page{

	@FindBy(css = ".visible-md [title='Register']")
	private WebElement register;
	
	@FindBy(linkText = "Login")
	private WebElement loginLink;


	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Member", "Portal" };

	public GuestWebMemberPortalPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickRegister() {
		try {
		  click(register);
		}
		catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",register);
		}
	}
	
	public void clickLoginLink() {
		click(loginLink);
	}

}