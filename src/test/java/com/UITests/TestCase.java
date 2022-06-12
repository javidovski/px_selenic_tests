package com.UITests;



import java.time.Duration;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.frameToBeAvailableAndSwitchToIt;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.UITests.AccountLookup.page.ActivateCardPage;
import com.UITests.MerchantHome.page.MerchantHomePage;
import com.UITests.UtilityClasses.DatabaseUtils;
import com.UITests.UtilityClasses.DateUtils;
import com.UITests.page.LogInPage;
import com.UITests.page.SwitchMerchantPage;


public class TestCase {
	
	
	public static final String APPSERVER = "1";	
	public static final String DOWNLOAD_DIR = "/Users/jeffmartin/Downloads/";
	public static final String FAILOVER_PORT = "10443";	
	public static final String MERCHANT_ID = "10101010";	
	public static final String MERCHANT_HOME_PATH = "/merchant/home.srv";
	public static final String GUEST_PATH = "/guest";
	public static final String registrationCode="123456";
	public static final String combinedCardTemplateID="30145";
	public static final String inactiveStatusEnumID="10101";
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final int    DEFAULT_TIMEOUT=10;
	
	public static String SERVER;
	public static String PAYTRONIX_BASE_URL;
	public static String EMAIL;
	public static String GUEST_BASE_URL;
	
	public    WebDriver driver;	
	protected String startTime, stopTime;
	protected LogInPage logInPage;
	protected MerchantHomePage merchantHomePage;
	protected SwitchMerchantPage switchMerchantPage;
	protected ActivateCardPage activateCardPage;
	
	public TestCase() {
		SERVER = "wal1at-app" + APPSERVER;
		PAYTRONIX_BASE_URL = "https://wal1at-app" + APPSERVER + ".paytronix.com";
		EMAIL = "incoming+app" + APPSERVER + "@atfleet.test";
		GUEST_BASE_URL = "https://wal1at-app" + APPSERVER + ".atfleet.test:10443";
	}
	
	@BeforeEach
	public void beforeTest() {
		startTime = DateUtils.getCurrentDatetime();
		ChromeOptions opts = new ChromeOptions();
		opts.addArguments("--start-maximized");
		opts.addArguments("--disable-geolocation");
		opts.addArguments("--incognito");
		opts.addArguments("--enable-strict-powerful-feature-restrictions");
		opts.addArguments("--ignore-certificate-errors");
		//opts.addArguments("--headless");
		//opts.addArguments("--window-size=1200x600");		
		
		//2 options below required since upgrading to Chrome 98 for some unknown reason
		//opts.addArguments("--remote-debugging-port=9222");
		opts.addArguments("--no-sandbox");
		opts.addArguments("--disable-dev-shm-usage");
		
		driver = new ChromeDriver(opts);
		driver.manage().window().maximize();
		driver.get(PAYTRONIX_BASE_URL + MERCHANT_HOME_PATH);
	}

	@AfterEach
	public void afterTest() {		
		if (driver != null) {
			driver.quit();
		}
		
		stopTime = DateUtils.getCurrentDatetime();
		System.out.println("Test took " + DateUtils.getSecondsBetween(startTime, stopTime) + " seconds");		
	}
	
	/**
	 * Takes in an inactive card number to activate.
	 * @param cardNum the cardNum, {@code String}, to activate 
	 */
	public void activateCard(String cardNum) {
		activateCardPage = new ActivateCardPage(driver);
		activateCardPage.selectStoreDropdown("afs1 - Access Filter Store 1");
		activateCardPage.setCardNumberField(cardNum);
		activateCardPage.clickSubmitButton();
		System.out.println("Card successfully activated.");
	}
	
	/**
	 * Login to the Merchant home with <b><i> px_admin </i></b> credentials
	 */
	public void login() {				
		logInPage = new LogInPage(driver);
		logInPage.setMerchantUsernameInputField("test_px_admin");
		logInPage.setMerchantPasswordInputField("test");
		logInPage.clickTakeMeThereButton();
		merchantHomePage = new MerchantHomePage(driver);
		merchantHomePage.clickCookieBanner();
	
	}
	
	/**
	 * Switches to PX Generic Test Merchant
	 */
	public void switchMerchant() {
		merchantHomePage.clickSWITCHLink();
		switchMerchantPage = new SwitchMerchantPage(driver);
		switchMerchantPage.setPxidField(MERCHANT_ID);
		switchMerchantPage.clickGoButton();
	}

	/**
	 * This method will reset the card. It will delete all user's info and inactivates the card.
	 * 
	 * @param cardNum the cardNum, {@code String}, to reset
	 * @param merchantID the merchantId, {@code String}, the {@code cardNum} belongs to
	 * @throws Throwable
	 */
	public void resetCard(String cardNum, String merchantID) throws Throwable {
		System.out.println("\n\nReseting our card ...");
		DatabaseUtils.resetCard(cardNum, merchantID);
		sleep(2000);
		System.out.println("Card successfully reset.");
	}
	

	public void navigateToURL(String url) {
		driver.get(url);
	}
	
	public void clickCookieBanner( ) {
		String locatorIdStr = "//div[contains(.,'Paytronix uses cookies to deliver the')and @style='display: block;']/descendant::button[contains(text(),'Continue')]";
		
		if(elementIsVisible(locatorIdStr,"xpath",2000,500)) {
			clickElement(locatorIdStr,"xpath");
		}
	}
	
	public String getRandomAlphanumeric(int length) {		
		UUID uuid = UUID.randomUUID();
		String uuidstr = uuid.toString();
		return uuidstr.substring(0,length);
	}
		
	public void sleep(int milliseconds) throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(milliseconds, TimeUnit.MILLISECONDS);
		Thread.sleep(milliseconds);
	}
	
	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}
	
	public void clickElement(String locatorIdStr, String locatorStr) {
		By locator = getLocator(locatorIdStr, locatorStr);
		//finds the first matching element, else throws NoSuchElementException
		WebDriverWait wait = new WebDriverWait(driver,DEFAULT_TIMEOUT);

		try {
			wait.until(elementToBeClickable(locator)).click();
		}
		catch (ElementClickInterceptedException e) {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();",driver.findElement(locator));
		}
	}
	
	public void setCheckbox(String locatorIdStr, String locatorStr, boolean checked) {
		By locator = getLocator(locatorIdStr, locatorStr);
		WebElement el = driver.findElement(locator);
		if ((!el.isSelected() && checked) || (el.isSelected() && !checked)) {
			el.click();
		}
	}

	public String getElementText(String locatorIdStr, String locatorStr) {
		By locator = getLocator(locatorIdStr, locatorStr);
		WebElement el = driver.findElement(locator);
		return el.getText();
	}
	
	public void verifyTextInElement(String locatorIdStr, String locatorStr, String text) {
		By locator = getLocator(locatorIdStr, locatorStr);
		WebElement el = driver.findElement(locator);
		assertEquals(text,el.getText());
	}
	
	public void verifyElementContainsText(String locatorIdStr, String locatorStr, String text) {
		By locator = getLocator(locatorIdStr, locatorStr);
		WebElement el = driver.findElement(locator);
		assertTrue(el.getText().contains(text));
	}

	public void verifyTextOnPage(String text) {
		String xpath = "//*[contains(., '" + text + "')]";
		By locator = getLocator(xpath,"xpath");
		try {
			//finds the first matching element, else throws NoSuchElementException
			WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
			WebElement el = wait.until(visibilityOfElementLocated(locator));
			assertTrue(el != null); //I don't think it could ever be null, but just in case
		} 
		catch (TimeoutException e) {
			fail("No element found");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Element couldn't be found due to unknown exception");
		}
	}
	
	
	
	public void verifyTextOnPage(String text, boolean shouldExist, int timeout, int pollingInterval) {
		String xpath = "//*[contains(., '" + text + "')]";
		By locator = getLocator(xpath,"xpath");
		boolean exists = false;
		

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));

		try {
			WebElement el = wait.until(visibilityOfElementLocated(locator));
			assertTrue(el != null); //I don't think it could ever be null, but just in case
			exists = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			e.printStackTrace();
			fail("unknown exception occurred");
		}
		
		assertEquals(shouldExist,exists);
	}
	
	public boolean textExistsOnPage(String text, int timeout, int pollingInterval) {
		String xpath = "//*[contains(., '" + text + "')]";
		By locator = getLocator(xpath,"xpath");
		boolean exists = false;
		

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));

		try {
			WebElement el = wait.until(visibilityOfElementLocated(locator));
			assertTrue(el != null); //I don't think it could ever be null, but just in case
			exists = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			e.printStackTrace();
			fail("unknown exception occurred");
		}
		
		return exists;
	}
	
	public boolean elementIsVisible(String locatorIdStr, String locatorStr, int timeout, int pollingInterval) {
		By locator = getLocator(locatorIdStr,locatorStr);
		boolean exists = false;
		

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));

		try {
			WebElement el = wait.until(visibilityOfElementLocated(locator));
			assertTrue(el != null); //I don't think it could ever be null, but just in case
			exists = true;
		}
		catch (NoSuchElementException | TimeoutException e) {}
		catch (Exception e)  {
			e.printStackTrace();
			fail("unknown exception occurred");
		}
		
		return exists;
	}
	
	public void verifyElementExists(String locatorIdStr, String locatorStr) {		
		By locator = getLocator(locatorIdStr, locatorStr);
		List<WebElement> el = driver.findElements(locator);
		assertFalse(el.isEmpty());		
	}
	
	public void verifyElementExistsNTimes(String locatorIdStr, String locatorStr, int numOccurrences) {
		By locator = getLocator(locatorIdStr, locatorStr);
		List<WebElement> el = driver.findElements(locator);
		assertEquals(numOccurrences, el.size());
	}
	
	public void verifyElementDoesntExist(String locatorIdStr, String locatorStr) {
		By locator = getLocator(locatorIdStr, locatorStr);
		List<WebElement> el = driver.findElements(locator);
		assertTrue(el.isEmpty());
	}
	
	public void verifyElementExists_AtLeastNTimes(String locatorIdStr, String locatorStr, int numOccurrences) {
		By locator = getLocator(locatorIdStr, locatorStr);
		List<WebElement> el = driver.findElements(locator);
		assertTrue(el.size() >= numOccurrences);
	}
	
	public void verifyElementExists_AtMostNTimes(String locatorIdStr, String locatorStr, int numOccurrences) {
		By locator = getLocator(locatorIdStr, locatorStr);
		List<WebElement> el = driver.findElements(locator);
		assertTrue(el.size() <= numOccurrences);
	}
	
	public By getLocator(String locatorIdStr, String locatorStr) {
		locatorStr = locatorStr.toLowerCase();
		By locator = null;
		
		switch (locatorStr) {
		case "xpath" :
			locator = By.xpath(locatorIdStr);
			break;
			
		case "id" :
			locator = By.id(locatorIdStr);
			break;
		
		case "classname" : 
			locator = By.className(locatorIdStr);
			break;
			
		case "cssselector" :
			locator = By.cssSelector(locatorIdStr);
			break;
			
		case "linktext" :
			locator = By.linkText(locatorIdStr);
			break;
			
		case "name" :
			locator = By.name(locatorIdStr);
			break;
			
		case "tagname​" :
			locator = By.tagName(locatorIdStr);
			break;
			
		case "partiallinktext" :
			locator = By.partialLinkText(locatorIdStr);
			break;
			
		default:
            fail("unknown locator type '" + locatorStr + "'");
            break;
		}
		
		return locator;
	}
	
	public void scrollUp(int pixels) {		
		int signedPixels = (pixels < 0) ? pixels : pixels*-1;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0," + signedPixels + ")");
	}
	
	public void scrollDown(int pixels) {
		int signedPixels = (pixels > 0) ? pixels : pixels*-1;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0," + signedPixels + ")");
	}
	
	public void waitUntilTextVisible(String text) {
		String xpath = "//*[contains(., '" + text + "')]";
		By locator = getLocator(xpath,"xpath");
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		wait.until(visibilityOfElementLocated(locator));
	}
	
	public void waitUntilTextVisible(String text, int timeout) {
		String xpath = "//*[contains(., '" + text + "')]";
		By locator = getLocator(xpath,"xpath");
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		wait.until(visibilityOfElementLocated(locator));
	}
	
	public WebElement waitUntilElementVisible(String locatorIdStr, String locatorStr) {
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		By locator = getLocator(locatorIdStr,locatorStr);
		return wait.until(visibilityOfElementLocated(locator));				
	}
	
	public WebElement waitUntilElementVisible(String locatorIdStr, String locatorStr, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		By locator = getLocator(locatorIdStr,locatorStr);
		return wait.until(visibilityOfElementLocated(locator));		
	}
	
	public WebElement waitUntilElementClickable(String locatorIdStr, String locatorStr, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver,timeout);
		By locator = getLocator(locatorIdStr,locatorStr);
		wait.until(visibilityOfElementLocated(locator));
		return wait.until(elementToBeClickable(driver.findElement(locator)));	
	}
	
	public WebElement waitForElementWithRefresh(String locatorIdStr,String locatorStr,int timeout,int pollingInterval) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofMillis(timeout))
				.pollingEvery(Duration.ofMillis(pollingInterval));
		By locator = getLocator(locatorIdStr,locatorStr);
		
		//until repeatedly calls the lambda function until it returns neither null nor false, or throws a TimeoutException
		return wait.until(webDriver -> {
		 List<WebElement> list = webDriver.findElements(locator); 
		 if (list.isEmpty()) {
			 webDriver.navigate().refresh();
			 return null;
		 }
		 else {
			 return list.get(0);
		 }
		});			 
	}
	
	public void waitUntilFrameAvailableSwitchToIt(int frameIndex) {
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		wait.until(frameToBeAvailableAndSwitchToIt(frameIndex));
	}
	
	public void waitUntilFrameAvailableSwitchToIt(String locatorIdStr, String locatorStr) {
		WebDriverWait wait = new WebDriverWait(driver,TestCase.DEFAULT_TIMEOUT);
		By locator = getLocator(locatorIdStr,locatorStr);
		wait.until(frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	public void openNewTab() {
		((JavascriptExecutor) driver).executeScript("window.open()");
	}
	
	public void switchToTab(int num) {
		driver.switchTo().window(driver.getWindowHandles().toArray()[num].toString());
	}

	public void verifyStringsEqual(String str1, String str2) {
		//Workaround for if a string you want to compare contains a line break
		String string1 = str1.replace("\n", " ").replace("\r", " ");
		String string2 = str2.replace("\n", " ").replace("\r", " ");
		assertTrue(string1.equals(string2),"Error: string1 is not equal to string2");
	}
	
	public String getHostname() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		return ip.getHostName();
	}

}







