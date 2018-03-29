$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("./features/user.feature");
formatter.feature({
  "line": 1,
  "name": "Create a user account",
  "description": "",
  "id": "create-a-user-account",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 2,
  "name": "Create a new user account by valid input and the owner id is not registered",
  "description": "",
  "id": "create-a-user-account;create-a-new-user-account-by-valid-input-and-the-owner-id-is-not-registered",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 3,
  "name": "I am connect to user database",
  "keyword": "Given "
});
formatter.step({
  "line": 4,
  "name": "input detail is that first name is \"Yuan\", last name is \"Cui\", email address is \"ycu20@uclive.ac.nz\", password is \"12345\" is valid",
  "keyword": "And "
});
formatter.step({
  "line": 5,
  "name": "email address \"ycu20@uclive.ac.nz\" is not occupied",
  "keyword": "And "
});
formatter.step({
  "line": 6,
  "name": "I submit the input information",
  "keyword": "When "
});
formatter.step({
  "line": 7,
  "name": "Next time I can retrieve information which first name is \"Yuan\", last name is \"Cui\", password is \"12345\" from user table by owner id \"ycu20@uclive.ac.nz\"",
  "keyword": "Then "
});
formatter.match({
  "location": "TestSteps.connectDatabase()"
});
formatter.result({
  "duration": 728322320,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 36
    },
    {
      "val": "Cui",
      "offset": 57
    },
    {
      "val": "ycu20@uclive.ac.nz",
      "offset": 81
    },
    {
      "val": "12345",
      "offset": 115
    }
  ],
  "location": "TestSteps.inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIsIsValid(String,String,String,String)"
});
formatter.result({
  "duration": 20939338,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ycu20@uclive.ac.nz",
      "offset": 15
    }
  ],
  "location": "TestSteps.emailAddressIsNotOccupied(String)"
});
formatter.result({
  "duration": 7896422,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.iSubmitTheInputInformation()"
});
formatter.result({
  "duration": 445854598,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 58
    },
    {
      "val": "Cui",
      "offset": 79
    },
    {
      "val": "12345",
      "offset": 98
    },
    {
      "val": "ycu20@uclive.ac.nz",
      "offset": 134
    }
  ],
  "location": "TestSteps.nextTimeICanRetrieveInformationWhichFirstNameIsLastNameIsPasswordIsFromUserTableByOwnerId(String,String,String,String)"
});
formatter.result({
  "duration": 4257220,
  "status": "passed"
});
formatter.scenario({
  "line": 9,
  "name": "Try to create a new user account but forget to enter last name",
  "description": "",
  "id": "create-a-user-account;try-to-create-a-new-user-account-but-forget-to-enter-last-name",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I am connect to user database",
  "keyword": "Given "
});
formatter.step({
  "line": 11,
  "name": "input detail is that first name is \"Yuan\", last name is \"\", email address is \"ycu21@uclive.ac.nz\", password is \"12345\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "email address \"ycu21@uclive.ac.nz\" is not occupied",
  "keyword": "And "
});
formatter.step({
  "line": 13,
  "name": "I submit the input information",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "Next time I cannot retrieve information from user table by owner id \"ycu21@uclive.ac.nz\"",
  "keyword": "Then "
});
formatter.match({
  "location": "TestSteps.connectDatabase()"
});
formatter.result({
  "duration": 1090148,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 36
    },
    {
      "val": "",
      "offset": 57
    },
    {
      "val": "ycu21@uclive.ac.nz",
      "offset": 78
    },
    {
      "val": "12345",
      "offset": 112
    }
  ],
  "location": "TestSteps.inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIs(String,String,String,String)"
});
formatter.result({
  "duration": 416778,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ycu21@uclive.ac.nz",
      "offset": 15
    }
  ],
  "location": "TestSteps.emailAddressIsNotOccupied(String)"
});
formatter.result({
  "duration": 596759,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.iSubmitTheInputInformation()"
});
formatter.result({
  "duration": 50952,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "ycu21@uclive.ac.nz",
      "offset": 69
    }
  ],
  "location": "TestSteps.nextTimeICannotRetrieveInformationFromUserTableByOwnerId(String)"
});
formatter.result({
  "duration": 762810,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Try to create a new user account but use an invalid email address",
  "description": "",
  "id": "create-a-user-account;try-to-create-a-new-user-account-but-use-an-invalid-email-address",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 17,
  "name": "I am connect to user database",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "input detail is that first name is \"Yuan\", last name is \"Cui\", email address is \"uclive.ac.nz\", password is \"12345\"",
  "keyword": "And "
});
formatter.step({
  "line": 19,
  "name": "email address \"uclive.ac.nz\" is not occupied",
  "keyword": "And "
});
formatter.step({
  "line": 20,
  "name": "I submit the input information",
  "keyword": "When "
});
formatter.step({
  "line": 21,
  "name": "Next time I cannot retrieve information from user table by owner id \"uclive.ac.nz\"",
  "keyword": "Then "
});
formatter.match({
  "location": "TestSteps.connectDatabase()"
});
formatter.result({
  "duration": 516482,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 36
    },
    {
      "val": "Cui",
      "offset": 57
    },
    {
      "val": "uclive.ac.nz",
      "offset": 81
    },
    {
      "val": "12345",
      "offset": 109
    }
  ],
  "location": "TestSteps.inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIs(String,String,String,String)"
});
formatter.result({
  "duration": 372425,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "uclive.ac.nz",
      "offset": 15
    }
  ],
  "location": "TestSteps.emailAddressIsNotOccupied(String)"
});
formatter.result({
  "duration": 605189,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.iSubmitTheInputInformation()"
});
formatter.result({
  "duration": 78810,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "uclive.ac.nz",
      "offset": 69
    }
  ],
  "location": "TestSteps.nextTimeICannotRetrieveInformationFromUserTableByOwnerId(String)"
});
formatter.result({
  "duration": 1934333,
  "status": "passed"
});
formatter.scenario({
  "line": 23,
  "name": "Try to create a new user account but using existing email address",
  "description": "",
  "id": "create-a-user-account;try-to-create-a-new-user-account-but-using-existing-email-address",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 24,
  "name": "I am connect to user database",
  "keyword": "Given "
});
formatter.step({
  "line": 25,
  "name": "input detail is that first name is \"Yuan\", last name is \"Cui\", email address is \"lebron.james@gmail.com\", password is \"12345\"",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "email address \"lebron.james@gmail.com\" is occupied",
  "keyword": "And "
});
formatter.step({
  "line": 27,
  "name": "I submit the input information",
  "keyword": "When "
});
formatter.step({
  "line": 28,
  "name": "Next time I cannot retrieve information which first name is \"Yuan\", last name is \"Cui\", password is \"12345\" from user table by owner id \"lebron.james@gmail.com\"",
  "keyword": "Then "
});
formatter.match({
  "location": "TestSteps.connectDatabase()"
});
formatter.result({
  "duration": 578064,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 36
    },
    {
      "val": "Cui",
      "offset": 57
    },
    {
      "val": "lebron.james@gmail.com",
      "offset": 81
    },
    {
      "val": "12345",
      "offset": 119
    }
  ],
  "location": "TestSteps.inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIs(String,String,String,String)"
});
formatter.result({
  "duration": 290315,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "lebron.james@gmail.com",
      "offset": 15
    }
  ],
  "location": "TestSteps.emailAddressIsOccupied(String)"
});
formatter.result({
  "duration": 849685,
  "status": "passed"
});
formatter.match({
  "location": "TestSteps.iSubmitTheInputInformation()"
});
formatter.result({
  "duration": 66347,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Yuan",
      "offset": 61
    },
    {
      "val": "Cui",
      "offset": 82
    },
    {
      "val": "12345",
      "offset": 101
    },
    {
      "val": "lebron.james@gmail.com",
      "offset": 137
    }
  ],
  "location": "TestSteps.nextTimeICannotRetrieveInformationWhichFirstNameIsLastNameIsPasswordIsFromUserTableByOwnerId(String,String,String,String)"
});
formatter.result({
  "duration": 514649,
  "status": "passed"
});
});