/**
 * 
 */
package com.UITests.EmailTool.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;


public class EmailBuilderPage extends Page {


	@FindBy(xpath = "/descendant::input[@type='text'][1]")
	private WebElement emailName;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='save name']")
	private WebElement saveName;

	@FindBy(xpath = "/descendant::label[normalize-space()='Sender name']/following-sibling::input")
	private WebElement senderNameField;

	@FindBy(xpath = "//label[contains(text(),'From: email address')]/following-sibling::*[contains(@id,'singleSelectionDropdown')]")
	private WebElement fromEmailDropdown;
	
	
	@FindBy(xpath = "//label[contains(text(),'From: email address')]/following-sibling::*[contains(@id,'singleSelectionDropdown')]/descendant::*[contains(text(),'rewards@campaignmail.test.test')]")
	private WebElement fromEmailrewardsAtCampaign;


	@FindBy(xpath = "/descendant::label[normalize-space()='Reply to: email address']/following-sibling::input")
	private WebElement replyToEmailAddressField;

	@FindBy(xpath = "/descendant::label[normalize-space()='Subject']/following-sibling::input")
	private WebElement subjectField;

	
	@FindBy(xpath="//button[@id='toggle-link-modal']")
	private WebElement addSpecialLink;
	
	@FindBy(xpath="//button[@id='toggle-survey-modal']")
	private WebElement addSurvey;
	
	@FindBy(xpath="//button[@id='insert-link-but' and .='INSERT LINK']")
	private WebElement insertLink;

	@FindBy(id = "link-parameters")
	private WebElement linkParametersDropdown;
	
	@FindBy(id = "survey-parameters")
	private WebElement surveyParametersDropdown;

	@FindBy(id = "link-label")
	private WebElement linkLabelField;
	
	@FindBy(id = "survey-label")
	private WebElement surveyLabelField;
	
	@FindBy(xpath="	//button[@id='insert-survey-but' and .='INSERT SURVEY']")
	private WebElement insertSurvey;
	
	@FindBy(xpath="//span[.='approve email']/ancestor::button")
	private WebElement approveEmail;
	
	@FindBy(xpath="//*[.='approved']")
	private WebElement emailAppproved;
	
	@FindBy(xpath="//textarea")
	private WebElement textArea;
	
	@FindBy(xpath="//span[contains(text(),'send test')]")
	private WebElement sendTest;
	
	@FindBy(xpath="	//*[.='OK']/ancestor::button")
	private WebElement OK;
	
	@FindBy(xpath="//*[.='The test email has been sent.']")
	private WebElement emailSent;


	private static final String[] TITLE_WORDS = { "Email", "Builder" };

	public EmailBuilderPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void setEmailName(String text) {
		waitFor(emailName).clear();
		emailName.sendKeys(text);
	}

	public void clickSaveName() {
		click(saveName);
	}

	public void setSenderNameField(String text) {
		waitFor(senderNameField).clear();
		senderNameField.sendKeys(text);
	}

	public void clickFromEmailDropdown() {
		click(fromEmailDropdown);
	}
	
	public void clickFromEmailrewardsAtCampaign() {
		click(fromEmailrewardsAtCampaign);
	}
	

	public void setReplyToEmailAddressField(String text) {
		waitFor(replyToEmailAddressField).clear();
		replyToEmailAddressField.sendKeys(text);
	}

	public void setSubjectField(String text) {
		waitFor(subjectField).clear();
		subjectField.sendKeys(text);
	}

	
	/*
	public void setFromEmailAddress(WebDriver driver) {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	WebElement el = scrollTo(fromEmailDropdownInput);
	//doesn't work because it's read-only
	executor.executeScript("arguments[0].setAttribute('value', 'rewards@campaignmail.test.test')",el);		
	}
	*/

	public void selectLinkParametersDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(linkParametersDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(linkParametersDropdown);
		dropdown.selectByVisibleText(text);
	}
	
	public void selectSurveyParametersDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(surveyParametersDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(surveyParametersDropdown);
		dropdown.selectByVisibleText(text);
	}
	
	

	public void setLinkLabelField(String text) {
		waitFor(linkLabelField).clear();
		linkLabelField.sendKeys(text);
	}
	
	public void setSurveyLabelField(String text) {
		waitFor(surveyLabelField).clear();
		surveyLabelField.sendKeys(text);
	}
	
	
	
	public void clickAddSpecialLink() {
		click(addSpecialLink);
	}
	
	public void clickAddSurvey() {
		click(addSurvey);
	}
	
	public void clickInsertSurvey() {
		click(insertSurvey);
	}
	
	public void clickInsertLink() {
		click(insertLink);
	}
	
	public void clickApproveEmail() {
		click(approveEmail);
	}
	
	public void clickTextArea() {
		click(textArea);
	}
	
	public void setTextArea(String text) {
		waitFor(textArea).clear();
		textArea.sendKeys(text);
	}
	
	public void clickSendTest() {
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",sendTest);		
	}
	
	public void clickOK() {
		click(OK);
	}
	
	public boolean insertServeyIsVisible() {
		return elementIsVisible(insertSurvey);
	}
	
	public boolean insertServeyIsntVisible() {
		return elementIsntVisible(insertSurvey);
	}
	
	public void waitUntilApprovedVisible() {
		waitUntilElementVisible(emailAppproved);
	}
	
	public void waitUntilEmailSentVisible() {
		waitUntilElementVisible(emailSent);
	}
	
	
}