Feature: Sign-up function on mailchimp.com
  The user should be able to sign-up when valid credentials are provided.
  If any of the credentials do not fulfill the requirements, error messages should appear indicating what is wrong.

Scenario: Sign-up with valid credentials
	Given I navigate to the sign-up page
	And enter email address, username and password
	When I click on the sign-up button
	Then I am registered as a user
  
Scenario: Sign-up with existing username
	Given I navigate to the sign-up page
	And enter email address, username and password
	When I click on the sign-up button
	Then I get an error message

Scenario: Sign-up without email
	Given I navigate to the sign-up page
	And enter email address, username and password
	When I click on the sign-up button
	Then I get an error message
  
Scenario: Sign-up with long username
	Given I navigate to the sign-up page
	And enter email address, username and password
	When I click on the sign-up button
	Then I get an error message

 #   Examples: 
 #     | name  | value | status  |
 #     | name1 |     5 | success |
 #     | name2 |     7 | Fail    |
