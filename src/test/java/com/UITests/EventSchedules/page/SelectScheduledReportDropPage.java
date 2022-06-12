package com.UITests.EventSchedules.page;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class SelectScheduledReportDropPage extends Page {
	
	@FindBy(xpath = "//span[.='New Scheduled Report Drop']")
	private WebElement newReportDrop;
	
	
	
	private static final String[] TITLE_WORDS = { "Select", "Scheduled",  "Report", "Drop"};
	
	public SelectScheduledReportDropPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void clickNewReportDrop() {
		click(newReportDrop);
	}
	
	
	
	

}
