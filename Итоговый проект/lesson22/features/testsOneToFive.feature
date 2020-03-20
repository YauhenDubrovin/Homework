@FeatureOne
Feature: Mail.ru tests from one to five

  @TestOne
  Scenario: Send a mail
    Given I click 'compose a mail' button
    When I click 'to whom' button
    When I choose the first email address in the list
    When I click 'send' button
    When I confirm sending the empty mail
    Then the mail is sent

  @TestTwo
  Scenario: Move a mail from inbox to spam folder
    When I choose the first mail
    When I click 'spam' button
    Then the mail is moved to the spam folder

  @TestThree
  Scenario: Restore a mail from spam folder to inbox folder
    Given I go to the spam folder
    When I choose the first mail
    When I click 'not spam' button
    Then the mail is moved to the inbox folder

  @TestFour
  Scenario: Delete a mail from inbox folder
    When I choose the first mail
    When I click 'delete' button
    Then the mail is moved to the trash folder

  @TestFive
  Scenario: Restore a mail from trash folder to inbox folder
    Given I go to the trash folder
    When I choose the first mail
    When I click 'to folder' button
    When I choose the inbox folder from the list
    Then the mail is moved to the inbox folder