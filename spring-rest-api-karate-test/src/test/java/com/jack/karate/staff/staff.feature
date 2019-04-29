Feature: cats end-point

  Background:
    * url demoBaseUrl
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

  Scenario: create and retrieve a staff
    * def salary = new java.math.BigDecimal(123.456)
    * print 'the value of salary is:', salary

# create a new staff
    Given path 'staff'
    And request { name: 'jack', salary : '#(salary)'}
    When method post
    Then status 200
    And match response == '#notnull'

    * def id = response

# get by id
    Given path 'staff', id
    * configure retry = { count: 5, interval: 2000 }
    And retry until responseStatus == 200 && response.id == id
    When method get
    And match response == { id: '#ignore' ,name: 'jack', salary : '#(salary)'}
