package steps;

import dao.UserDao;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dao.VehicleDao;
import entity.User;
import entity.Vehicle;
import org.junit.Assert;

import static org.junit.Assert.*;

public class TestSteps {
    private UserDao userDao = null;
    private VehicleDao vehicleDao = null;
    private Connection connection = null;
    private User user = null;
    private boolean checkOwnerIdResult;
    private  boolean insertUserInfoResult;
    private Vehicle vehicle = null;
    private boolean checkPlateNumberResult;
    private boolean insertVehicleResult;
    boolean checkOwnerExistedResult;
    boolean updateVehicleRegistrationResult;
    boolean removeVehicleRegistrationResult;
    ResultSet registrationsBefore;
    ResultSet registrationsAfter;


    @Given("^I am connect to user database$")
    public void connectDatabase() throws SQLException {
        String url = "jdbc:sqlite:./assignment.sqlite";
        connection = DriverManager.getConnection(url);
    }

    @Given("^input detail is that first name is \"([^\"]*)\", last name is \"([^\"]*)\", email address is \"([^\"]*)\", password is \"([^\"]*)\" is valid$")
    public void inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIsIsValid(String firstName, String lastName, String email, String password) throws Throwable {
        user = userDao.checkSubmission(firstName, lastName, email, password);
        assertNotNull(user);
    }

    @Given("^email address \"([^\"]*)\" is not occupied$")
    public void emailAddressIsNotOccupied(String email) throws Throwable {
        ResultSet resultSet = userDao.fetchOwnerIds(connection);
        checkOwnerIdResult = userDao.checkOwnerId(resultSet, email);
        assertTrue(checkOwnerIdResult);
    }

    @When("^I submit the input information$")
    public void iSubmitTheInputInformation() throws Throwable {
        insertUserInfoResult = userDao.insertUserInfo(user, connection, checkOwnerIdResult);
    }

    @Then("^Next time I can retrieve information which first name is \"([^\"]*)\", last name is \"([^\"]*)\", password is \"([^\"]*)\" from user table by owner id \"([^\"]*)\"$")
    public void nextTimeICanRetrieveInformationWhichFirstNameIsLastNameIsPasswordIsFromUserTableByOwnerId(String firstName, String lastName, String password, String ownerId) throws Throwable {
        String getFirstName = "";
        String getLastName = "";
        String getPasswrod = "";
        ResultSet resultSet = userDao.retrieveInfoByOwnerId(ownerId, connection);
        while(resultSet.next()){
            getFirstName = resultSet.getString("first_name");
            getLastName = resultSet.getString("last_name");
            getPasswrod = resultSet.getString("password");
        }
        assertEquals(getFirstName, firstName);
        assertEquals(getLastName, lastName);
        assertEquals(getPasswrod, password);
        connection.close();
    }

    @Given("^input detail is that first name is \"([^\"]*)\", last name is \"([^\"]*)\", email address is \"([^\"]*)\", password is \"([^\"]*)\"$")
    public void inputDetailIsThatFirstNameIsLastNameIsEmailAddressIsPasswordIs(String firstName, String lastName, String email, String password) throws Throwable {
        user = userDao.checkSubmission(firstName, lastName, email, password);
    }

    @Then("^Next time I cannot retrieve information from user table by owner id \"([^\"]*)\"$")
    public void nextTimeICannotRetrieveInformationFromUserTableByOwnerId(String ownerId) throws Throwable {
        ResultSet resultSet = userDao.retrieveInfoByOwnerId(ownerId, connection);
        assertFalse(resultSet.next());
        connection.close();
    }

    @Given("^email address \"([^\"]*)\" is occupied$")
    public void emailAddressIsOccupied(String email) throws Throwable {
        ResultSet resultSet = userDao.fetchOwnerIds(connection);
        checkOwnerIdResult = userDao.checkOwnerId(resultSet, email);
        assertFalse(checkOwnerIdResult);
    }

    @Then("^Next time I cannot retrieve information which first name is \"([^\"]*)\", last name is \"([^\"]*)\", password is \"([^\"]*)\" from user table by owner id \"([^\"]*)\"$")
    public void nextTimeICannotRetrieveInformationWhichFirstNameIsLastNameIsPasswordIsFromUserTableByOwnerId(String firstName, String lastName, String password, String ownerId) throws Throwable {
        String getFirstName = "";
        String getLastName = "";
        String getPasswrod = "";
        ResultSet resultSet = userDao.retrieveInfoByOwnerId(ownerId, connection);
        while(resultSet.next()){
            getFirstName = resultSet.getString("first_name");
            getLastName = resultSet.getString("last_name");
            getPasswrod = resultSet.getString("password");
        }
        assertNotEquals(getFirstName, firstName);
        assertNotEquals(getLastName, lastName);
        assertNotEquals(getPasswrod, password);
        connection.close();
    }

    @Given("^Input owner_id \"([^\"]*)\", type \"([^\"]*)\", plate_number \"([^\"]*)\", make \"([^\"]*)\", model \"([^\"]*)\", fuel \"([^\"]*)\", manufacture date \"([^\"]*)\" is valid$")
    public void inputOwner_idTypePlate_numberMakeModelFuelManufactureDateIsValid(String ownerId, String type, String plateNumber, String make, String model, String fuel, String manufactureDate) throws Throwable {
        vehicle = vehicleDao.checkSubmission(ownerId, type, plateNumber, make, model, fuel, manufactureDate);
        assertNotNull(vehicle);
    }

    @Given("^Plate number \"([^\"]*)\" is not occupied$")
    public void plateNumberIsNotOccupied(String plateNumber) throws Throwable {
        ResultSet resultSet = vehicleDao.fetchPlateNumbers(connection);
        checkPlateNumberResult = vehicleDao.checkPlateNumber(resultSet, plateNumber);
        assertTrue(checkPlateNumberResult);
    }

    @When("^I submit vehicle registration$")
    public void iSubmitVehicleRegistration() throws Throwable {
        insertVehicleResult = vehicleDao.insertVehicleInfo(vehicle, connection, checkPlateNumberResult);
    }

    @Then("^next time I can retrieve registration which type is \"([^\"]*)\", make is \"([^\"]*)\", model is \"([^\"]*)\", fuel is \"([^\"]*)\", manufacture date is \"([^\"]*)\" from vehicle table by plate number is \"([^\"]*)\" and owner id is \"([^\"]*)\"$")
    public void nextTimeICanRetrieveRegistrationWhichTypeIsMakeIsModelIsFuelIsManufactureDateIsFromVehicleTableByPlateNumberIsAndOwnerIdIs(String type, String make, String model, String fuel, String manufactureDate, String plateNumber, String ownerId) throws Throwable {
        ResultSet resultSet = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId, plateNumber, connection);
        String getType = null;
        String getMake = null;
        String getModel = null;
        String getFuel = null;
        String getManufactureDate = null;
        if (resultSet.next()) {
            getType = resultSet.getString("type");
            getMake = resultSet.getString("make");
            getModel = resultSet.getString("model");
            getFuel = resultSet.getString("fuel");
            getManufactureDate = resultSet.getString("manufacture_date");
        }
        assertEquals(getType, type);
        assertEquals(getMake, make);
        assertEquals(getModel, model);
        assertEquals(getFuel, fuel);
        assertEquals(getManufactureDate, manufactureDate);
        connection.close();
    }

    @Given("^Input owner_id \"([^\"]*)\", type \"([^\"]*)\", plate_number \"([^\"]*)\", make \"([^\"]*)\", model \"([^\"]*)\", fuel \"([^\"]*)\", manufacture date \"([^\"]*)\" is not valid$")
    public void inputOwner_idTypePlate_numberMakeModelFuelManufactureDateIsNotValid(String ownerId, String type, String plateNumber, String make, String model, String fuel, String manufactureDate) throws Throwable {
        vehicle = vehicleDao.checkSubmission(ownerId, type, plateNumber, make, model, fuel, manufactureDate);
        assertNull(vehicle);
    }

    @Then("^next time I cannot retrieve registration from plate number \"([^\"]*)\" and owner id is \"([^\"]*)\"$")
    public void nextTimeICannotRetrieveRegistrationFromPlateNumberAndOwnerIdIs(String plateNumber, String ownerId) throws Throwable {
        ResultSet resultSet = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId, plateNumber, connection);
        assertFalse(resultSet.next());
        connection.close();
    }

    @Given("^Plate number \"([^\"]*)\" is occupied$")
    public void plateNumberIsOccupied(String plateNumber) throws Throwable {
        ResultSet resultSet = vehicleDao.fetchPlateNumbers(connection);
        checkPlateNumberResult = vehicleDao.checkPlateNumber(resultSet, plateNumber);
        assertFalse(checkPlateNumberResult);
    }

    @Given("^Registration is existed with owner id \"([^\"]*)\" and plate number \"([^\"]*)\"$")
    public void registrationIsExistedWithOwnerIdAndPlateNumber(String ownerId, String plateNumber) throws Throwable {
        checkOwnerExistedResult = vehicleDao.checkOwnerExisted(connection, ownerId, plateNumber);
        assertTrue(checkOwnerExistedResult);
    }

    @When("^I update vehicle registration$")
    public void iUpdateVehicleRegistration() throws Throwable {
        updateVehicleRegistrationResult = vehicleDao.updateVehicleInfo(vehicle, connection, checkOwnerExistedResult);
    }

    @Then("^next time I cannot retrieve registration which type is \"([^\"]*)\", make is \"([^\"]*)\", model is \"([^\"]*)\", fuel is \"([^\"]*)\", manufacture date is \"([^\"]*)\" from vehicle table by plate number is \"([^\"]*)\" and owner id is \"([^\"]*)\"$")
    public void nextTimeICannotRetrieveRegistrationWhichTypeIsMakeIsModelIsFuelIsManufactureDateIsFromVehicleTableByPlateNumberIsAndOwnerIdIs(String type, String make, String model, String fuel, String manufactureDate, String plateNumber, String ownerId) throws Throwable {
        ResultSet resultSet = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId, plateNumber, connection);
        String getType = null;
        String getMake = null;
        String getModel = null;
        String getFuel = null;
        String getManufactureDate = null;
        if (resultSet.next()) {
            getType = resultSet.getString("type");
            getMake = resultSet.getString("make");
            getModel = resultSet.getString("model");
            getFuel = resultSet.getString("fuel");
            getManufactureDate = resultSet.getString("manufacture_date");
        }
        assertNotEquals(getType, type);
        assertNotEquals(getMake, make);
        assertNotEquals(getModel, model);
        assertNotEquals(getFuel, fuel);
        assertNotEquals(getManufactureDate, manufactureDate);
        connection.close();
    }

    @Given("^Registration is not existed with owner id \"([^\"]*)\" and plate number \"([^\"]*)\"$")
    public void registrationIsNotExistedWithOwnerIdAndPlateNumber(String ownerId, String plateNumber) throws Throwable {
        checkOwnerExistedResult = vehicleDao.checkOwnerExisted(connection, ownerId, plateNumber);
        assertFalse(checkOwnerExistedResult);
    }

    @When("^I remove a vehicle registration with owner id \"([^\"]*)\" and plate number \"([^\"]*)\"$")
    public void iRemoveAVehicleRegistrationWithOwnerIdAndPlateNumber(String ownerId, String plateNumber) throws Throwable {
        removeVehicleRegistrationResult = vehicleDao.removeVehicleRegistration(connection, plateNumber, ownerId, checkOwnerExistedResult);
    }

    @Given("^four registrations can be retrieve from vehicle table$")
    public void RegistrationsCanBeRetrieveFromVehicleTable() throws Throwable {
        registrationsBefore = vehicleDao.fetchPlateNumbers(connection);
    }

    @Then("^four registrations without any changes$")
    public void RegistrationsWithoutAnyChanges() throws Throwable {
        registrationsAfter = vehicleDao.fetchPlateNumbers(connection);
        HashSet<String> plateNumbersBefore = new HashSet<>();
        HashSet<String> plateNumbersAfter = new HashSet<>();
        while (registrationsBefore.next()){
            plateNumbersBefore.add(registrationsBefore.getString("plate_number"));
        }
        while (registrationsAfter.next()){
            plateNumbersAfter.add(registrationsAfter.getString("plate_number"));
        }
        assertEquals(plateNumbersBefore, plateNumbersAfter);
        connection.close();

    }





}
