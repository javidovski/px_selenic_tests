/**
 * 
 */
package com.UITests.Users.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class SelectUserPage extends Page{

	@FindBy(xpath = "//img[@title = 'Upload New']")
	private WebElement uploadNew;


	private static final String[] TITLE_WORDS = { "Select", "User" };

	public SelectUserPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickUploadNew() {
		click(uploadNew);
	}

}