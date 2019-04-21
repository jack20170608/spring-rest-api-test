Feature: test table in steps

  Scenario: three of a kind beats two pair
    Given a hand with the following cards:
      | rank | suit |
      | 2     | H |
      | 2     | S |
      | 2     | C |
      | 4     | D |
      | A     | H |
    And another hand with the following cards:
      | rank | suit |
      | 2     | H |
      | 2     | S |
      | 4     | C |
      | 4     | D |
      | A     | H |
    Then the first hand should beat the second hand