package com.UITests.GuestWeb.page;


import org.openqa.selenium.WebDriver;
import com.UITests.page.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GuestWebAccountBalance extends Page {
	
	
	@FindBy(xpath="//a[text()='Click to take this survey to open in multiple tabs']")
	private WebElement surveyLink;
	
	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Account", "Balance" };
	
	public GuestWebAccountBalance(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public String getSurveyLinkHref() {
		return surveyLink.getAttribute("href");
	}
	
	public void clickSurveyLink() {
		click(surveyLink);
	}

}
