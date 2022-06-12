package com.UITests.EventSchedules.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;

public class NewScheduledReportDropPage extends Page {
	
	@FindBy(name = "url")
	private WebElement urlField;
	
	@FindBy(name = "clearUsername")
	private WebElement usernameField;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Submit']")
	private WebElement submit;
	
	private static final String[] TITLE_WORDS = { "New", "Scheduled",  "Report", "Drop"};
	
	public static NewScheduledReportDropPage using(WebDriver driver) {
		return new NewScheduledReportDropPage(driver);		
	}
	
	public NewScheduledReportDropPage (WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	
	public NewScheduledReportDropPage setURLField(String text) throws InterruptedException {
		waitFor(urlField).clear();
		urlField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewScheduledReportDropPage setUsernameField(String text) throws InterruptedException {
		waitFor(usernameField).clear();
		usernameField.sendKeys(text);
		sleep(500);
		return this;
	}
	
	public NewScheduledReportDropPage clickSubmit() {
		click(submit);
		return this;
	}

}
