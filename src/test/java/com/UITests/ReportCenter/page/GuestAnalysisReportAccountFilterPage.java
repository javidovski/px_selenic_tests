/**
 * 
 */
package com.UITests.ReportCenter.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class GuestAnalysisReportAccountFilterPage extends Page {

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Select a Filter']")
	private WebElement selectAFilter;

	@FindBy(xpath = "/descendant::li[normalize-space(.)='Account Filter for ATs']")
	private WebElement accountFilterForATs;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Go']")
	private WebElement goButton;


	private static final String[] TITLE_WORDS = { "Guest", "Analysis", "Report", "Account", "Filter" };

	public GuestAnalysisReportAccountFilterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickSelectAFilter() {
		click(selectAFilter);
	}

	public void clickAccountFilterForATs() {
		click(accountFilterForATs);
	}

	public void clickGoButton() {
		click(goButton);
	}

}