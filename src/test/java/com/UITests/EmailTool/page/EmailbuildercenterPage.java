/**
 * 
 */
package com.UITests.EmailTool.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class EmailbuildercenterPage extends Page{

	@FindBy(xpath = "/descendant::span[normalize-space(.)='approve email']")
	private WebElement approveEmail;


	private static final String[] TITLE_WORDS = { "Email", "Builder" };

	public EmailbuildercenterPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickApproveEmail() {
		click(approveEmail);
	}

}