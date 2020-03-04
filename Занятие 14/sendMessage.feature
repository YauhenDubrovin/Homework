Feature: Test sending a message

  Scenario: Log in mail.ru and send a new empty message
    Given I am on the main page
    When I enter a user name
    When I enter a password and get to the inbox folder
    When I click "compose a mail" button
    When I click "to whom" button
    When I choose the first email address in the list
    When I click "send" button
    When I click "confirm sending the empty mail"
    Then The mail is sent

