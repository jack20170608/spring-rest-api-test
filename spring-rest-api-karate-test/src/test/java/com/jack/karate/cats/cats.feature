Feature: cats end-point

  Background:
    * url demoBaseUrl
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

  Scenario: create and retrieve a cat

# create a new cat
    Given path 'cats'
    And request { name: 'Billie' }
    When method post
    Then status 200
    And match response == { id: '#number', name: 'Billie' ,kittens: null}

    * def id = response.id

# get by id
    Given path 'cats', id
    When method get
    Then status 200
    And match response == { id: '#(id)', name: 'Billie', kittens:null }

# get all cats
    Given path 'cats'
    When method get
    Then status 200
    And match response contains { id: '#(id)', name: 'Billie', kittens:null}

# get cat but ask for xml
    Given path 'cats', id
    And header Accept = 'application/xml'
    When method get
    Then status 200
    And match response == <Cat><id>#(id)</id><name>Billie</name></Cat>
