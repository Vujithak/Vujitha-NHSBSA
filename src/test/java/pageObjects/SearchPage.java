package pageObjects;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import factory.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage {

	WebDriver driver;
	WebDriverWait wait;

	public SearchPage() {
		this.driver = Base.getDriver();
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(Base.getDriver(), Duration.ofSeconds(10));
	}

	@FindBy(id = "accept-cookies")
	WebElement acceptCookies_bt;

	@FindBy(xpath = "//input[@data-test=\"search-jobTitle-input\"]")
	WebElement jobTitleOrSkill_txt;

	@FindBy(xpath = "//input[@data-test=\"search-location-input\"]")
	WebElement location_txt;

	@FindBy(xpath = "//input[@data-test=\"search-jobReference-input\"]")
	WebElement jobReference_txt;

	@FindBy(id = "employer")
	WebElement employer_txt;

	@FindBy(xpath = "//ul[@id=\"location__listbox\"]//li")
	List<WebElement> listOfRelatedLocations;

	@FindBy(id = "distance")
	WebElement distance_input;

	@FindBy(id = "payRange")
	WebElement payRange_input;

	@FindBy(id = "sort")
	WebElement sortBy_dropDown;

	@FindBy(xpath = "//h1[@id=\"search-results-heading\"]")
	WebElement searchResultJobHeading_msg;

	@FindBy(xpath = "//main[contains(@class, \"nhsuk-main-wrapper\")]//*[@id=\"no-result-title\" or @data-test=\"search-result-query\"]")
	WebElement resultPage_msg;

	@FindBy(xpath = "//li[@data-test=\"search-result\"]")
	List<WebElement> jobResults;

	@FindBy(xpath = "//li[contains(@data-test, \"publicationDate\")]/strong")
	List<WebElement> newestDates;

	@FindBy(xpath = "//div[@data-test =\"search-result-location\"]/h3/div")
	List<WebElement> jobsWithFilteredLocation;

	@FindBy(xpath = "//a[@id =\"searchOptionsBtn\"]")
	WebElement searchOptions_btn;

	@FindBy(xpath = "//input[@data-test=\"search-button\"]")
	WebElement search_btn;

	@FindBy(id = "clearFilters")
	WebElement clearFilters_btn;

	@FindBy(xpath = "//span[text()=\"Next\"]")
	WebElement next_btn;

	/**
	 * This method accepts cookies
	 */
	public void acceptCookies() {
		acceptCookies_bt.click();
	}

	/**
	 * Enters the job title
	 */
	public void enterJobTitle(String jobTitle) {
		jobTitleOrSkill_txt.clear();
		jobTitleOrSkill_txt.sendKeys(jobTitle);
	}
	/**
	 * Enters the job title
	 */
	public void enterLocation(String location) {
		location_txt.clear();
		location_txt.sendKeys(location);
	}

	/**
	 * Enters a job Reference into the search input
	 */
	public void enterJobReference(String jobReference) {
		jobReference_txt.clear();
		jobReference_txt.sendKeys(jobReference);
	}

	/**
	 * Enters an Employer name into the search input
	 */
	public void enterEmployer(String employer) {
		employer_txt.clear();
		employer_txt.sendKeys(employer);
	}

	/**
	 * Clicks the 'Search Options' button to expand additional filters.
	 */
	public void clickSearchOptions() {
	    wait.until(ExpectedConditions.elementToBeClickable(searchOptions_btn)).click();
	}

	/**
	 * Submits the job search form by clicking the search button.
	 */
	public void clickSearch() {
		search_btn.click();
	}

	/**
	 * Clears all selected filters by clicking the 'Clear Filters' button.
	 */
	public void clickClearFilters() {
		clearFilters_btn.click();
	}

	/**
	 * Returns the heading message from the search results page.
	 */
	public String getSearchResultMessage() {

		return searchResultJobHeading_msg.getText().trim();

	}

	/**
	 * Returns the result page message when no results found.
	 */
	public String getErrorMessage() {

		return resultPage_msg.getText();

	}

	/**
	 * Selects a distance value from the dropdown.
	 */
	public void selectDistance(String distance) {
		wait.until(ExpectedConditions.elementToBeClickable(distance_input));

		if (distance_input.isEnabled()) {
			Select select = new Select(distance_input);
			select.selectByValue(distance);
		} else {
			System.out.println("Distance dropdown is disabled.");
		}
	}

	/**
	 * Selects a pay range
	 */
	public void selectPayRange(String payRange) {
		Select payRangeDropdown = new Select(payRange_input);
		payRangeDropdown.selectByVisibleText(payRange);
	}

	/**
	 * Selects the SortOption as Date Posted (newest)
	 */
	public void selectSortOption(String sortByNewestDate) {
		Select dropdown = new Select(sortBy_dropDown);
		dropdown.selectByVisibleText(sortByNewestDate);
	}

	/**
	 * Verifies if the search results are sorted in descending order by posted date.
	 */
	public boolean areResultsSortedByNewestDate() {

		List<LocalDate> postedDates = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);

		for (WebElement el : newestDates) {
			String dateText = el.getText().trim(); // e.g., "21 June 2025"
			LocalDate date = LocalDate.parse(dateText, formatter);
			postedDates.add(date);
		}

		// Create a copy and sort it in descending order
		List<LocalDate> sortedDates = new ArrayList<>(postedDates);
		sortedDates.sort(Comparator.reverseOrder());

		return postedDates.equals(sortedDates);
	}

	/**
	 * Validates results are displaying when search is performed.
	 */
	public List<WebElement> verifyingResultsAreDisplayed() {
		System.out.println("No of results found:" + jobResults.size());
		return jobResults;
	}

	/**
	 * Returns the entered value in the JobTitle field.
	 */
	public String getJobTitle() {
		String jobTitlevalue = jobTitleOrSkill_txt.getAttribute("value");
		return jobTitlevalue;
	}

	/**
	 * Returns the entered value in the Location field.
	 */
	public String getJobLocation() {
		String locationvalue = location_txt.getAttribute("value");
		return locationvalue;
	}

	/**
	 * Returns the entered value in the distance field.
	 */
	public String getDistance() {
		String distancevalue = distance_input.getAttribute("value");
		return distancevalue;
	}

	/**
	 * Returns the entered value in the Job Reference field.
	 */
	public String getJobReference() {
		String jobReferencevalue = jobReference_txt.getAttribute("value");
		return jobReferencevalue;
	}

	/**
	 * Returns the entered value in the Job Employer field.
	 */
	public String getEmployer() {
		String employervalue = employer_txt.getAttribute("value");
		return employervalue;
	}

	/**
	 * Returns the value in the pay range field.
	 */
	public String getPayRange() {
		String payRangevalue = payRange_input.getAttribute("value");
		return payRangevalue;
	}

	public void resetSearchPage() {
	    // Wait for job title input field to ensure the page is loaded
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//input[@data-test='search-jobTitle-input']")));
	}
	

}
