/**
 * 
 */
package com.UITests.SurveyCenter.page;



import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.UITests.page.Page;

public class SurveydetailsPage extends Page {

	@FindBy(name = "survey-title")
	private WebElement surveyTitleField;

	@FindBy(name = "survey-description")
	private WebElement surveyDescriptionField;

	@FindBy(name = "survey-code")
	private WebElement surveyCodeField;

	@FindBy(className = "end-date-month")
	private WebElement endDateMonthField;

	@FindBy(className = "end-date-day")
	private WebElement endDateDayField;

	@FindBy(className = "end-date-year")
	private WebElement endDateYearField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='+ Add Question']")
	private WebElement addQuestionButton;

	@FindBy(xpath = "//*[contains(@class,'survey-question')][1]/descendant::input[contains(@class,'question-title')]")
	private WebElement question1TitleField;
	
	@FindBy(xpath = "//*[contains(@class,'survey-question')][2]/descendant::input[contains(@class,'question-title')]")
	private WebElement question2TitleField;
	
	@FindBy(xpath = "//*[contains(@class,'survey-question')][3]/descendant::input[contains(@class,'question-title')]")
	private WebElement question3TitleField;

	@FindBy(xpath = "/descendant::select[@class='question-type-select'][1]")
	private WebElement question1TypeSelectDropdown;

	@FindBy(xpath = "/descendant::select[@class='question-type-select'][2]")
	private WebElement question2TypeSelectDropdown;
	
	@FindBy(xpath = "/descendant::select[@class='question-type-select'][3]")
	private WebElement question3TypeSelectDropdown;

	@FindBy(className = "plus")
	private WebElement plus;

	@FindBy(className = "answer-title")
	private WebElement answerTitleField;

	@FindBy(xpath = "/descendant::button[normalize-space(.)='Save']")
	private WebElement saveButton;

	@FindBy(className = "start-date-month")
	private WebElement startDateMonthField;

	@FindBy(className = "start-date-day")
	private WebElement startDateDayField;

	@FindBy(className = "start-date-year")
	private WebElement startDateYearField;

	@FindBy(className = "ui-state-error")
	private WebElement uiStateErrorField;

	

	private static final String[] TITLE_WORDS = { "Survey", "Center" };

	public SurveydetailsPage(WebDriver driver) {
		super(driver, TITLE_WORDS);
	}
	
	public static SurveydetailsPage using(WebDriver driver) {
		return new SurveydetailsPage(driver);
	}

	public SurveydetailsPage setSurveyTitleField(String text) {
		waitFor(surveyTitleField).clear();
		surveyTitleField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setSurveyDescriptionField(String text) {
		waitFor(surveyDescriptionField).clear();
		surveyDescriptionField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setSurveyCodeField(String text) {
		waitFor(surveyCodeField).clear();
		surveyCodeField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setEndDateMonthField(String text) {
		waitFor(endDateMonthField).clear();
		endDateMonthField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setEndDateDayField(String text) {
		waitFor(endDateDayField).clear();
		endDateDayField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setEndDateYearField(String text) {
		waitFor(endDateYearField).clear();
		endDateYearField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage clickAddQuestionButton() {
		click(addQuestionButton);
		return this;
	}

	public SurveydetailsPage setQuestion1TitleField(String text) {
		waitFor(question1TitleField).clear();
		question1TitleField.sendKeys(text);
		//JavascriptExecutor executor = (JavascriptExecutor)driver;
		//executor.executeScript("arguments[0].setAttribute('value','" + text + "');",
		//		question1TitleField);
		return this;
	}
	
	public SurveydetailsPage setQuestion2TitleField(String text) {
		waitFor(question2TitleField).clear();
		question2TitleField.sendKeys(text);
		return this;
	}
	
	public SurveydetailsPage setQuestion3TitleField(String text) {
		waitFor(question3TitleField).clear();
		question3TitleField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage selectQuestion1TypeSelectDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(question1TypeSelectDropdown).getFirstSelectedOption().getText().trim()
				.length() >= 0);
		Select dropdown = new Select(question1TypeSelectDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}


	public SurveydetailsPage selectQuestion2TypeSelectDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(question2TypeSelectDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(question2TypeSelectDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}
	
	public SurveydetailsPage selectQuestion3TypeSelectDropdown(String text) {
		WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT_FOR_ELEMENT_TIMEOUT);
		wait.ignoring(StaleElementReferenceException.class);
		wait.until(webdriver -> new Select(question3TypeSelectDropdown).getFirstSelectedOption().getText().trim().length() >= 0);
		Select dropdown = new Select(question3TypeSelectDropdown);
		dropdown.selectByVisibleText(text);
		return this;
	}

	public SurveydetailsPage clickPlus() {
		click(plus);
		return this;
	}

	public SurveydetailsPage setAnswerTitleField(String text) {
		waitFor(answerTitleField).clear();
		answerTitleField.sendKeys(text);
		return this;
	}


	public SurveydetailsPage clickSaveButton() {
		click(saveButton);
		return this;
	}
	

	public SurveydetailsPage setStartDateMonthField(String text) {
		waitFor(startDateMonthField).clear();
		startDateMonthField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setStartDateDayField(String text) {
		waitFor(startDateDayField).clear();
		startDateDayField.sendKeys(text);
		return this;
	}

	public SurveydetailsPage setStartDateYearField(String text) {
		waitFor(startDateYearField).clear();
		startDateYearField.sendKeys(text);
		return this;
	}

	public String getUiStateErrorFieldText() {
		return waitFor(uiStateErrorField).getText();
	}
	
	public String getStartMonthValue() {
		waitUntilElementVisible(startDateMonthField);
		return startDateMonthField.getAttribute("value");
	}
	
	public String getStartDayValue() {
		waitUntilElementVisible(startDateDayField);
		return startDateDayField.getAttribute("value");
	}
	
	public String getStartYearValue() {
		waitUntilElementVisible(startDateYearField);
		return startDateYearField.getAttribute("value");
	}
	
	public String getEndMonthValue() {
		waitUntilElementVisible(endDateMonthField);
		return endDateMonthField.getAttribute("value");
	}
	
	public String getEndDayValue() {
		waitUntilElementVisible(endDateDayField);
		return endDateDayField.getAttribute("value");
	}
	
	public String getEndYearValue() {
		waitUntilElementVisible(endDateYearField);
		return endDateYearField.getAttribute("value");
	}

}