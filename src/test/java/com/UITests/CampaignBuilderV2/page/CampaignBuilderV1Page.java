/**
 * 
 */
package com.UITests.CampaignBuilderV2.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class CampaignBuilderV1Page extends Page{

	@FindBy(xpath = "/descendant::span[normalize-space(.)='SAVE AND CONTINUE']")
	private WebElement sAVEANDCONTINUE2;

	@FindBy(xpath = "/descendant::div[@class='card card-default-type'][4]")
	private WebElement webElement5;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Save and continue']")
	private WebElement saveAndContinue4;

	@FindBy(xpath = "//table/tbody/tr[8]/td[1]/input")
	private WebElement webElement23;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Save and Continue']")
	private WebElement saveAndContinue22;

	@FindBy(xpath="//input[@id='textInput']")
	private WebElement adjustmentQuantityField;
	
	

	@FindBy(css = ".selector-refresh-0 .svg-inline--fa")
	private WebElement webElement4;

	@FindBy(css = "div:nth-of-type(2) > div > div:first-of-type > div > span > i > svg > path")
	private WebElement webElement22;

	@FindBy(name = "isGoing")
	private WebElement isGoingField;

	@FindBy(xpath="//input[@placeholder='Search or Select Wallet']")
	private WebElement offerNameInputField;
	
	
	@FindBy(xpath = "/descendant::span[normalize-space(.)='Create New']")
	private WebElement createNew;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='+ Add']")
	private WebElement add;

	@FindBy(xpath = "/descendant::li[normalize-space(.)='Free Clif Bar']")
	private WebElement freeClifBar;

	@FindBy(xpath = "/descendant::li[normalize-space(.)='General 1']")
	private WebElement general;

	@FindBy(xpath = "//span[.='SAVE AND CONTINUE']/ancestor::button[not(@disabled)]")
	private WebElement sAVEANDCONTINUE;

	@FindBy(xpath = "/descendant::p[normalize-space(.)='ADD EMAIL']")
	private WebElement aDDEMAIL;

	@FindBy(xpath = "/descendant::div[@class='card card-default-type'][3]")
	private WebElement webElement;

	@FindBy(xpath = "//button[contains(.,'Save and continue')]")
	private WebElement saveAndContinue;

	@FindBy(xpath = "//table/tbody/tr[6]/td[1]/input")
	private WebElement webElement2;

	@FindBy(css = "#cg-target-size > .count-value")
	private WebElement field;

	@FindBy(xpath = "//span[.='Save and Continue']/ancestor::button")
	private WebElement saveAndContinue2;
	
	@FindBy(xpath="//button[contains(.,'Save and Continue')]")
	private WebElement saveAndContinue3;

	@FindBy(xpath = "/descendant::div[normalize-space(.)='2'][2]")
	private WebElement webElement3;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='save as draft']")
	private WebElement saveAsDraft;

	private static final String[] TITLE_WORDS = { "Campaign", "Builder" };

	public CampaignBuilderV1Page(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void setIsGoingField(String text) {
		waitFor(isGoingField).clear();
		isGoingField.sendKeys(text);
	}
	
	public void setAdjustmentQuantity(String text ) {
		waitFor(adjustmentQuantityField).clear();
		adjustmentQuantityField.sendKeys(text);
	}

	public void clickCreateNew() {
		click(createNew);
	}

	public void clickAdd() {
		click(add);
	}

	public void clickFreeClifBar() {
		click(freeClifBar);
	}

	public void clickGeneral() {
		click(general);
	}

	public void clickSAVEANDCONTINUE() {
		click(sAVEANDCONTINUE);
	}

	public void clickADDEMAIL() {
		click(aDDEMAIL);
	}

	public void clickWebElement() {
		click(webElement);
	}

	public void clickSaveAndContinue() {
		click(saveAndContinue);
	}

	public void clickWebElement2() {
		click(webElement2);
	}

	public String getFieldText() {
		return waitFor(field).getText();
	}

	public void clickSaveAndContinue2() {
		click(saveAndContinue2);
	}
	
	public void clickSaveAndContinue3() {
		click(saveAndContinue3);
	}

	public void clickWebElement3() {
		click(webElement3);
	}

	public void clickSaveAsDraft() {
		click(saveAsDraft);
	}

	public void clickWebElement4() {
		click(webElement4);
	}

	public void clickWebElement22() {
		click(webElement22);
	}
	
	public void clickOfferInputField() {
	    click(offerNameInputField);
	
	}

	public void clickSAVEANDCONTINUE2() {
		click(sAVEANDCONTINUE2);
	}

	public void clickWebElement5() {
		click(webElement5);
	}

	public void clickSaveAndContinue4() {
		//WebElement button = scrollTo(waitFor(saveAndContinue4));
		click(saveAndContinue4);
		
	}

	public void clickWebElement23() {
		click(webElement23);
	}

	public void clickSaveAndContinue22() {
		click(saveAndContinue22);
	}

	

}