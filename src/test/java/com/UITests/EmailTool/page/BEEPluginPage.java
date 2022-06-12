/**
 * 
 */
package com.UITests.EmailTool.page;


import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.page.Page;

public class BEEPluginPage extends Page{

	@FindBy(xpath = "/descendant::span[normalize-space(.)='Parameters']")
	private WebElement parameters;
	
	@FindBy(xpath="//*[.='Text']/preceding-sibling::*[contains(@class,'image-drag')]")
	private WebElement textImageDrag;
	
	@FindBy(xpath="//div[contains(@class,'module-empty') or contains(@class,'module-box') or contains(@data-name,'Structure')]")
	private WebElement textContentBox;
	
	@FindBy(xpath="//div[contains(@class,'txtTinyMce')]")
	private WebElement contentBoxClickElement;
	
	@FindBy(xpath="//span[contains(text(),'Content')]")
	private WebElement contentTab;
	
	@FindBy(xpath="//button[.='SAVE']")
	private WebElement saveEmail;
	
	@FindBy(xpath = "/descendant::span[normalize-space(.)='Actions']")
	private WebElement actions;

	@FindBy(xpath = "/descendant::div[normalize-space(.)='Send test']")
	private WebElement sendTest;
	
	@FindBy(xpath="//*[contains(.,'<template:link type=\"unsubscribe_url\">Click here to unsubscribe.</template:link>')]")
	private WebElement unsubscribe_url;

	private static final String[] TITLE_WORDS = { "BEE", "Plugin" };

	public BEEPluginPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	
	public void setTextBlockParagraph(WebDriver driver, String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		WebElement textBlockParagraph = driver.findElement(By.xpath("//p[contains(text(),'block ready for')]"));
		wait.until(visibilityOf(textBlockParagraph));
		JavascriptExecutor executor = (JavascriptExecutor)driver;		
		executor.executeScript("var ele=arguments[0]; ele.innerHTML = '" + text + "';", textBlockParagraph);
	}

	public void clickContentTab() {
		click(contentTab);
	}
	public void clickContentBoxClickElement() {
		click(contentBoxClickElement);
	}
	
	public void dragdropTextImage(WebDriver driver) {
		Actions action = new Actions(driver);
		WebElement waitElement = waitFor(textImageDrag);
		WebElement waitTarget = waitFor(textContentBox);
		action.dragAndDrop(waitElement,waitTarget).build().perform();
		//another way to do it:
		//action.clickAndHold(textImageDrag).moveToElement(textContentBox).release(textContentBox).build().perform();
	}

	
	public void clickParameters() {
		click(parameters);
	}

	public void clickSaveEmail() {
		click(saveEmail);
	}
	
	public void clickActions() {
		click(actions);
	}
	
	public void clickSendTest() {
		click(sendTest);	
	}
	
	public void waitUntilUnsubscribeURLVisible() {
		waitUntilElementVisible(unsubscribe_url);
	}

}