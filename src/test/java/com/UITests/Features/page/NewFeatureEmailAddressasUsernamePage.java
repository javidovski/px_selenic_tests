/**
 * 
 */
package com.UITests.Features.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class NewFeatureEmailAddressasUsernamePage extends Page {

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;
	

	private static final String[] TITLE_WORDS = { "New", "Feature", "\"Email", "Address", "as", "Username\"" };

		
	public NewFeatureEmailAddressasUsernamePage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}