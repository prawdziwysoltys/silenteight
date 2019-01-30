Feature: Editing assigned batch list

    Scenario: Adding and removing batch from assigned list
      Given I'm log-in as test user
      When I create new decision tree "test_batch_assign"
      Then Decision Tree "test_batch_assign" is visible in main page
      When I click on decision tree "test_batch_assign"
      And I click Edit Assignment button
      Then I pick first batch from Available list
      Then Batch "picked" is in "available" section
      And I click "Add" button on batch "picked"
      Then Batch "picked" is in "assigned" section
      When I click Save Changes button in Edit Assignment window
      Then Batch "picked" is visible in Assigned list in Decision Tree Page
      When I open main page
      And I click on decision tree "test_batch_assign"
      When I click Edit Assignment button
      Then Batch "picked" is in "assigned" section
      And I click "Remove" button on batch "picked"
      Then Batch "picked" is in "available" section
      When I click Save Changes button in Edit Assignment window




