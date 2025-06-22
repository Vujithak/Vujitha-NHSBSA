package pageObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class SearchPage extends BaseClass {

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@data-test=\"search-jobTitle-input\"]")
	WebElement jobTitleOrSkill_txt;

	@FindBy(xpath = "//input[@data-test=\"search-location-input\"]")
	WebElement location_txt;

	@FindBy(xpath = "//a[@id =\"searchOptionsBtn\"]")
	WebElement searchOptions_btn;

	@FindBy(xpath = "//input[@data-test=\"search-jobReference-input\"]")
	WebElement jobReference_txt;

	@FindBy(xpath = "//input[@data-test=\"search-employer-input\"]")
	WebElement searchEmployer_txt;

	@FindBy(xpath = "//ul[@id=\"location__listbox\"]/li")
	List<WebElement> listOfRelatedLocations;

	@FindBy(css = "input[type=\"submit\"]")
	WebElement search_btn;

	@FindBy(id = "distance")
	WebElement distance_dropDown;

	@FindBy(id = "payRange")
	WebElement payRange_dropDown;

	@FindBy(id = "sort")
	WebElement sortBy_dropDown;

	@FindBy(xpath = "//h1[@id=\"search-results-heading\"]")
	WebElement searchResultJobHeading;

	@FindBy(xpath = "//a[@data-test='search-result-job-title']")
	List<WebElement> jobTitles;

	@FindBy(xpath = "//li[contains(@data-test, \"publicationDate\")]/strong")
	List<WebElement> newestDates;

	@FindBy(xpath = "//div[@data-test =\"search-result-location\"]/h3/div")
	List<WebElement> jobsWithFilteredLocation;

	@FindBy(id = "clearFilters")
	WebElement clearFilters_btn;

	@FindBy(xpath = "//span[text()=\"Next\"]")
	WebElement next_btn;

	@FindBy(xpath = "//span[text()=\"Previous\"]")
	WebElement previous_btn;

	/**
	 * Enters a job title into the search input, clearing any previous text.
	 */
	public void enterJobTitle(String jobTitle) {
		jobTitleOrSkill_txt.clear();
		jobTitleOrSkill_txt.sendKeys(jobTitle);
	}

	/**
	 * Inputs a full job location into the search location field.
	 */
	public void enterJobLocation(String jobLocation) {
		location_txt.clear();
		location_txt.sendKeys(jobLocation);
	}

	/**
	 * Enters a job Reference into the search input, clearing any previous text.
	 */
	public void enterJobReference(String jobReference) {
		jobReference_txt.clear();
		jobReference_txt.sendKeys(jobReference);
	}

	/**
	 * Enters an Employer name into the search input, clearing any previous text.
	 */
	public void enterEmployer(String employer) {
		searchEmployer_txt.clear();
		searchEmployer_txt.sendKeys(employer);
	}

	/**
	 * Returns the entered value in the JobTitle field.
	 */
	public String getJobTitle() {
		return jobTitleOrSkill_txt.getAttribute("value");
	}

	/**
	 * Returns the entered value in the Location field.
	 */
	public String getJobLocation() {
		return location_txt.getAttribute("value");
	}

	/**
	 * Enters a partial location and selects the desired option from autocomplete
	 * suggestions
	 */
	public void enterLocation(String partialLocation, String joblocation) {
		location_txt.clear();
		location_txt.sendKeys(partialLocation);

		for (WebElement selectedLocation : listOfRelatedLocations) {
			String actualLocation = selectedLocation.getText().trim().toLowerCase();
			if (actualLocation.contains(joblocation.trim().toLowerCase())) {
				selectedLocation.click();
				break;
			}

		}
	}

	/**
	 * Clicks the 'Search Options' button to expand additional filters.
	 */
	public void clickSearchOptions() {
		searchOptions_btn.click();
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

		return searchResultJobHeading.getText();

	}

	/**
	 * Selects a distance value from the dropdown.
	 */
	public void selectDistance(String distance) {
		wait.until(ExpectedConditions.elementToBeClickable(distance_dropDown));
		Select distanceDropdown = new Select(distance_dropDown);
		distanceDropdown.selectByVisibleText(distance);
	}

	/**
	 * Selects a pay range from the dropdown filter.
	 */
	public void selectPayRange(String payRange) {
		Select payRangeDropdown = new Select(payRange_dropDown);
		payRangeDropdown.selectByVisibleText(payRange);
	}

	/**
	 * Sorts the search results based on the selected sorting option.
	 */
	public void selectSortOption(String sortByText) {
		Select dropdown = new Select(sortBy_dropDown);
		dropdown.selectByVisibleText(sortByText);
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
	 * Navigates to the next page of search results.
	 */
	public void clickNextButton() {
		wait.until(ExpectedConditions.elementToBeClickable(next_btn));
		if (next_btn.isDisplayed() && next_btn.isEnabled()) {
			next_btn.click();
		}
	}

	/**
	 * Navigates to the previous page of search results.
	 */
	public void clickPreviousButton() {
		wait.until(ExpectedConditions.elementToBeClickable(previous_btn));
		if (previous_btn.isDisplayed() && previous_btn.isEnabled()) {
			previous_btn.click();
		}
	}

}
