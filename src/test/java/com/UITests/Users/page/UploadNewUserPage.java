/**
 * 
 */
package com.UITests.Users.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class UploadNewUserPage extends Page{

	@FindBy(xpath = "//input[@name='upload_file']")
	private WebElement field;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;


	private static final String[] TITLE_WORDS = { "Upload", "New", "User" };

	public UploadNewUserPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void setField(String text) {
		waitFor(field).clear();
		field.sendKeys(text);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}