/**
 * 
 */
package com.UITests.ReportCenter.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class GuestAnalysisReportCustomizeOutputPage extends Page {

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Go Back']")
	private WebElement goBackButton;

	@FindBy(xpath = "//table/tbody/tr[2]/td[1]/div[2]/div[6]/select")
	private WebElement reportTypeDropdown;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Run Demographic Report']")
	private WebElement runDemographicReportButton;

	

	private static final String[] TITLE_WORDS = { "Guest", "Analysis", "Report", "Customize", "Output" };

	public GuestAnalysisReportCustomizeOutputPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickGoBackButton() {
		click(goBackButton);
	}

	public void selectReportType(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(reportTypeDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(reportTypeDropdown);
		dropdown.selectByVisibleText(text);
	}

	public void clickRunDemographicReportButton() {
		click(runDemographicReportButton);
	}

}