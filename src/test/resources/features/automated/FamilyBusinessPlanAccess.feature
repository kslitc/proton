Feature: Proton Mail - Having proton.me webpage as landing page, access the Proton mail Family and Business plans and
  verify their url and content

  Happy Path - Proton Mail Family and Business plans accessibility

  Objective: Verify Proton Mail Family and Business plans accessibility

  Precondition: Proton webpage must be available

  PostCondition: Url must be the expected one and it must contain, at least, Proton logo

  Scenario Outline: Navigate to Proton Mail Family and Business plans and verify url
    When user goes to "https://proton.me/"
    And user clicks on "Email"
    And user clicks on "Pricing"
    And user clicks on "<plan>"
    Then url is "<url>"
    And logo "<logo>" is visible
    And browser window closed

    Examples:
      | plan       | url                              | logo                |
      | Families   | https://proton.me/family         | Proton              |
      | Businesses | https://proton.me/business/plans | Proton For Business |