/**
 * 
 */
package com.UITests.EmailTool.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class EmailToolPage extends Page{

	@FindBy(css = "input[value='Create New Email']")
	private WebElement createNewEmailButton;

	private static final String[] TITLE_WORDS = { "Email", "Tool" };

	public EmailToolPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickCreateNewEmailButton() {
		click(createNewEmailButton);
	}

}