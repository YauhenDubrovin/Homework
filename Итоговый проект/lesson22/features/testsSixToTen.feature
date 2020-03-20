@FeatureTwo
Feature: Mail.ru tests from six to ten

  @TestSix
  Scenario: Delete a sent mail
    Given I go to the sent folder
    When I choose the first mail
    When I click 'delete' button
    Then the mail is moved to the trash folder

  @TestSeven
  Scenario: Move a deleted mail to spam folder
    Given I go to the trash folder
    When I choose the first mail
    When I click 'spam' button
    Then the mail is moved to the spam folder

  @TestEight
  Scenario: Filter flagged mails in inbox folder
    Given I put the flag on the first mail
    When I press 'filter' button
    When I choose 'with flag' option
    Then there is only one mail in inbox folder

  @TestNine
  Scenario: Check unflagging in inbox folder
    Given I unflag the first mail in inbox folder
    When I press 'filter' button
    When I choose 'with flag' option
    Then there are no mails in inbox folder

  @TestTen
  Scenario: Check selection button in inbox folder
    Given I press 'select all' button
    When I click 'delete' button
    When I confirm deleting
    Then there are no mails in inbox folder