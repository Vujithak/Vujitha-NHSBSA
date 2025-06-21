package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
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
	
	@FindBy(xpath = "//a[@data-test='search-result-job-title']")
    List<WebElement> jobTitles;
	
	@FindBy(xpath = "//li[contains(@data-test, \"publicationDate\")]/strong")
	List<WebElement> newestDates;
	
	@FindBy(xpath ="//div[@data-test =\"search-result-location\"]/h3/div")
	List<WebElement> jobsWithFilteredLocation;

    @FindBy(xpath = "//span[text()=\"Next\"]")
    WebElement nextButton;
	
	public void enterJobTitle(String Jobtitle) {
		jobTitleOrSkill_txt.clear();
		jobTitleOrSkill_txt.sendKeys(Jobtitle);
    }

    public void enterLocation(String partialLocation, String locationSuggested) {
    	location_txt.clear();
    	location_txt.sendKeys(partialLocation);
    	
    	for(WebElement selectedLocation : listOfRelatedLocations) 
    	{
    		String actualLocation = selectedLocation.getText().trim().toLowerCase();
    		if(actualLocation.contains(locationSuggested.trim().toLowerCase())) 
    		{
    			selectedLocation.click();
    			break;
    		}
    		
    	}
    }

    public void clickSearch() {
    	search_btn.click();
    }

    public void selectSortOption(String sortByText) {
        Select dropdown = new Select(sortBy_dropDown);
        dropdown.selectByVisibleText(sortByText);
    }
    
    public boolean validateListedJobResultsWithLocation(String expectedListedLocation) {
    	
    	System.out.println("Number of Jobs based on location is listed:" +jobsWithFilteredLocation.size());
    	for(WebElement eachJoblocationElement : jobsWithFilteredLocation) 
    	{
    		String actualListedLocation = eachJoblocationElement.getText().trim().toLowerCase();
    		if(!actualListedLocation.contains(expectedListedLocation.trim().toLowerCase()))
    		{
    			System.out.println("Location is not matching: "+actualListedLocation);
    			return false;
    		}
    		
    	}
		return true;
    }
    
    public boolean validateListedJobResultsWithJobTitle(String expectedListedJobTitle) {
    	
    	for(WebElement eachJobTitleElement : jobTitles) 
    	{
    		String actualListedJobTitle = eachJobTitleElement.getText().trim().toLowerCase();
    		if(!actualListedJobTitle.contains(expectedListedJobTitle.trim().toLowerCase()))
    		{
    			System.out.println("JobTitle is not matching: "+actualListedJobTitle);
    			return false;
    		}
    		
    	}
		return true;
    }
    
    
	
	
	
	
	
	
	
	
}
