/**
 * 
 */
package com.UITests.Features.page;


import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class SelectFeaturePage extends Page {

	@FindBy(xpath = "//table/tbody/tr[1]/td[1]/form/select")
	private WebElement dropdown1;

	@FindBy(css = "input[value='New']")
	private WebElement newButton;
	
	@FindBy(xpath = "//td[text()='Email Address as Username']/preceding-sibling::td/a[img[@title='Delete']]")
	private WebElement deleteEmailAsUsername;
	
	@FindBy(xpath = "//input[@type='button' and @value='Yes']")
	private WebElement confirmFeatureDelete;
	


	private static final String[] TITLE_WORDS = { "Select", "Feature" };

	
	
	public SelectFeaturePage(WebDriver driver) {
		super(driver,TITLE_WORDS);
	}
	

	public void selectDropdown1(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(dropdown1).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(dropdown1);
		dropdown.selectByVisibleText(text);
	}

	public void clickNewButton() {
		click(newButton);
	}
	
	public void clickDeleteEmailAsUsername() {
		click(deleteEmailAsUsername);
	}
	
	public void clickConfirmFeatureDelete() {
		click(confirmFeatureDelete);
	}

	

}