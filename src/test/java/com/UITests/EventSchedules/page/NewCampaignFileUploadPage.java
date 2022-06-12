package com.UITests.EventSchedules.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class NewCampaignFileUploadPage extends Page {

	
	@FindBy(name = "label")
	private WebElement labelField;
	
	@FindBy(name = "scheduleId")
	private WebElement scheduleDropdown;
	
	@FindBy(name = "dropId")
	private WebElement filePickupSiteDropdown;
	
	@FindBy(name = "notifyAddress")
	private WebElement notifyAddressField;
	
	@FindBy(name = "failureNotifyAddress")
	private WebElement failureNotifyAddressField;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	private WebElement submit;
	
	
	private static final String[] TITLE_WORDS = { "New", "Campaign", "File", "Upload"};

	public static NewCampaignFileUploadPage using(WebDriver driver) {
		return new NewCampaignFileUploadPage(driver);		
	}
	
	public NewCampaignFileUploadPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	
	public NewCampaignFileUploadPage setLabelField(String text) throws InterruptedException {
		waitFor(labelField).clear();
		labelField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewCampaignFileUploadPage selectSchedule(String text) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(scheduleDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(scheduleDropdown);
		dropdown.selectByVisibleText(text);
		sleep(500);
		return this;
	}
	
	public NewCampaignFileUploadPage selectFilePickupSite(String text) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(filePickupSiteDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(filePickupSiteDropdown);
		dropdown.selectByVisibleText(text);
		sleep(500);
		return this;
	}
	
	public NewCampaignFileUploadPage setNotifyAddressField(String text) throws InterruptedException{
		waitFor(notifyAddressField).clear();
		notifyAddressField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewCampaignFileUploadPage setFailureNotifyAddressField(String text)throws InterruptedException {
		waitFor(failureNotifyAddressField).clear();
		failureNotifyAddressField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewCampaignFileUploadPage clickSubmit() throws InterruptedException{
		click(submit);
		sleep(500);
		return this;
	}

}
