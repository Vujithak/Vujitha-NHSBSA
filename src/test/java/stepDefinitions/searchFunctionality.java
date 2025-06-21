package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

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
public void user_enter_partial_location_and_selects_from_suggestions(String partialLocation, String locationSuggested) {
    sp.enterLocation(partialLocation, locationSuggested);
}
@When("user enters job title {string}")
public void user_enters_job_title(String Jobtitle) {
    sp.enterJobTitle(Jobtitle);
}

@And("user clicks on search button")
public void user_clicks_on_search_button() {
   sp.clickSearch();
}

@And("user sorts results by {string}")
public void user_sorts_results_by_newest_date(String sortByText) {
    sp.selectSortOption(sortByText);
}

@Then("user should be able to view the results realted to {string} location.")
public void user_should_be_able_to_view_the_results_realted_to_location(String expectedListedLocation) {
	  isValid = sp.validateListedJobResultsWithLocation(expectedListedLocation);
      Assert.assertTrue("Few of the listed job results are not matching with the expected location: " + expectedListedLocation, isValid);
}
@Then("user should be able to see the results related to {string} job title")
public void user_should_be_able_to_see_the_results_related_to_job_title(String expectedListedJobTitle) {
	isValid = sp.validateListedJobResultsWithJobTitle(expectedListedJobTitle);
    Assert.assertTrue("Listed job results are not matching with the expected Job Title: " + expectedListedJobTitle, isValid);
}


}
