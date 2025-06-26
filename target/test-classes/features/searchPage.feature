Feature: NHS Job Search functionality.
Background:
Given user is on the NHS job search page with title "Search for jobs in the NHS"
And click on Accept Cookies button.
 
@positiveValidation @regression
Scenario Outline: Validate search functionality by providing various combinations
  When user performs search with "<jobTitle>", "<location>", "<distance>", "<jobReference>", "<employer>", "<payRange>"
  And user clicks on search button
  Then user should get list of jobs with matching preferences
  And the search message should contain "<searchResultMessage>"
  And user sorts results by "Date Posted (newest)"
  Then the job results should be sorted with newest Date Posted

  Examples:
  | jobTitle                                               | location    | distance | jobReference | employer         | payRange               | searchResultMessage                                                             |
  | Cardiac Physiologist                                   |             |          |              |                  |                        | jobs found for Cardiac Physiologist                                             |
  |                                                        | Manchester  | 10       |              |                  |                        | jobs found within 10 miles of Manchester                                        |
  |                                                        |             |          | 1316185502   |                  |                        | job found for 1316185502                                                        |
  |                                                        |             |          |              | NHS Resolution   |                        | jobs found for NHS Resolution                                                   |
  |                                                        |             |          |              |                  | £10,000 to £20,000     | jobs found                                                                      |
  | Staff Nurse                                            | Manchester  |          |              | Healthcare       |                        | jobs found for Staff Nurse within 5 miles of Manchester for Healthcare          |
  | Test Analyst                                           | Sheffield   | 30       | 1316185502   | Healthcare       | £10,000 to £20,000     | job found for 1316185502                                                        |
  |                                                        |             |          |              |                  |                        | jobs found                                                                      |
  
@positiveValidation 
 Scenario: Validate clearFilters button functionality
 When user performs search with "Test Analyst", "London", "10", "1316185502", "Healthcare", "£10,000 to £20,000"
 And  user clicks on clear filter button
 Then all fields should be blank
       
@negativeValidation
Scenario Outline: Validate Searchfunctionality by providing special characters in Job Title field
    When user performs search with "<jobTitle>", "<location>", "<distance>", "<jobReference>", "<employer>", "<payRange>"
    And  user clicks on search button
    Then the results page should display message "<searchResultMessage>"
Examples:
    | jobTitle   | location | distance | jobReference | employer | payRange | searchResultMessage             |
    | $%^£££     |          |          |              |          |          | No result found                 |
    |            | $£%%%    |      5   |     c09      |          |          | Location not found              |
    |            |          |          | %^Tc%$&%^    |          |          | No result found                 |
    |            |          |          |              | @@@BM    |          | No result found                 |
      

    