Feature: Searching computers
  I want to search computers which name contains a given keyword
  I want to navigate through result pages and sort result pages

  @searching
  Scenario Outline: Searching computers by company keyword
    Given I load the computer database homepage
    When I search computers with <company> keyword
    Then I should see a list of computer names all containing <company>
    And I should see <message> as counting message
    

    Examples: 
      | company  | message 						  |
      | "Apple"  | "13 computers found" |
      | "IBM"    | "25 computers found" |
      
  
  @searching
  Scenario: Searching one computer by its name
    Given I load the computer database homepage
    When I search computers with "Apple IIe" keyword
    Then I should see only one computer named "Apple IIe"
    And I should see "One computer found" as counting message 
    And I should see "Displaying 1 to 1 of 1" as pagination label
   
  @searching
  Scenario: Searching a none existing computer
    Given I load the computer database homepage
    When I search computers with "Apple XXX" keyword
    Then I should see "No computer" as counting message
    And I should see "Nothing to display" in the message panel
  
  
  @navigation
  Scenario: Navigating through next pages
    Given I load the computer database homepage
    When I search computers with "B" keyword
    Then I should be able to find the "Game Boy Color" computer using next link
   
  @sorting
  Scenario: Sorting computers list by name
    Given I load the computer database homepage
    Then I should see "ACE" as first computer in the list
    When I sort the current list by name
    Then I should see "lenovo thinkpad z61p" as first computer in the list
  	When I sort the current list by name
    Then I should see "ACE" as first computer in the list
    