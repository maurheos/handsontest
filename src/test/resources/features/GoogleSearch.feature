Feature: Google Homepage Search
  In order to test Google Homepage Search
  As Google user
  I want to specify the search conditions
  So I can find the information related

  @automated @web @google
  Scenario Outline: User can search with “Google Search”
    Given I'm on the homepage
    When I type "<text_to_search>" into the search field
    And I click the Google Search button
    Then I go to the search results page
    And the first result is "<first_result>"
    When I click on the first result link
    Then I go to the "<expected_title>" page

    Examples:
      | text_to_search                                            | first_result                            | expected_title               |
      | The name of the wind                                      | The Name of the Wind - Patrick Rothfuss | Patrick Rothfuss - The Books |
      | The name of the wind site:https://www.patrickrothfuss.com | The Books - Patrick Rothfuss            | Patrick Rothfuss - The Books |

  @automated @web @google
  Scenario Outline: User can search by using the suggestions
    Given I'm on the homepage
    When I type "<text_to_search>" into the search field
    And the suggestions list is displayed
    And I click on the first suggestion in the list
    Then I go to the search results page
    And the first result is "<first_result>"
    When I click on the first result link
    Then I go to the "<expected_title>" page

    Examples:
      | text_to_search    | first_result                                            | expected_title                                          |
      | The name of the w | The Name of the Wind - Patrick Rothfuss                 | Patrick Rothfuss - The Books                            |
      | The name of the w | El nombre del viento - Wikipedia, la enciclopedia libre | El nombre del viento - Wikipedia, la enciclopedia libre |
      | The name of the w | The Name of the Wind - Wikipedia                        | The Name of the Wind - Wikipedia                        |