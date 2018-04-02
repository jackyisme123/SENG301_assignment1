Project Structure

|--- assignment
|    |--- features
|    |    |--- user.feature
|    |    └--- vehicle.feature
|    |--- src
|    |    |--- main
|    |    |    └--- java
|    |    |    |    |--- dao
|    |    |    |    |    |--- UserDao.java
|    |    |    |    |    └--- VehicleDao.java
|    |    |    |    └--- entity
|    |    |    |    |    |--- User.java
|    |    |    |    |    └--- Vehicle.java
|    |    |    └--- resources
|    |    └--- test
|    |    |    └--- java
|    |    |    |    |--- dao
|    |    |    |    |    |--- TestUserDao.java
|    |    |    |    |    └--- TestVehicleDao.java
|    |    |    |    |--- steps
|    |    |    |    |    |--- TestSteps.java
|    |    |    |    |--- UserCucumberTest.java
|    |    |    |    └--- VehicleCucumberTest.java
|    |--- target
|    |--- assignment.sqlite
|    |--- database.sql
|    |--- pom.xml
|    └--- sqlite-jdbc-3.20.0.1.jar


Brief Introduction

features package: store two features
src package: for developing.
src/main/java/dao store two jdbc classes
src/main/java/entity store two entities
src/test/java/dao store two unit test classes for two jdbc classes
src/test/java/steps store functions for cucumber test
src/test/java/UserCucumberTest.java cucumber test for user 
src/test/java/VehicleCucumberTest.java cucumber test for vehicle
assignment.sqlite sqlite database for the assignment
database.sql create two tables for assignment in database


How to Start

- Prepare database
run command "sqlite3 .open /path/to/assignment/folder/assignment.sqlite" in terminal

- Create or reset tables
run command ".read /path/to/assignment/folder/database.sql" in sqlite command line

- Run program
Use an IDE will help to be more efficient. Intellij IDEA and Eclipse are recommended.
In intellij, right clicking java file will help to compile and execute file automatically.
-- UserDao.java and VehicleDao.java are the main developing program
-- TestUserDao.java and TestVehicleDao.java are the unit test files for functions in UserDao.java and VehicleDao.java.
   Every function can be test separately by click the green arrow on the left side of function declaration, or running the test file for testing all of functions together.
-- UserCucumberTest.java and VehicleCucumberTest.java are two test files for integration test.
