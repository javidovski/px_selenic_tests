package com.UITests.EventSchedules.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class SelectSchedulePage extends Page {
	
	@FindBy(name = "newType")
	private WebElement newScheduleTypeDropdown;
	
	@FindBy(xpath = "//input[@type='submit' and @value='New']")
	private WebElement newScheduleButton;
	
	private static final String[] TITLE_WORDS = { "Select", "Schedule" };

	public SelectSchedulePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void selectNewScheduleType(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(newScheduleTypeDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(newScheduleTypeDropdown);
		dropdown.selectByVisibleText(text);
	}
	
	public void clickNewScheduleButton() {
		click(newScheduleButton);
	}

}
