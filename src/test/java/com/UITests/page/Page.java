package com.UITests.page;

import static org.junit.jupiter.api.Assertions.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.time.Duration;
import java.util.Arrays;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class Page {
	
	protected WebDriver driver;
	protected static int DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT = 15;
	
	
	protected Page(WebDriver driver, String[] TITLE_WORDS) {
		this.driver = driver;
		assert this.driver != null;
		WebDriverWait wait = new WebDriverWait(this.driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		Arrays.stream(TITLE_WORDS).forEach(word -> {
			wait.until(attributeContains(By.tagName("title"), "innerHTML", word));
		});
		PageFactory.initElements(this.driver, this);
	}
	
	protected WebElement waitFor(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		return wait.until(elementToBeClickable(element));
	}

	protected WebElement scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		return element;
	}

	protected WebElement click(WebElement element) {
		WebElement webElement = scrollTo(waitFor(element));
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		return wait.ignoring(ElementClickInterceptedException.class).until(webDriver -> {
			webElement.click();
			return webElement;
		});
	}
	
	protected void sleep(int milliseconds) throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
		Thread.sleep(milliseconds);
	}
	
	protected boolean elementIsVisible(WebElement el) {
		// ExpectedCondition.visibilityOfElementLocated is an expectation for checking
		// that an element is present on the DOM of a page
		// and visible (can be seen by a user). Visibility means that the element is not
		// only displayed but also has a height and width that is greater than 0.

		boolean isVisible = false;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(2000))
				.pollingEvery(Duration.ofMillis(400));

		try {
			wait.until(visibilityOf(el));
			isVisible = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			fail("unknown exception occurred");
		}

		return isVisible;
	}
	
	protected boolean elementIsVisible(WebElement el, int timeout, 
			int pollingInterval) {

		boolean isVisible = false;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));

		try {
			wait.until(visibilityOf(el));
			isVisible = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			fail("unknown exception occurred");
		}

		return isVisible;
	}
	
	protected boolean elementIsntVisible(WebElement el) {
	
		boolean isntVisible = false;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(2000))
				.pollingEvery(Duration.ofMillis(400));

		try {
			wait.until(invisibilityOf(el));
			isntVisible = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			fail("unknown exception occurred");
		}

		return isntVisible;
	}
	
	protected boolean elementIsntVisible(WebElement el, int timeout, 
			int pollingInterval) {
		
		boolean isntVisible = false;

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));

		try {
			wait.until(invisibilityOf(el));
			isntVisible = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			fail("unknown exception occurred");
		}

		return isntVisible;
	}

	protected void waitUntilElementVisible(WebElement el) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(400))
				.ignoring(NoSuchElementException.class);

		wait.until(visibilityOf(el));
	}
	
	protected void waitUntilElementVisible(WebElement el, int timeout, 
			int pollingInterval) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval))
				.ignoring(NoSuchElementException.class);

		wait.until(visibilityOf(el));
	}
	
	protected void waitUntilTextContains(WebElement el, String text) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(400))
				.ignoring(NoSuchElementException.class);

		wait.until(textToBePresentInElement(el, text));
	}
	
	protected void waitUntilTextContains(WebElement el, String text, int timeout, 
			int pollingInterval) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval))
				.ignoring(NoSuchElementException.class);

		wait.until(textToBePresentInElement(el, text));
	}
	
	protected void waitForTextToBe(Page p, String fieldName, String locatorType, 
			String text) 
					throws NoSuchFieldException, SecurityException {
		//An expectation for checking WebElement with given locator has specific text

		Field field = p.getClass().getDeclaredField(fieldName);
		Annotation annotation = field.getAnnotation(FindBy.class);
		FindBy findByAnnotation = (FindBy)annotation;
		By locator = getLocator(locatorType,findByAnnotation);

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(5000))
				.pollingEvery(Duration.ofMillis(400))
				.ignoring(NoSuchElementException.class);
		wait.until(textToBe(locator, text));
	}

	
	
	
	public By getLocator(String locatorStr, FindBy findByAnnotation) {
		locatorStr = locatorStr.toLowerCase();
		By locator = null;
		String locatorID = null;
		
		switch (locatorStr) {
		case "xpath" :
			locatorID = findByAnnotation.xpath();
			locator = By.xpath(locatorID);
			break;
			
		case "id" :
			locatorID = findByAnnotation.id();
			locator = By.id(locatorID);
			break;
		
		case "classname" : 
			locatorID = findByAnnotation.className();
			locator = By.className(locatorID);
			break;
			
		case "cssselector" :
			locatorID = findByAnnotation.css();
			locator = By.cssSelector(locatorID);
			break;
			
		case "linktext" :
			locatorID = findByAnnotation.linkText();
			locator = By.linkText(locatorID);
			break;
			
		case "name" :
			locatorID = findByAnnotation.name();
			locator = By.name(locatorID);
			break;
			
		case "tagname​" :
			locatorID = findByAnnotation.tagName();
			locator = By.tagName(locatorID);
			break;
			
		case "partiallinktext" :
			locatorID = findByAnnotation.partialLinkText();
			locator = By.partialLinkText(locatorID);
			break;
			
		default:
            fail("unknown locator type '" + locatorStr + "'");
            break;
		}
		
		return locator;
	}
	
	


}
