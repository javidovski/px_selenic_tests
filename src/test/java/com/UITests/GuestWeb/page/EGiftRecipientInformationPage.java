/**
 * 
 */
package com.UITests.GuestWeb.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class EGiftRecipientInformationPage extends Page{

	@FindBy(id = "recipient_from")
	private WebElement recipientFromField;

	@FindBy(id = "recipient_to")
	private WebElement recipientToField;

	@FindBy(id = "recipient_email")
	private WebElement recipientEmailField;

	@FindBy(id = "recipient_email_confirm")
	private WebElement recipientEmailConfirmField;

	@FindBy(id = "msg_content")
	private WebElement msgContentField;

	@FindBy(xpath = "//div[2]/div[1]/div[2]/div[2]")
	private WebElement webElement;

	@FindBy(xpath = "//div[2]/div[1]/div[2]/div[3]")
	private WebElement valueField;

	@FindBy(id = "result_quantity")
	private WebElement resultQuantityField;

	@FindBy(id = "result_msg")
	private WebElement resultMsgField;

	@FindBy(xpath = "(//*[@value='Continue'])[2]")
	private WebElement continueButton;

	

	private static final String[] TITLE_WORDS = { "Recipient", "Information" };

	public EGiftRecipientInformationPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	

	public EGiftRecipientInformationPage setRecipientFromField(String text) {
		waitFor(recipientFromField).clear();
		recipientFromField.sendKeys(text);
		return this;
	}


	public EGiftRecipientInformationPage setRecipientToField(String text) {
		waitFor(recipientToField).clear();
		recipientToField.sendKeys(text);
		return this;
	}

	public EGiftRecipientInformationPage setRecipientEmailField(String text) {
		waitFor(recipientEmailField).clear();
		recipientEmailField.sendKeys(text);
		return this;
	}

	public EGiftRecipientInformationPage setRecipientEmailConfirmField(String text) {
		waitFor(recipientEmailConfirmField).clear();
		recipientEmailConfirmField.sendKeys(text);
		return this;
	}

	public EGiftRecipientInformationPage setMsgContentField(String text) {
		waitFor(msgContentField).clear();
		msgContentField.sendKeys(text);
		return this;
	}

	public EGiftRecipientInformationPage clickWebElement() {
		click(webElement);
		return this;
	}

	public String getValueFieldText() {
		return waitFor(valueField).getText();
	}

	public String getResultQuantityFieldText() {
		return waitFor(resultQuantityField).getText();
	}

	public String getResultMsgFieldText() {
		return waitFor(resultMsgField).getText();
	}

	public EGiftRecipientInformationPage clickContinue() {
		click(continueButton);
		return this;
	}

}