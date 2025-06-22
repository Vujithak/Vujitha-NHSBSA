Feature: NHS Job Search functionality.
Background:
Given user is on the NHS job search page with title "Search for jobs in the NHS"
 
 #Scenario: Validate search functionality by providing only-Job Location 
    #When user enter partial location "Liver" and selects "Liverpool" from suggestions
    #And  user clicks on search button
    #And  user sorts results by "Date Posted (newest)"
    #Then user should be able to view the results with the most recent date posted
    #And  the message should contain "Liverpool"
    #
 #Scenario: Validate Searchfunctionality by providing only-Job Title
    #When user enters job title "Healthcare Assistant"
    #And user clicks on search button
    #And  user sorts results by "Date Posted (newest)"
    #Then user should be able to view the results with the most recent date posted
    #And  the message should contain "Healthcare Assistant"
    #
 #Scenario: Validate clearFilters button functionality
 #When user enters job title "Healthcare Assistant" and location "Manchester"
 #And user clicks on clear filter button
 #Then job title and location fields should be blank
    #
#Scenario Outline: Validate search functionality by providing valid - Job title/skill and Location
 #When user enters job title "<JobTitle>" and location "<Location>"
 #And user clicks on search button
 #And user sorts results by "Date Posted (newest)"
 #Then user should be able to view the results with the most recent date posted
  #Examples:
      #| JobTitle                    | Location     | 
      #| Staff Nurse                 | London       |
      #| Cardiac Physiologist        | L15 3HP       |
      #| Practice Nurse              | Manchester   |
      
 Scenario: Validate search functionality by not providing any input and navigations of page
 When user clicks on search button
 Then job results should be listed with the message contains "jobs found" 
 And user should be able to click on Next page and Previous page buttons

     
    