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

public class NewCampaignMessageTemplatePage extends Page {

	@FindBy(name = "label")
	private WebElement labelField;

	@FindBy(name = "eventTypeEnumId")
	private WebElement eventTypeEnumIdDropdown;

	@FindBy(name = "surveyId")
	private WebElement surveyIdDropdown;

	@FindBy(name = "pullBody")
	private WebElement pullBodyField;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;


	private static final String[] TITLE_WORDS = { "New", "Campaign", "Message", "Template" };
	
	public NewCampaignMessageTemplatePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public static NewCampaignMessageTemplatePage using(WebDriver driver) {
		return new NewCampaignMessageTemplatePage(driver);
	}

	

	public NewCampaignMessageTemplatePage setLabelField(String text) {
		waitFor(labelField).clear();
		labelField.sendKeys(text);
		return this;
	}

	public NewCampaignMessageTemplatePage selectEventTypeEnumIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(eventTypeEnumIdDropdown).getFirstSelectedOption().getText().trim()
				.length() > 0);
		Select dropdown = new Select(eventTypeEnumIdDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public NewCampaignMessageTemplatePage selectSurveyIdDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(surveyIdDropdown).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(surveyIdDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public NewCampaignMessageTemplatePage setPullBodyField(String text) {
		waitFor(pullBodyField).clear();
		pullBodyField.sendKeys(text);
		return this;
	}

	public NewCampaignMessageTemplatePage clickSubmitButton() {
		click(submitButton);
		return this;
	}

}