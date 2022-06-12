/**
 * 
 */
package com.UITests.MerchantAdmin.WebSalePrograms.page;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import com.UITests.page.Page;

public class ViewWebSaleProgramWebeGiftPage extends Page {

	@FindBy(xpath = "//table/tbody/tr[td[1][normalize-space()=\"Credit Card Processor\"]]/td[2]")
	private WebElement creditCardProcessorField;

	private static final String[] TITLE_WORDS = { "View", "Web", "Sale", "Program", "\"Web", "eGift\"" };
	
	public ViewWebSaleProgramWebeGiftPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public String getCreditCardProcessorFieldText() {
		return waitFor(creditCardProcessorField).getText();
	}

}