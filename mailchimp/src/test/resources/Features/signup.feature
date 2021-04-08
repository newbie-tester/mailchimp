Feature: Sign-up function on mailchimp.com
  The user should be able to sign-up when valid credentials are provided, and see a message asking for email verification.
  If any of the credentials do not fulfill the requirements, error message should appear indicating what is wrong.

Scenario Outline: Create an account with valid or invalid credentials
  Given I navigate to the sign-up page
  And enter <email>, <username> and <password>
  When I click on the sign-up button
  Then a <message> appears showing the sign-up status

Examples: 
  | email         | username            | password   | message                                                                              |
  | "random"      | "random"            | "Abcd123!" | "Check your email"                                                                   |
  | "hej@hej.com" | "over100characters" | "Abcd123!" | "Enter a value less than 100 characters long"                                        |
  | "hej@hej.com" | "hejhej"            | "Abcd123!" | "Another user with this username already exists. Maybe it's your evil twin. Spooky." |
  | ""            | "hejhej"            | "Abcd123!" | "Please enter a value"                                                               |
