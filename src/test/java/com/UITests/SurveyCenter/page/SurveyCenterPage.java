/**
 * 
 */
package com.UITests.SurveyCenter.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class SurveyCenterPage extends Page {

	@FindBy(xpath = "/descendant::button[normalize-space(.)='New Survey']")
	private WebElement newSurveyButton;
	

	private static final String[] TITLE_WORDS = { "Survey", "Center" };

	public SurveyCenterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickNewSurveyButton() {
		click(newSurveyButton);
	}
	

}