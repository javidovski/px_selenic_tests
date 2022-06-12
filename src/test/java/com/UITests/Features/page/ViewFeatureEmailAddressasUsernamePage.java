/**
 * 
 */
package com.UITests.Features.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;

public class ViewFeatureEmailAddressasUsernamePage extends Page {

	@FindBy(linkText = "Features")
	private WebElement featuresLink;


	private static final String[] TITLE_WORDS = { "View", "Feature", "\"Email", "Address", "as", "Username\"" };

		
	public ViewFeatureEmailAddressasUsernamePage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}

	
	public void clickFeaturesLink() {
		click(featuresLink);
	}

}