Feature: NHS Job Search functionality.
Background:
Given user is on the NHS job search page with title "Search for jobs in the NHS"
    
 Scenario Outline: Validate Searchfunctionality by providing only Job Title
    When user enters job title "<JobTitle>"
    And user clicks on search button
    Then user should see job results containing "<JobTitle>"
    And  user sorts results by "Date Posted (newest)"
    Then the job results should be sorted with newest Date Posted
Examples:
         | JobTitle               |
         | Cardiac Physiologist   | 
         | Staff Nurse            |

         
 #Scenario Outline: Validate search functionality by providing only Job Location 
    #When user enter partial location "<PartialLocation>" and selects "<Location>" from suggestions
    #And  user clicks on search button
    #And  user sorts results by "Date Posted (newest)"
    #Then user should be able to view the results with the most recent date posted
    #And the message should contain "<Location>"
#Examples:
         #| PartialLocation | Location   |
         #| Liver           | Liverpool  |
         #| Man             | Manchester |
#
#Scenario Outline: Validate search functionality by providing valid - Job title/skill and Location
 #When user enters job title "<JobTitle>" and location "<Location>"
 #And user clicks on search button
 #And user sorts results by "Date Posted (newest)"
 #Then user should be able to view the results with the most recent date posted
  #Examples:
      #| JobTitle                    | Location     | 
      #| Staff Nurse                 | London       |
      #| Cardiac Physiologist        | L15 3HP      |
      #| Practice Nurse              | Manchester   |
#
 #Scenario: Validate Searchfunctionality by providing all fields
    #When user enters job title "Staff Nurse" and location "London"
    #And  user selects the distance "+10 Miles"
    #And  user clicks on search options link to expand search
    #And  user enters job reference "1316185502" Employer "Healthcare" and Pay Range "£10,000 to £20,000"
    #And  user clicks on search button
    #And  user sorts results by "Date Posted (newest)"
    #Then user should be able to view the results with the most recent date posted
    #
 #Scenario: Validate clearFilters button functionality
 #When user enters job title "Healthcare Assistant" and location "Manchester"
 #And  user selects the distance "+10 Miles"
 #And  user clicks on search options link to expand search
 #And  user enters job reference "1316185502" Employer "Healthcare" and Pay Range "£10,000 to £20,000"
 #And  user clicks on clear filter button
 #Then all fields should be blank
       #
 #Scenario: Validate search functionality by not providing any input and Next button is visible
 #When user clicks on search button
 #And  user sorts results by "Date Posted (newest)"
 #Then user should be able to view the results with the most recent date posted
 #And the message should contain "jobs found" 
 #And user should be able to view Next button
#
#Scenario Outline: Validate Searchfunctionality by providing special characters in Job Title field
    #When user enters job title "<JobTitle>"
    #And  user clicks on search button
    #Then the results page should display message "No result found"
    #And the message should contain "No result found for <JobTitle>"  
#Examples:
           #| JobTitle  |
           #| @$$$$@    |
           #| ###@@!!   |
      #

    
    
 
 

     
    