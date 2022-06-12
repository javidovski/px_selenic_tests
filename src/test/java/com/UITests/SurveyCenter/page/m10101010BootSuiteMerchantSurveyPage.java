package com.UITests.SurveyCenter.page;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;

public class m10101010BootSuiteMerchantSurveyPage extends Page {
	
	@FindBy(id="1_open")
	private WebElement openResponseField;
	
	@FindBy(xpath="//div[@id='survey_content']/div[2]/div/div[2]/div[2]/div/span/input")
	private WebElement singleSelect1_PrettyGood;
	
	@FindBy(xpath="//div[@id='survey_content']/div[3]/div/div[2]/div[1]/div/span/input")
	private WebElement singleSelect2_NeedsImprovement;
	
	@FindBy(xpath="//div[@id='survey_content']/div[4]/div/div[2]/span[4]/span/input")
	private WebElement singleSelect3_NA;
	
	@FindBy(id="submit-survey")
	private WebElement submitSurvey;
	
	@FindBy(xpath = "//div[contains(.,'Paytronix uses cookies to deliver the')and @style='display: block;']/descendant::button[contains(text(),'Continue')]")
	private WebElement cookieBanner;
	
	private static final String[] TITLE_WORDS = { "PX", "Generic", "Test", "Merchant", "Survey" };

	public m10101010BootSuiteMerchantSurveyPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public static m10101010BootSuiteMerchantSurveyPage using(WebDriver driver) {
		return new m10101010BootSuiteMerchantSurveyPage(driver);
	}
	
	public m10101010BootSuiteMerchantSurveyPage setOpenResponseField(String text) {
		waitFor(openResponseField).clear();
		openResponseField.sendKeys(text);
		return this;
	}
	
	public m10101010BootSuiteMerchantSurveyPage clickSingleSelect1_PrettyGood() {
		click(singleSelect1_PrettyGood);
		return this;
	}
	
	public m10101010BootSuiteMerchantSurveyPage clickSingleSelect2_NeedsImprovement() {
		click(singleSelect2_NeedsImprovement);
		return this;
	}
	
	public m10101010BootSuiteMerchantSurveyPage clickSingleSelect3_NA() {
		click(singleSelect3_NA);
		return this;
	}
	
	public m10101010BootSuiteMerchantSurveyPage clickSubmitSurvey() {
		click(submitSurvey);
		return this;
	}
	
	public m10101010BootSuiteMerchantSurveyPage clickCookieBanner() {
		int oldTimeout = Page.DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT;
		Page.DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT=2;
		try {
			click(cookieBanner);
		}
		catch (NoSuchElementException | TimeoutException e) {
			//if there's no cookie banner, then skip this
		}
		finally {
			Page.DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT=oldTimeout;
		}
		
		return this;
	}

}
