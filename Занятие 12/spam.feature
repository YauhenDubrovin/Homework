Feature: Test spam functionality

  @FirstTest
  Scenario: Moving a mail to spam folder
    Given I am in inbox folder
    When I open a mail and press spam button
    Then Mail is moved to spam folder

  @SecondTest
  Scenario: Moving a mail from spam folder
    Given I am in spam folder
    When I open a mail and press not-spam button
    Then Mail is moved from spam folder