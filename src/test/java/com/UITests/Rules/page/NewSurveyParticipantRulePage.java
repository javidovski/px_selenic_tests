package com.UITests.Rules.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class NewSurveyParticipantRulePage extends Page {

	@FindBy(name = "pxrule_ruleName")
	private WebElement pxruleRuleNameField;

	@FindBy(name = "messageSlots_0")
	private WebElement messageCheckbox;

	@FindBy(name = "messageSlots_0_templateId")
	private WebElement campaignMessageTemplateropdown;

	@FindBy(name = "messageSlots_0_hasPull")
	private WebElement hasPullCheckbox;

	@FindBy(name = "messageSlots_0_odds")
	private WebElement oddsField;

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Event to trigger rule on\"]]/td[2]/input[9]")
	private WebElement webRegistration;

	@FindBy(css = "input[value='Submit']")
	private WebElement submitButton;
	
	@FindBy(linkText = "Template Rules")
	private WebElement templateRulesLink;


	private static final String[] TITLE_WORDS = { "New", "Survey", "Participant", "Rule" };

	public NewSurveyParticipantRulePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public static NewSurveyParticipantRulePage using(WebDriver driver) {
		return new NewSurveyParticipantRulePage(driver);
	}

	public NewSurveyParticipantRulePage setPxruleRuleNameField(String text) {
		waitFor(pxruleRuleNameField).clear();
		pxruleRuleNameField.sendKeys(text);
		return this;
	}

	public NewSurveyParticipantRulePage clickMessageCheckbox() {
		click(messageCheckbox);
		return this;
	}

	public NewSurveyParticipantRulePage selectMessageTemplateDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(campaignMessageTemplateropdown).getFirstSelectedOption().getText().trim()
				.length() >= 0);
		Select dropdown = new Select(campaignMessageTemplateropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public NewSurveyParticipantRulePage clickMessageHasPullCheckbox() {
		click(hasPullCheckbox);
		return this;
	}

	public NewSurveyParticipantRulePage setMessageOddsField(String text) {
		waitFor(oddsField).clear();
		oddsField.sendKeys(text);
		return this;
	}

	public NewSurveyParticipantRulePage clickWebRegistration() {
		click(webRegistration);
		return this;
	}

	public NewSurveyParticipantRulePage clickSubmitButton() {
		click(submitButton);
		return this;
	}
	
	public NewSurveyParticipantRulePage clickTemplateRulesLink() {
		click(templateRulesLink);
		return this;
	}

}