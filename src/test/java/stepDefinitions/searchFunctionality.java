package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import factory.Base;
import io.cucumber.java.en.*;
import pageObjects.SearchPage;

public class searchFunctionality {

	WebDriver driver = Base.getDriver();
	SearchPage jobSearchPage = new SearchPage();
	boolean isValid;

	@Given("user is on the NHS job search page with title {string}")
	public void user_is_on_the_nhs_job_search_page_with_title(String expectedTitle) {

		String actualTitle = driver.getTitle();
		Assert.assertEquals("Mistmatch in page Title", expectedTitle, actualTitle);
	}

	@And("click on Accept Cookies button.")
	public void click_on_accept_cookies_button() {
		jobSearchPage.acceptCookies();
	}

	@When("user performs search with {string}, {string}, {string}, {string}, {string}, {string}")
	public void user_performs_search_with(String jobTitle, String location, String distance, String jobReference,String employer, String payRange) {
		jobSearchPage.resetSearchPage();
		jobSearchPage.enterJobTitle(jobTitle);
		
		
		jobSearchPage.enterLocation(location);
		
		if (!location.isBlank() && !distance.trim().isEmpty()) {
			jobSearchPage.selectDistance(distance);
		}
		jobSearchPage.clickSearchOptions();
		
		jobSearchPage.enterJobReference(jobReference);
		jobSearchPage.enterEmployer(employer);
		
		if (!payRange.trim().isEmpty()) {
			jobSearchPage.selectPayRange(payRange);
		}

	}

	@And("user clicks on search button")
	public void user_clicks_on_search_button() {
		jobSearchPage.clickSearch();

	}

	@And("user clicks on clear filter button")
	public void user_clicks_on_clear_filter_button() {
		jobSearchPage.clickClearFilters();
		jobSearchPage.clickSearchOptions();
	}

	@Then("user should get list of jobs with matching preferences")
	public void user_should_get_list_of_jobs_with_matching_preferences() {
		Assert.assertFalse("Job Results not found:", jobSearchPage.verifyingResultsAreDisplayed().isEmpty());

	}

	@Then("all fields should be blank")
	public void all_fields_should_be_blank() {
		Assert.assertTrue("Job Title is not blank:", jobSearchPage.getJobTitle().trim().isEmpty());
		Assert.assertTrue("Location is not blank:", jobSearchPage.getJobLocation().trim().isEmpty());
		Assert.assertTrue("Location is not blank:", jobSearchPage.getDistance().trim().isEmpty());
		Assert.assertTrue("Job Reference is not blank:", jobSearchPage.getJobReference().trim().isEmpty());
		Assert.assertTrue("Employer is not blank:", jobSearchPage.getEmployer().trim().isEmpty());
		Assert.assertTrue("PayRange is not blank:", jobSearchPage.getPayRange().trim().isEmpty());
	}

	@Then("the results page should display message {string}")
	public void the_results_page_should_display_message(String resultPageMessage) {
		String actualMessage = jobSearchPage.getErrorMessage().trim();
		Assert.assertEquals("Expected 'No result found' message not displayed", resultPageMessage, actualMessage);
	}

	@And("the search message should contain {string}")
	public void the_search_message_should_contain(String searchResultMessage) {
		System.out.println("Actual message: " + jobSearchPage.getSearchResultMessage());
		Assert.assertTrue("Expected message not found:",
				jobSearchPage.getSearchResultMessage().contains(searchResultMessage));
	}

	@And("user sorts results by {string}")
	public void user_sorts_results_by(String sortByNewestDate) {
		jobSearchPage.selectSortOption(sortByNewestDate);
	}

	@And("the job results should be sorted with newest Date Posted")
	public void the_job_results_should_be_sorted_with_newest_date_posted() {
		jobSearchPage.areResultsSortedByNewestDate();

	}

}
