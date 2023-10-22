Feature: validate OrangeHRM APIs

  @Regression
  Scenario Outline: Add candidate and delete it
    Given  User login to OrangeHRM with "<username>" and "<password>"
    When Add candidate with "<firstname>" and "<lastname>" and "<email>"
    And delete the candidate
    Then candidate deleted success with status code 200
    Examples:
      | username | password | firstname | lastname | email             |
      | Admin    | admin123 | Ahmed     | gaber    | gaber@example.com |

  @Regression
  Scenario Outline: search candidate with job title
    Given  User login to OrangeHRM with "<username>" and "<password>"
    When  search for candidate with "<jobTitle>"
    Then results should contain the name of "<jobTitle>"
    Examples:
      | username | password | jobTitle |
      | Admin    | admin123 | QA Lead  |