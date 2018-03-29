Feature: Create a user account
  Scenario: Create a new user account by valid input and the owner id is not registered
    Given I am connect to user database
    And input detail is that first name is "Yuan", last name is "Cui", email address is "ycu20@uclive.ac.nz", password is "12345" is valid
    And email address "ycu20@uclive.ac.nz" is not occupied
    When I submit the input information
    Then Next time I can retrieve information which first name is "Yuan", last name is "Cui", password is "12345" from user table by owner id "ycu20@uclive.ac.nz"

  Scenario: Try to create a new user account but forget to enter last name
    Given I am connect to user database
    And input detail is that first name is "Yuan", last name is "", email address is "ycu21@uclive.ac.nz", password is "12345"
    And email address "ycu21@uclive.ac.nz" is not occupied
    When I submit the input information
    Then Next time I cannot retrieve information from user table by owner id "ycu21@uclive.ac.nz"

  Scenario: Try to create a new user account but use an invalid email address
    Given I am connect to user database
    And input detail is that first name is "Yuan", last name is "Cui", email address is "uclive.ac.nz", password is "12345"
    And email address "uclive.ac.nz" is not occupied
    When I submit the input information
    Then Next time I cannot retrieve information from user table by owner id "uclive.ac.nz"

  Scenario: Try to create a new user account but using existing email address
    Given I am connect to user database
    And input detail is that first name is "Yuan", last name is "Cui", email address is "lebron.james@gmail.com", password is "12345"
    And email address "lebron.james@gmail.com" is occupied
    When I submit the input information
    Then Next time I cannot retrieve information which first name is "Yuan", last name is "Cui", password is "12345" from user table by owner id "lebron.james@gmail.com"