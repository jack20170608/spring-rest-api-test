Feature: order end-point

  Background:
    * url demoBaseUrl
    * configure logPrettyRequest = true
    * configure logPrettyResponse = true

  Scenario: create and retrieve a order

# create a new order
    Given path 'order'
    And request {orderDetail : 'This is the order detail' }
    When method post
    Then status 200
    And match response == '#notnull'

    * def id = response

# get by id
    Given path 'order', id
    * configure retry = { count: 20, interval: 1000 }
    And retry until responseStatus == 200 && response.id == id
    When method get
    And match response == { id: '#ignore', orderDetail: 'This is the order detail'}
