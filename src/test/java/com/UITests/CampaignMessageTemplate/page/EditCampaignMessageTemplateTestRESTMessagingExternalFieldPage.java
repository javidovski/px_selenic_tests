/**
 * 
 */
package com.UITests.CampaignMessageTemplate.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class EditCampaignMessageTemplateTestRESTMessagingExternalFieldPage extends Page {

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Email Template\"]]/td[2]/select")
	private WebElement dropdown1;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;
	

	private static final String[] TITLE_WORDS = { "Edit", "Campaign", "Message", "Template", "\"Test", "RESTMessaging",
			"External", "Field\"" };
	
	public EditCampaignMessageTemplateTestRESTMessagingExternalFieldPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void selectDropdown1(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(dropdown1).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(dropdown1);
		dropdown.selectByVisibleText(text);
	}

	public void clickSubmitButton() {
		click(submitButton);
	}

}