Feature: basic show the karate command

  Scenario: def variable basic
    Given def myVar = 'world'
    Then print myVar

    * def myNum = 5
    * def color = 'red '
    Then assert color + myNum == 'red 5'

    * def myJson = {foo: 'bar', baz: [1,2,3]}
    * print 'the value of myJson is:', myJson
    * print 'the pretty json of myJson is"', karate.pretty(myJson)
    #* print 'the pretty xml of myJson is"', karate.(myJson)

    * def cat = { name: 'Billie', scores: [2,5] }
    * assert cat.scores[1] == 5

    * def specialCat = {name : 'Jammy', scores : [3,8], 'content-type':'json'}
    * assert specialCat['content-type'] == 'json'

    * def cats = [{name:'tom'}, {name:'bob'}]
    * def firstCat = cats[0]
    * match firstCat == {name:'tom'}


  Scenario: xml format
    * def xmlCat = <cat><name>Billie</name><scores><score>2</score><score>5</score></scores></cat>
#    Then match xmlCat/cat/scores/score[2] == '5'
    # but karate allows you to traverse xml like json !!
    Then match xmlCat.cat.scores.score[1] == '5'

    Given def user = {name: 'john', age: 21 }
    And def lang = 'en'
#  if a string value within a JSON (or XML) object declaration is enclosed between #( and ) - it will be evaluated as a JavaScript expression
    When def session = {name: '#(user.name)', locale: '#(lang)', sessionUser:'#(user)'}

    Given def xmlUser = <user><name>john</name></user>
    When def xmlSession = <session><locale>#(lang)</locale><sessionUser>#(user)</sessionUser></session>
    Then match xmlSession.session.sessionUser.name == 'john'
#They work only within JSON, XML or when the Right Hand Side of the Karate expression is a “quoted string”

    * def name = 'jack'
    # wrong !
#    * def foo1 = 'hello #(name)'
    * def foo2 = 'hello' + name
    # right !
    * def foo3 = '#("hello " + name)'
    * print foo1
    * print foo2
    * print foo3

  Scenario: Enclosed javascript
    When def user = { name: 'john', age: 21 }
    And def lang = 'en'

    * def embedded = { name: '#(user.name)', locale: '#(lang)', sessionUser: '#(user)' }
    * def enclosed = ({ name: user.name, locale: lang, sessionUser: user })
    * match embedded == enclosed

  Scenario:  multi-line expressions
    # instead of:
    * def cat = <cat><name>Billie</name><scores><score>2</score><score>5</score></scores></cat>
    # this is more readable:
    * def cat =
    * def cat =
  """
  <cat>
      <name>Billie</name>
      <scores>
          <score>2</score>
          <score>5</score>
      </scores>
  </cat>
  """
    # example of a request payload in-line
    Given request
  """
  <?xml version='1.0' encoding='UTF-8'?>
  <S:Envelope xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
  <S:Body>
  <ns2:QueryUsageBalance xmlns:ns2="http://www.mycompany.com/usage/V1">
      <ns2:UsageBalance>
          <ns2:LicenseId>12341234</ns2:LicenseId>
      </ns2:UsageBalance>
  </ns2:QueryUsageBalance>
  </S:Body>
  </S:Envelope>
  """

    # example of a payload assertion in-line
#    Then match response ==
#  """
#  { id: { domain: "DOM", type: "entityId", value: "#ignore" },
#    created: { on: "#ignore" },
#    lastUpdated: { on: "#ignore" },
#    entityState: "ACTIVE"
#  }
#  """


#  Notice that in the above example, string values within the table need to be enclosed in quotes. Otherwise they would be evaluated as expressions
  Scenario: Simple way to create JSON Arrays
    * table cats
      | name   | age |
      | 'bob'  | 2   |
      | 'Wild' | 4   |
      | 'Nyan' | 3   |
    * match cats == [{name: 'bob', age: 2}, {name: 'Wild', age: 4}, {name: 'Nyan', age: 3}]

    * def one = 'hello'
    * def two = {baz : 'world'}
    * table foo
      | foo     | bar           |
      | one     | {baz: 1}      |
      | two.baz | ['baz','ban'] |
    * match foo == [{foo:'hello', bar: {baz:1}}, {foo: 'world', bar: ['baz', 'ban']} ]


    * def one = { baz: null }
    * table json
      | foo     | bar    |
      | 'hello' |        |
      | one.baz | (null) |
      | 'world' | null   |
    * match json == [{ foo: 'hello' }, { bar: null }, { foo: 'world' }]


    * set search
      | path       | 0        | 1      | 2       |
      | name.first | 'John'   | 'Jane' |         |
      | name.last  | 'Smith'  | 'Doe'  | 'Waldo' |
      | age        | 20       |        |         |

    * match search[0] == { name: { first: 'John', last: 'Smith' }, age: 20 }
    * match search[1] == { name: { first: 'Jane', last: 'Doe' } }
    * match search[2] == { name: { last: 'Waldo' } }


  Scenario: Don’t parse, treat as raw text
#    Scenario Outline:
#        # note the 'text' keyword instead of 'def'
#      * text query =
#    """
#    {
#      hero(name: "<name>") {
#        height
#        mass
#      }
#    }
#    """
#      Examples:
#        | name  |
#        | John  |
#        | Smith |
    * def text = 'hello <foo> world'
    * replace text.foo = 'bar'
      * match text == 'hello bar world'

    * def text = 'hello <one> world <two> bye'
    * replace text
      | token | value   |
      | one   | 'cruel' |
      | two   | 'good'  |
    * match text == 'hello cruel world good bye'


  Scenario: type transfer
    * def big = 123123123123
    * string json = {num : '#(big)'}
    * match json == {"num": 1.23123123123E11}

    * def big = new java.math.BigDecimal(123123123123)
    * string json = { num: '#(big)' }
    * match json == {"num":123123123123}