package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;

import factory.Base;
import io.cucumber.java.en.*;
import pageObjects.SearchPage;


public class searchFunctionality {
	
WebDriver driver = Base.getDriver();
SearchPage sp = new SearchPage(driver);
boolean isValid;

@Given("user is on the NHS job search page with title {string}")
public void user_is_on_the_nhs_job_search_page_with_title(String expectedTitle) {
	
	String actualTitle = driver.getTitle();
    Assert.assertEquals("Mistmatch in page Title", expectedTitle, actualTitle);
}

@When("user enter partial location {string} and selects {string} from suggestions")
public void user_enter_partial_location_and_selects_from_suggestions(String partialLocation, String joblocation) {
    sp.enterLocation(partialLocation, joblocation);
}
@When("user enters job title {string}")
public void user_enters_job_title(String jobtitle) {
    sp.enterJobTitle(jobtitle);
}
@When("user enters job title {string} and location {string}")
public void user_enters_job_title_and_location(String jobTitle, String jobLocation) {
    sp.enterJobTitle(jobTitle);
    sp.enterJobLocation(jobLocation);
}

@And("user selects the distance {string}")
public void user_selects_the_distance(String distance) {
    sp.selectDistance(distance);
}

@And("user clicks on search options link to expand search")
public void user_clicks_on_search_options_link_to_expand_search() {
     sp.clickSearchOptions();
}

@And("user enters job reference {string} Employer {string} and Pay Range {string}")
public void user_enters_job_reference_employer_and_pay_range(String jobReference, String employer, String payRange) {
     sp.enterJobReference(jobReference);
     sp.enterEmployer(employer);
     sp.selectPayRange(payRange);
}

@And("user clicks on search button")
public void user_clicks_on_search_button() {
   sp.clickSearch();
}

@And("user sorts results by {string}")
public void user_sorts_results_by_newest_date(String sortByText) {
    sp.selectSortOption(sortByText);
}

@And("user clicks on clear filter button")
public void user_clicks_on_clear_filter_button() {
       sp.clickClearFilters();
       sp.clickSearchOptions();
}

@And("user should be able to view Next button")
public void user_should_be_able_to_view_next_button() {
    sp.NextButtonVisibility();
}

@Then("all fields should be blank")
public void all_fields_should_be_blank() {
    Assert.assertTrue("Job Title is not blank:", sp.getJobTitle().trim().isEmpty());
    Assert.assertTrue("Location is not blank:", sp.getJobLocation().trim().isEmpty());
    Assert.assertFalse("Distance is Enabled:", sp.validatingDistanceIsEnabled());
    Assert.assertTrue("Job Reference is not blank:", sp.getJobLocation().trim().isEmpty());
    Assert.assertTrue("Employer is not blank:", sp.getEmployer().trim().isEmpty());
    Assert.assertTrue("PayRange is not blank:", sp.getPayRange().trim().isEmpty());
}

@Then("user should be able to view the results with the most recent date posted")
public void user_should_be_able_to_view_the_results_with_the_most_recent_date_posted() {
	Assert.assertTrue("Dates are not sorted with newest date:", sp.areResultsSortedByNewestDate());
	Assert.assertFalse("Job Results not found:", sp.verifyingResultsAreDisplayed().isEmpty());
}

@Then("the message should contain {string}")
public void the_message_should_contain(String resultMessage) {
    String actualSearchResultMessage = sp.getSearchResultMessage();
    assertThat("Result message does not contain expected text: " + actualSearchResultMessage, containsStringIgnoringCase(resultMessage));
}

//@Then("job results should be listed with the message contains {string}")
//public void results_should_able_to_view_the_jobs_listed_with_the_message_contains(String resultMessage) {
//   sp.areResultsSortedByNewestDate();
//   String actualMessage = sp.getSearchResultMessage();
//  assertThat("Result message does not contain expected text", actualMessage, containsStringIgnoringCase(resultMessage));
//   
//}




}
