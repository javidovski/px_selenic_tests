package com.UITests.EventSchedules.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class SelectScheduledFileUploadPage extends Page{

	@FindBy(name = "newType")
	private WebElement newFileUploadTypeDropdown;

	@FindBy(xpath = "//input[@type='submit' and @value='New']")
	private WebElement newUploadButton;

	private static final String[] TITLE_WORDS = { "Select", "Scheduled",  "File", "Upload"};

	public SelectScheduledFileUploadPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void selectNewUploadType(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(newFileUploadTypeDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(newFileUploadTypeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void clickNewUploadButton() {
		click(newUploadButton);
	}

}
