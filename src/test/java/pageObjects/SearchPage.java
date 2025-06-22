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

	public SearchPage(WebDriver driver) 
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@data-test=\"search-jobTitle-input\"]")	
	WebElement	jobTitleOrSkill_txt; 
	
	@FindBy(xpath="//input[@data-test=\"search-location-input\"]")	
	WebElement	location_txt; 
	
	@FindBy(xpath="//ul[@id=\"location__listbox\"]/li")
	List<WebElement> listOfRelatedLocations;
	
	@FindBy(css = "input[type=\"submit\"]")
	WebElement search_btn;
	
	@FindBy(id = "sort") 
	WebElement sortBy_dropDown;
	
	@FindBy(xpath = "//h1[@id=\"search-results-heading\"]")
	WebElement searchResultJobHeading;
	
	@FindBy(xpath = "//a[@data-test='search-result-job-title']")
    List<WebElement> jobTitles;
	
	@FindBy(xpath = "//li[contains(@data-test, \"publicationDate\")]/strong")
	List<WebElement> newestDates;
	
	@FindBy(xpath ="//div[@data-test =\"search-result-location\"]/h3/div")
	List<WebElement> jobsWithFilteredLocation;
    
    @FindBy(id = "clearFilters")
    WebElement clearFilters_btn;
    
    @FindBy(xpath = "//span[text()=\"Next\"]")
    WebElement next_btn;
    
    @FindBy(xpath = "//span[text()=\"Previous\"]")
    WebElement previous_btn;
	
	public void enterJobTitle(String jobTitle) {
		jobTitleOrSkill_txt.clear();
		jobTitleOrSkill_txt.sendKeys(jobTitle);
    }
	public void enterJobLocation(String jobLocation) {
		location_txt.clear();
		location_txt.sendKeys(jobLocation);
    }
	
	public String getJobTitle() {
		return jobTitleOrSkill_txt.getAttribute("value");
    }
	public String getJobLocation() {
		return location_txt.getAttribute("value");
    }


    public void enterLocation(String partialLocation, String joblocation) {
    	location_txt.clear();
    	location_txt.sendKeys(partialLocation);
    	
    	for(WebElement selectedLocation : listOfRelatedLocations) 
    	{
    		String actualLocation = selectedLocation.getText().trim().toLowerCase();
    		if(actualLocation.contains(joblocation.trim().toLowerCase())) 
    		{
    			selectedLocation.click();
    			break;
    		}
    		
    	}
    }

    public void clickSearch() {
    	search_btn.click();
    }
    
    public void clickClearFilters() {
    	clearFilters_btn.click();
    }

    public String getSearchResultMessageJobTitle() {
    	
    	return searchResultJobHeading.getText();
    	
    }
    public void selectSortOption(String sortByText) {
        Select dropdown = new Select(sortBy_dropDown);
        dropdown.selectByVisibleText(sortByText);
    }

// checking the results are sorted
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
    
    public void clickNextButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(next_btn));
    	if(next_btn.isDisplayed() && next_btn.isEnabled()) 
    	{
    		next_btn.click();
    	}
    }

    public void clickPreviousButton() {
    	wait.until(ExpectedConditions.elementToBeClickable(previous_btn));
    	if(previous_btn.isDisplayed() && previous_btn.isEnabled()) 
    	{
    		previous_btn.click();
    	}
    }

    
	
	
	
	
	
	
	
	
}
