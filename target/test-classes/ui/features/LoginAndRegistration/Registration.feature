@RegistrationWithMockData
Feature: Digital Bank Registration Page with mocked email and ssn

  Background:
    Given The user with "jkbts@gmail.com" is not in DB
    Given User  navigates to Digital Bank signup page

  Scenario: Positive Case. As a user, I want to successfully create Digital Bank account
    When User creates account with following field with mocked email and ssn
    |title|firstName|lastName|gender|dob       |ssn     |email  |password  |address     |locality|region|postalCode|country|homePhone |mobilePhone|workPhone |termsCheckMark|
    |Mrs. |Emma     |Hock    |F     |12/08/2000|random  |random |Tester123 |28 Main st  |City    |CA    |99921     |US     |2146591008|2136591208 |1126593008|true          |
    Then user should be displayed with the message "Success Registration Successful. Please Login."




  @NegativeRegistrationCases
  Scenario Outline: Negative Test Cases. As a Digital Bank Admin I want to make sure users can not register without providing all valid data
    When User creates account with following field with mocked email and ssn
      |title  |firstName  |lastName  |gender  |dob  |ssn   |email  |password  |address  |locality  |region  |postalCode  |country  |homePhone  |mobilePhone  |workPhone  |termsCheckMark  |
      |<title>|<firstName>|<lastName>|<gender>|<dob>|<ssn> |<email>|<password>|<address>|<locality>|<region>|<postalCode>|<country>|<homePhone>|<mobilePhone>|<workPhone>|<termsCheckMark>|
    Then User should see the "<fieldWithError>" required field error message "<errorMessage>"


    Examples:
      |title|firstName|lastName|gender|dob|ssn|email |password  |address     |locality|region|postalCode|country|homePhone |mobilePhone|workPhone |termsCheckMark|fieldWithError|errorMessage                      |
      |     |         |        |      |   |   |      |          |            |        |      |          |       |          |           |          |              |  title       |Please select an item in the list.|
      |Mr.  |         |        |      |   |   |      |          |            |        |      |          |       |          |           |          |              |   firstName  |Please fill out this field.       |
      |Mr.  |  Jack   |        |      |   |   |      |          |            |        |      |          |       |          |           |          |              |   lastName   |Please fill out this field.       |
      |Mr.  |  Jack   |  Jack  |      |   |   |      |          |            |        |      |          |       |          |           |          |              |   gender     |Please select one of these options.|

  @Registration
  Scenario: Positive Case.Automate Registration Steps by Deleting Previously Created User
    When User creates account with following field
      |title|firstName|lastName|gender|dob       |ssn          |email           |password  |address     |locality|region|postalCode|country|homePhone |mobilePhone|workPhone |termsCheckMark|
      |Mr.  |Jungkook |Hock    |M     |12/08/2000|123-42-2230  |jkbts@gmail.com |Tester123 |28 Main st  |City    |CA    |99921     |US     |2146591008|2136591208 |1126593008|true          |
    Then user should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      |title|firstName|lastName|gender|dob       |ssn          |email           |password  |address     |locality|region|postalCode|country|homePhone |mobilePhone|workPhone |accountNonExpired|accountNonLocked|credentialsNonExpired|enabled|
      |Mr.  |Jungkook |Hock    |M     |12/08/2000|123-42-2230  |jkbts@gmail.com |Tester123 |28 Main st  |City    |CA    |99921     |US     |2146591008|2136591208 |1126593008|true             |true            |true                 |true   |




    