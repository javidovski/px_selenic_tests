/**
 * 
 */
package com.UITests.MerchantHome.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class MerchantHomePage extends Page {

	@FindBy(xpath = "//*[text()='Logout']")
	private WebElement logout;
	
	@FindBy(xpath = "//a[contains(.,'Customer Service')]/following-sibling::*[contains(.,'Account Lookup')]/descendant::a[contains(text(),'Activate Card')][1]")
	private WebElement activateCardLink;

	@FindBy(linkText = "ADMIN")
	private WebElement aDMINLink;
	
	@FindBy(linkText = "SWITCH")
	private WebElement sWITCHLink;

	@FindBy(linkText = "Report Center")
	private WebElement reportCenterLink;
	
	@FindBy(linkText = "Marketing Tools")
	private WebElement marketingToolsLink;
	
	@FindBy(linkText = "Customer Service")
	private WebElement customerServiceLink;
	
	
	@FindBy(linkText = "Program Administration")
	private WebElement programAdministrationLink;
	
	
	@FindBy(xpath = "//a[contains(.,'Customer Service')]/following-sibling::*[contains(.,'Account Lookup')]/descendant::a[contains(text(),'Account Lookup')][1]")
	private WebElement accountLookup1;
	
	@FindBy(xpath = "//a[contains(.,'Marketing Tools')]/following-sibling::*[contains(.,'Email Tool')]/descendant::a[contains(text(),'Email Tool')]")
	private WebElement emailTool;
	
	@FindBy(xpath = "//a[contains(.,'Marketing Tools')]/following-sibling::*[contains(.,'Campaign Center')]/descendant::a[contains(text(),'Campaign Center')]")
	private WebElement campaignCenter;
	
	@FindBy(xpath = "//a[contains(.,'Marketing Tools')]/following-sibling::*[contains(.,'Survey Center')]/descendant::a[contains(text(),'Survey Center')]")
	private WebElement surveyCenter;
	
	@FindBy(xpath = "//a[contains(.,'Marketing Tools')]/following-sibling::*[contains(.,'Campaign Message Templates')]/descendant::a[contains(text(),'Campaign Message Templates')]")
	private WebElement campaignMessageTemplates;
	
	@FindBy(xpath = "//a[contains(.,'Program Administration')]/following-sibling::*[contains(.,'Rules')]/descendant::a[contains(text(),'Rules')]")
	private WebElement rules;
	
	@FindBy(xpath = "//a[contains(.,'Program Administration')]/following-sibling::*[contains(.,'Category Manager')]/descendant::a[contains(text(),'Category Manager')]")
	private WebElement categoryManager;
	
	@FindBy(linkText = "PAYTRONIX")
	private WebElement pAYTRONIXLink;
	
	@FindBy(xpath = "//div[contains(.,'Paytronix uses cookies to deliver the')and @style='display: block;']/descendant::button[contains(text(),'Continue')]")
	private WebElement cookieBanner;


	private static final String[] TITLE_WORDS = { "Merchant", "Home" };

	public MerchantHomePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public void clickLogout() {
		click(logout);
	}

	public void hoverCustomerServiceLink() {
		Actions builder = new Actions(driver);
		builder.moveToElement(customerServiceLink).perform();
	}
	
	public void hoverMarketingToolsLink() throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.moveToElement(marketingToolsLink).perform();
		sleep(500);
	}
	
	public void hoverProgramAdministrationLink() {
		Actions builder = new Actions(driver);
		builder.moveToElement(programAdministrationLink).perform();
	}
	
	public void clickAccountLookup1() {
		//click first Account Lookup link under Customer Service
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(accountLookup1));
		builder.moveToElement(accountLookup1).click().perform();
	}
	
	public void clickEmailTool() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(emailTool));
		builder.moveToElement(emailTool).click().perform();
	}
	
	public void clickSurveyCenter() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(surveyCenter));
		builder.moveToElement(surveyCenter).click().perform();
	}
	
	public void clickRules() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(rules));
		builder.moveToElement(rules).click().perform();
	}
	
	public void clickCategoryManager() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(categoryManager));
		builder.moveToElement(categoryManager).click().perform();
	}
	
	public void clickCampaignMessageTemplates() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(campaignMessageTemplates));
		builder.moveToElement(campaignMessageTemplates).click().perform();
	}
	
	public void clickProgramAdministrationLink() {
		click(programAdministrationLink);
	}
	
	public void clickADMINLink() {
		click(aDMINLink);
	}

	public void clickSWITCHLink() {
		click(sWITCHLink);
	}
	
	public void clickReportCenterLink() {
		click(reportCenterLink);
	}

	public void clickMarketingToolsLink() {
		click(marketingToolsLink);
	}
	
	public void clickCookieBanner() {
		click(cookieBanner);
	}
	
	public void clickCustomerServiceLink() {
		click(customerServiceLink);
	}

	public void clickPAYTRONIXLink() {
		click(pAYTRONIXLink);
	}

	public void clickActivateCardLink() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(activateCardLink));
		builder.moveToElement(activateCardLink).click().perform();
	}
	
	public void clickCampaignCenterLink() {
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(elementToBeClickable(campaignCenter));
		builder.moveToElement(campaignCenter).click().perform();
	}
}