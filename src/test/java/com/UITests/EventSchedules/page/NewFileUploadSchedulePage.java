package com.UITests.EventSchedules.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class NewFileUploadSchedulePage extends Page {

	
	@FindBy(name = "schedule_name")
	private WebElement scheduleNameField;
	
	@FindBy(name = "schedule_hours")
	private WebElement scheduleHoursField;
	
	@FindBy(name = "schedule_minutes")
	private WebElement scheduleMinutesField;
	
	@FindBy(name = "schedule_daysOfMonth")
	private WebElement scheduleDaysOfMonthField;
	
	@FindBy(name = "monthsSet")
	private WebElement monthsSet;
	
	@FindBy(name = "daysOfWeekSet")
	private WebElement daysOfWeekSet;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	private WebElement submit;
	
	
	
	private static final String[] TITLE_WORDS = { "New", "Scheduled", "File", "Upload", "Schedule"};
	
	public static NewFileUploadSchedulePage using(WebDriver driver) {
		return new NewFileUploadSchedulePage(driver);
	}
	
	public NewFileUploadSchedulePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public NewFileUploadSchedulePage setScheduleNameField(String text) throws InterruptedException {
		waitFor(scheduleNameField).clear();
		scheduleNameField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	
	public NewFileUploadSchedulePage setScheduleHoursField(String text) throws InterruptedException {
		waitFor(scheduleHoursField).clear();
		scheduleHoursField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewFileUploadSchedulePage setScheduleMinutesField(String text) throws InterruptedException{
		waitFor(scheduleMinutesField).clear();
		scheduleMinutesField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewFileUploadSchedulePage setScheduleDaysOfMonthField(String text) throws InterruptedException{
		waitFor(scheduleDaysOfMonthField).clear();
		scheduleDaysOfMonthField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewFileUploadSchedulePage selectMonths(String value) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(monthsSet).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(monthsSet);
		dropdown.selectByValue(value);
		sleep(500);
		return this;
		
	}
	
	public NewFileUploadSchedulePage selectDaysOfWeek(String text) throws InterruptedException{
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(daysOfWeekSet).getFirstSelectedOption().getText().trim().length() > 0);
		Select dropdown = new Select(daysOfWeekSet);
		dropdown.selectByVisibleText(text);
		sleep(500);
		return this;
	}
	
	public NewFileUploadSchedulePage clickSubmit() {
		click(submit);
		return this;
	}
	
	
	
}
