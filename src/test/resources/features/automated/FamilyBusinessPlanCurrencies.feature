Feature: Proton Mail - Having proton.me webpage as landing page, access the Proton mail Family and Business plans,
  modify the currency and verify their prices

  Happy Path - Proton Mail Family and Business plans currency prices

  Objective: Verify Proton Mail Family and Business plans currency prices

  Precondition: Proton webpage must be available

  PostCondition: Plan prices are in the expected selected currency

  Scenario Outline: Navigate to Proton Mail Family and Business plans, modify currency and verify prices
    When user goes to "https://proton.me/"
    And user clicks on "Email"
    And user clicks on "Pricing"
    And user clicks on "<plan>"
    And user selects "<currency>" "currency"
    Then plans are in "<symbol>" for "<plan>"
    And browser window closed

    Examples:
      | currency | symbol | plan       |
      | USD      | $      | Businesses |
      | EUR      | €      | Businesses |
      | CHF      | CHF    | Businesses |
      | USD      | $      | Families   |
      | EUR      | €      | Families   |
      | CHF      | CHF    | Families   |