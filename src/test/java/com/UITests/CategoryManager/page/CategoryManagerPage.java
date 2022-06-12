/**
 * 
 */
package com.UITests.CategoryManager.page;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.UITests.page.Page;


public class CategoryManagerPage extends Page{

	//@FindBy(xpath = "//table/tbody/tr[4]/td[1]/span/span[1]/input")
	@FindBy(xpath = "//td[text()='Free Candy Bar']/preceding-sibling::td[contains(@class,'selection-cell')]/descendant::input[@type='checkbox']")
	private WebElement webElement2;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Create']/descendant::span[normalize-space(.)='Create']")
	private WebElement createButton;

	@FindBy(id = "catEdition_name")
	private WebElement catEditionNameField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Apply']")
	private WebElement applyButton;

	@FindBy(xpath = "/descendant::*[@id='LNS_ProgramAdministration']/descendant::a[normalize-space(.)='Wallet Manager']")
	private WebElement lNSProgramAdministrationLink;
	
	@FindBy(className = "label-value")
	private WebElement labelValueField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Delete']/descendant::span[normalize-space(.)='Delete']")
	private WebElement deleteButton;


	private static final String[] TITLE_WORDS = { "Category", "Manager" };

	
	public CategoryManagerPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}


	public CategoryManagerPage clickCreateButton() {
		click(createButton);
		return this;
	}

	public CategoryManagerPage setCatEditionNameField(String text) {
		waitFor(catEditionNameField).clear();
		catEditionNameField.sendKeys(text);
		return this;
	}

	public CategoryManagerPage clickApplyButton() throws InterruptedException {
		click(applyButton);
		sleep(1000);
		return this;
	}

	public CategoryManagerPage clickLNSProgramAdministrationLink() {
		click(lNSProgramAdministrationLink);
		return this;
	}

	public String getLabelValueFieldText() {
		return waitFor(labelValueField).getText();
	}

	public CategoryManagerPage clickDeleteButton() {
		click(deleteButton);
		return this;
	}

	public CategoryManagerPage clickWebElement2() {
		click(webElement2);
		return this;
	}
	
	public CategoryManagerPage clickViewCategory(String categoryName) {
		WebElement openButton = driver.findElement(By.xpath("//td[text()='" + categoryName + "']" + 
				"/preceding-sibling::td[contains(@class,'action-col')]/descendant::button[@tooltip='Open']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",openButton);
		return this;
	}
	
	public CategoryManagerPage clickSelectCategory(String categoryName) {
		WebElement checkbox = driver.findElement(By.xpath("//td[text()='" + categoryName + "']" +  
				"/preceding-sibling::td[contains(@class,'selection-cell')]/descendant::input[@type='checkbox']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();",checkbox);
		return this;
	}

}