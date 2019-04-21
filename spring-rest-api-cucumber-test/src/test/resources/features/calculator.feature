Feature: Calculator functionalists

  Scenario Outline: positive int calculate
    Given addend is <addend>
    Given adder  is <adder>
    When I ask what's the answer for add
    Then I should get the answer <answer> for add

    Examples:
      | addend  | adder | answer  |
      | 1       | 2     | 3       |
      | 100     | 200   | 300     |
      | 1999900 | 100   | 2000000 |