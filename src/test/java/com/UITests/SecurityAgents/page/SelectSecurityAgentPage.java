/**
 * 
 */
package com.UITests.SecurityAgents.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.UITests.page.Page;

public class SelectSecurityAgentPage extends Page{

	@FindBy(linkText = "New Security Agent")
	private WebElement newSecurityAgentLink;


	private static final String[] TITLE_WORDS = { "Select", "Security", "Agent" };

	public SelectSecurityAgentPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public void clickNewSecurityAgentLink() {
		click(newSecurityAgentLink);
	}


}