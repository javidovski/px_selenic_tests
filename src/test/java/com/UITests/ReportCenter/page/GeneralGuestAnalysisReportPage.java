/**
 * 
 */
package com.UITests.ReportCenter.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class GeneralGuestAnalysisReportPage extends Page {

	@FindBy(linkText = "Download Report")
	private WebElement downloadReportLink;

	private static final String[] TITLE_WORDS = { "General", "Guest", "Analysis", "Report" };

	public GeneralGuestAnalysisReportPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickDownloadReportLink() {
		click(downloadReportLink);
	}

}