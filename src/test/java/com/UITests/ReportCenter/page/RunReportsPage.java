package com.UITests.ReportCenter.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;

public class RunReportsPage extends Page {
	
	@FindBy(xpath = "//table/tbody/tr[4]/td[3]/a[1]")
	private WebElement guestAnalysisDetailLink;
	
	private static final String[] TITLE_WORDS = { "Run", "Reports" };

	public RunReportsPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void clickGuestAnalysisDetailLink() {
		click(guestAnalysisDetailLink);
	}

}
