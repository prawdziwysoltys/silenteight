Feature: Working on branch

    Scenario: Adding batch to assigned
      Given I'm log-in as test user
#      When I create new decision tree "test_batch_assign"
      Then Decision Tree "test_batch_assign" is visible in main page
      When I click on decision tree "test_batch_assign"
      And I click Edit Assignment button
      Then Batch "BH_BTCH_AM" is in "available" section
      And I click "Add" button on batch "BH_BTCH_AM"
      Then Batch "BH_BTCH_AM" is in "assigned" section
      When I click Save Changes button in Edit Assignment window
      Then Batch "BH_BTCH_AM" is visible in Assigned list in Decision Tree Page

    Scenario: Removing batch from assigned
      When I click Edit Assignment button
      Then Batch "BH_BTCH_AM" is in "assigned" section
      And I click "Remove" button on batch "BH_BTCH_AM"
      Then Batch "BH_BTCH_AM" is in "available" section
      When I click Save Changes button in Edit Assignment window
      Then Batch "BH_BTCH_AM" is not visible in Assigned list in Decision Tree Page



