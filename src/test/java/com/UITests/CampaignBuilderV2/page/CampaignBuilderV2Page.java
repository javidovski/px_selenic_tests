/**
 * 
 */
package com.UITests.CampaignBuilderV2.page;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;

public class CampaignBuilderV2Page extends Page{

	@FindBy(className = "campaign-name")
	private WebElement campaignNameField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Add wallet']/descendant::span[normalize-space(.)='Add wallet']")
	private WebElement addWalletButton;

	@FindBy(xpath = "(//*[contains(@class,'adj-wallet-select')]/descendant::input[contains(@id,'react-select-') and contains(@id,'input')])[1]")
	private WebElement selectWalletInputField1;
	
	@FindBy(xpath = "(//*[contains(@class,'adj-wallet-select')]/descendant::*[contains(@class,'indicatorContainer')])[1]")
	private WebElement walletInputFieldDropdownArrow1;

	
	@FindBy(xpath = "//div[contains(@id,'react-select-') and contains(@id,'option-') and text()='Free Clif Bar']")
	private WebElement freeClifBar;

	@FindBy(xpath = "(//input[contains(@class,'adj-quantity')])[1]")
	private WebElement walletQuantityField1;
	
	
	@FindBy(xpath = "//*[contains(@class,'offer-store-select')]/descendant::input[contains(@id,'react-select-') and contains(@id,'-input')]")
	private WebElement selectStoreInputField;
	
	@FindBy(xpath = "//*[contains(@class,'offer-store-select')]/descendant::*[contains(@class,'indicatorContainer')]")
	private WebElement storeInputFieldDropdownArrow;
	
	@FindBy(xpath = "//div[contains(@id,'react-select-') and contains(@id,'option-') and text()='Corporate (corp)']")
	private WebElement corporateStore;
	
	
	@FindBy(xpath = "//*[contains(@class,'offer-expiration')]/descendant::*[contains(.,'Relative')]/preceding-sibling::input[@type='radio']")
	private WebElement relativeExpRadioButton;
	
	@FindBy(xpath = "//*[contains(@class,'relative-expiration')]/descendant::input[@type='text' and @class='form-control']")
	private WebElement relativeExpirationInputField;
	
	
	@FindBy(xpath = "//*[contains(@class,'offer-rb')]/descendant::*[contains(.,'This Campaign is Rule-Based')]/preceding-sibling::input[@type='checkbox']")
	private WebElement isRuleBasedCheckbox;
	
	@FindBy(xpath = "/descendant::span[normalize-space(.)='Message']")
	private WebElement messageTab;

	@FindBy(xpath = "//*[text()='Test Click To Load Email']/following-sibling::*/descendant::button[@label='Select']")
	private WebElement testClickToLoadSelectButton;

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Segment']")
	private WebElement segmentTab;
	
	@FindBy(xpath = "//*[text()='Account Filter for ATs']/ancestor::*/preceding-sibling::*[contains(@class,'selection-cell')]/descendant::*[contains(@class,'single-') and contains(@class,'-selected')]")
	private WebElement accountFiltersForATs;
	
	@FindBy(xpath = "/descendant::span[normalize-space(.)='Schedule']")
	private WebElement scheduleTab;
	
	@FindBy(xpath = "/descendant::button[normalize-space(.)='Save & Review']")
	private WebElement saveReviewButton;


	private static final String[] TITLE_WORDS = { "Campaign", "Builder" };

	public CampaignBuilderV2Page(WebDriver driver) throws InterruptedException {
		super(driver,TITLE_WORDS);
		sleep(500);
	}

	

	public CampaignBuilderV2Page setCampaignNameField(String text) {
		waitFor(campaignNameField).clear();
		campaignNameField.sendKeys(text);
		return this;
	}

	public CampaignBuilderV2Page clickAddWalletButton() throws InterruptedException {
		click(addWalletButton);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickSelectWalletInputField() {
		click(selectWalletInputField1);
		return this;
	}
	
	public CampaignBuilderV2Page clickWalletInputFieldDropdownArrow1() throws InterruptedException{
		click(walletInputFieldDropdownArrow1);
		return this;
	}
	
	public CampaignBuilderV2Page setWalletInputField1(String text) throws InterruptedException {
		//waitFor(selectWalletInputField).clear();		
		selectWalletInputField1.sendKeys(text);
		sleep(500);	
		return this;
	}

	public CampaignBuilderV2Page clickFreeClifBar() throws InterruptedException{
		click(freeClifBar);
		return this;
	}

	public CampaignBuilderV2Page setWalletQuantityField1(String text) throws InterruptedException{
		waitFor(walletQuantityField1).clear();
		walletQuantityField1.sendKeys(text);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickStoreInputFieldDropdownArrow() throws InterruptedException{
		click(storeInputFieldDropdownArrow);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickCorporateStore() throws InterruptedException{
		click(corporateStore);
		sleep(500);
		return this;
	}
	
	public CampaignBuilderV2Page clickRelativeExpRadioButton() throws InterruptedException{		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",relativeExpRadioButton);
		sleep(500);
		//relativeExpRadioButton.click();
		return this;
	}
	
	public CampaignBuilderV2Page setRelativeExpirationInputField(String number) throws InterruptedException{
		relativeExpirationInputField.sendKeys(number);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page setIsRuleBasedCheckbox(boolean checked) throws InterruptedException{
		if ((!isRuleBasedCheckbox.isSelected() && checked) || (isRuleBasedCheckbox.isSelected() && !checked)) {
			isRuleBasedCheckbox.click();
		}
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickMessageTab() throws InterruptedException{
		click(messageTab);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickTestClickToLoadSelectButton() throws InterruptedException{
		click(testClickToLoadSelectButton);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickSegmentTab() throws InterruptedException{
		click(segmentTab);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickAccountFiltersForATs() throws InterruptedException{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",accountFiltersForATs);
		sleep(500);
		//click(accountFiltersForATs);
		return this;
	}

	public CampaignBuilderV2Page clickScheduleTab() throws InterruptedException{
		click(scheduleTab);
		sleep(500);
		return this;
	}

	public CampaignBuilderV2Page clickSaveReviewButton() throws InterruptedException{
		click(saveReviewButton);
		sleep(500);
		return this;
	}

}