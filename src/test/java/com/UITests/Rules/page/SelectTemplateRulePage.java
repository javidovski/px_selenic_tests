/**
 * 
 */
package com.UITests.Rules.page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.UITests.page.Page;

public class SelectTemplateRulePage extends Page {


	@FindBy(css = "input[value='Attach Selected Rules']")
	private WebElement attachSelectedRulesButton;

	

	private static final String[] TITLE_WORDS = { "Select", "Template", "Rule" };

	public SelectTemplateRulePage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}

	public void clickAttachCheckbox(String ruleName) {
		String xpath = "//td[text()='" + ruleName + 
				"']/preceding-sibling::td/descendant::label[text()='Attach']/preceding-sibling::input[@type='checkbox']";
		WebElement el = driver.findElement(By.xpath(xpath));
		waitFor(el).click();
	}

	public void clickAttachSelectedRulesButton() {
		click(attachSelectedRulesButton);
	}

}