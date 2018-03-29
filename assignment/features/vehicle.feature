Feature: register a new vehicle
  Scenario: register a new vehicle by valid input and plate number is not occupied
    Given I am connect to user database
    And Input owner_id "kyre.irving@gmail.com", type "MC", plate_number "KI1111", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is valid
    And Plate number "KI1111" is not occupied
    When I submit vehicle registration
    Then next time I can retrieve registration which type is "MC", make is "Toyota", model is "LandCruiser", fuel is "diesel", manufacture date is "11/11/2016" from vehicle table by plate number is "KI1111" and owner id is "kyre.irving@gmail.com"

  Scenario: register a new vehicle but forget to enter type
    Given I am connect to user database
    And Input owner_id "kyre.irving@gmail.com", type "", plate_number "KI2222", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is not valid
    And Plate number "KI2222" is not occupied
    When I submit vehicle registration
    Then next time I cannot retrieve registration from plate number "KI2222" and owner id is "kyre.irving@gmail.com"

  Scenario: register a new vehicle but use an invalid vehicle type
    Given I am connect to user database
    And Input owner_id "kyre.irving@gmail.com", type "G", plate_number "KI2222", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is not valid
    And Plate number "KI2222" is not occupied
    When I submit vehicle registration
    Then next time I cannot retrieve registration from plate number "KI2222" and owner id is "kyre.irving@gmail.com"

  Scenario: register a new vehicle but use an invalid fuel type
    Given I am connect to user database
    And Input owner_id "kyre.irving@gmail.com", type "MC", plate_number "KI2222", make "Toyota", model "LandCruiser", fuel "SUNSHINE", manufacture date "11/11/2016" is not valid
    And Plate number "KI2222" is not occupied
    When I submit vehicle registration
    Then next time I cannot retrieve registration from plate number "KI2222" and owner id is "kyre.irving@gmail.com"

  Scenario: register a new vehicle by valid input but plate number is occupied
    Given I am connect to user database
    And Input owner_id "kyre.irving@gmail.com", type "MC", plate_number "JH1313", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is valid
    And Plate number "JH1313" is occupied
    When I submit vehicle registration
    Then next time I cannot retrieve registration from plate number "JH1313" and owner id is "kyre.irving@gmail.com"

  Scenario: update a vehicle registration by valid input and registration is existed
    Given I am connect to user database
    And Input owner_id "harden.james@gmail.com", type "MC", plate_number "JH1313", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is valid
    And Registration is existed with owner id "harden.james@gmail.com" and plate number "JH1313"
    When I update vehicle registration
    Then next time I can retrieve registration which type is "MC", make is "Toyota", model is "LandCruiser", fuel is "diesel", manufacture date is "11/11/2016" from vehicle table by plate number is "JH1313" and owner id is "harden.james@gmail.com"

  Scenario: update a vehicle registration by existing registration but forget to enter type
    Given I am connect to user database
    And Input owner_id "chris.paul@gmail.com", type "", plate_number "TRA111", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is not valid
    And Registration is existed with owner id "chris.paul@gmail.com" and plate number "TRA111"
    When I update vehicle registration
    Then next time I cannot retrieve registration which type is "", make is "Toyota", model is "LandCruiser", fuel is "diesel", manufacture date is "11/11/2016" from vehicle table by plate number is "TRA111" and owner id is "chris.paul@gmail.com"

  Scenario: update a vehicle registration by existing registration but use a invalid vehicle type
    Given I am connect to user database
    And Input owner_id "chris.paul@gmail.com", type "G", plate_number "TRA111", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is not valid
    And Registration is existed with owner id "chris.paul@gmail.com" and plate number "TRA111"
    When I update vehicle registration
    Then next time I cannot retrieve registration which type is "G", make is "Toyota", model is "LandCruiser", fuel is "diesel", manufacture date is "11/11/2016" from vehicle table by plate number is "TRA111" and owner id is "chris.paul@gmail.com"

  Scenario: update a vehicle registration by existing registration but use a invalid fuel type
    Given I am connect to user database
    And Input owner_id "chris.paul@gmail.com", type "MC", plate_number "TRA111", make "Toyota", model "LandCruiser", fuel "SUNSHINE", manufacture date "11/11/2016" is not valid
    And Registration is existed with owner id "chris.paul@gmail.com" and plate number "TRA111"
    When I update vehicle registration
    Then next time I cannot retrieve registration which type is "MC", make is "Toyota", model is "LandCruiser", fuel is "SUNSHINE", manufacture date is "11/11/2016" from vehicle table by plate number is "TRA111" and owner id is "chris.paul@gmail.com"

  Scenario: update a vehicle registration by valid input but not existing registration
    Given I am connect to user database
    And Input owner_id "chris.paul@gmail.com", type "MC", plate_number "TRA111", make "Toyota", model "LandCruiser", fuel "diesel", manufacture date "11/11/2016" is valid
    And Registration is not existed with owner id "chris.paul@gmail.com" and plate number "TRA222"
    When I update vehicle registration
    Then next time I cannot retrieve registration which type is "MC", make is "Toyota", model is "LandCruiser", fuel is "diesel", manufacture date is "11/11/2016" from vehicle table by plate number is "TRA111" and owner id is "chris.paul@gmail.com"

  Scenario: remove a vehicle registration by user id and plate number
    Given I am connect to user database
    Given four registrations can be retrieve from vehicle table
    And Registration is not existed with owner id "chris.paul@gmail.com" and plate number "TRA222"
    When I remove a vehicle registration with owner id "chris.paul@gmail.com" and plate number "TRA222"
    Then four registrations without any changes

  Scenario: remove a vehicle registration by user id and plate number
    Given I am connect to user database
    And Registration is existed with owner id "chris.paul@gmail.com" and plate number "TRA111"
    When I remove a vehicle registration with owner id "chris.paul@gmail.com" and plate number "TRA111"
    Then next time I cannot retrieve registration from plate number "TRA111" and owner id is "chris.paul@gmail.com"