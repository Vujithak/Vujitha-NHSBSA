Feature: NHS Job Search functionality.
Background:
Given user is on the NHS job search page with title "Search for jobs in the NHS"
 
 Scenario: Validate search functionality by providing only-Job Location 
    When  user enter partial location "Liver" and selects "Liverpool" from suggestions
    And   user clicks on search button
    And   user sorts results by "Date Posted (newest)"
    Then user should be able to view the results realted to "Liverpool" location with the Date Posted (newest).
    
 Scenario: Validate Searchfunctionality by providing only-Job Title
    When user enters job title "Healthcare Assistant"
    And user clicks on search button
    Then user should be able to see the results related to "Healthcare Assistant" job title
    
 Scenario: Validate clearFilters button functionality
 When user enters job title "Healthcare Assistant"
 And user clicks on search button
 And user navigates back to search for jobs page
 And user clicks on Clear filters button
 Then Job title field should be blank
    
Scenario outline : Validate search functionality by providing valid - Job title/skill and Location
 When user enters job title or skill "<JobTitleOrSkill>"
 And user enters "<Location>"
 And user clicks on search button
 And   user sorts results by "Date Posted (newest)"
 Then user should be able to see results realted to "<JobTitle>" and "<Location>" with the Date Posted (newest).
 
  Examples:
      | JobTitle                    | Location     |
      | Staff Nurse                 | London       |
      | Cardiac Physiologist        | L153HP       |
      | Practice Nurse              | Manchester   |
      
 Scenario: Validate search functionality by not providing any input
 When user clicks on search button
 Then results should be displayed with the message contains "jobs found" 
     
    