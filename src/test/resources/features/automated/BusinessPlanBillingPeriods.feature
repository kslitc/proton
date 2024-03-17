Feature: Proton Mail - Having proton.me webpage as landing page, access the Proton mail Business plan and verify its
  billing periods

  Happy Path - Proton Mail Business plan billing periods

  Objective: Verify Proton Mail Business plan billing periods

  Precondition: Proton webpage must be available

  PostCondition: Billing plan prices are different from the default ones after selecting another billing period

  Scenario Outline:
    When user goes to "https://proton.me/"
    And user clicks on "Email"
    And user clicks on "Pricing"
    And user clicks on "Businesses"
    And user writes down default plan prices
    And user selects "<period>" "period"
    Then plan prices are different
    And browser window closed

    Examples:
      | period    |
      | 1 month   |
      | 12 months |
      | 24 months |