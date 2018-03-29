package dao;

import entity.Vehicle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import static org.junit.Assert.*;

public class TestVehicleDao {
    VehicleDao vehicleDao = null;
    Connection connection = null;

    @Before
    public void before() throws SQLException {
        String url = "jdbc:sqlite:./assignment.sqlite";
        connection = DriverManager.getConnection(url);
    }

    @After
    public void after() throws SQLException {
        connection.close();
    }

    @Test
    public void testFetchPlateNumbers() throws SQLException {
        ResultSet resultSet1 = vehicleDao.fetchPlateNumbers(connection);
        HashSet<String> plateNumbers = new HashSet<>();
        HashSet<String> fetchPlateNumbers = new HashSet<>();
        plateNumbers.add("CP3333");
        plateNumbers.add("TRA111");
//        plateNumbers.add("JH1313");
        while(resultSet1.next()){
            String plateNumber = resultSet1.getString("plate_number");
            if (!(plateNumber.equals("JH1313") || plateNumber.equals("KI1111"))) fetchPlateNumbers.add(plateNumber);
        }
        assertEquals(plateNumbers, fetchPlateNumbers);
    }

    @Test
    public void testCheckSubmission(){
        Vehicle vehicle1 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle2 = vehicleDao.checkSubmission("","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle3 = vehicleDao.checkSubmission("kyre.irving@gmail.com","", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle4 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle5 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle6 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "", "diesel", "11/11/2016");
        Vehicle vehicle7 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "", "11/11/2016");
        Vehicle vehicle8 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "");
        Vehicle vehicle9 = vehicleDao.checkSubmission("kyre.irvinggmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle10 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MD", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle11 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI-111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle12 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota.", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle13 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "Land-Cruiser", "diesel", "11/11/2016");
        Vehicle vehicle14 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "fire", "11/11/2016");
        Vehicle vehicle15 = vehicleDao.checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/1/2016");
        assertNotNull(vehicle1);
        assertNull(vehicle2);
        assertNull(vehicle3);
        assertNull(vehicle4);
        assertNull(vehicle5);
        assertNull(vehicle6);
        assertNull(vehicle7);
        assertNull(vehicle8);
        assertNull(vehicle9);
        assertNull(vehicle10);
        assertNull(vehicle11);
        assertNull(vehicle12);
        assertNull(vehicle13);
        assertNull(vehicle14);
        assertNull(vehicle15);
    }

    @Test
    public void testCheckPlateNumber() throws SQLException {
        String plateNumber1 = "JH1313";
        String plateNumber2 = "CP3333";
        String plateNumber3 = "TRA111";
        String plateNumber4 = "YCU200";
        ResultSet resultSet1 = vehicleDao.fetchPlateNumbers(connection);
        Boolean result2 = vehicleDao.checkPlateNumber(resultSet1, plateNumber2);
        assertFalse(result2);
        resultSet1 = vehicleDao.fetchPlateNumbers(connection);
        Boolean result3 = vehicleDao.checkPlateNumber(resultSet1, plateNumber3);
        assertFalse(result3);
        resultSet1 = vehicleDao.fetchPlateNumbers(connection);
        Boolean result4 = vehicleDao.checkPlateNumber(resultSet1, plateNumber4);
        assertTrue(result4);
    }

    @Test
    public void testInsertVehicleInfo(){
        Vehicle vehicle1 = new Vehicle("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        boolean result1 = vehicleDao.insertVehicleInfo(vehicle1, connection, false);
        boolean result2 = vehicleDao.insertVehicleInfo(vehicle1, connection, true);
        assertFalse(result1);
        assertTrue(result2);
    }


    @Test
    public void testRetrieveVehicleInfoByOwnerIdAndPlateNumber() throws SQLException {
        String ownerId1 = "harden.james@gmail.com";
        String ownerId2 = "chris.paul@gmail.com";
        String ownerId3 = "ycu20@uclive.ac.nz";
        String plateNumber1 = "JH1313";
        String plateNumber2 = "CP3333";
        String plateNumber3 = "TRA111";
        ResultSet resultSet1 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId1, plateNumber1, connection);
        ResultSet resultSet2 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId1, plateNumber2, connection);
        ResultSet resultSet3 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId2, plateNumber1, connection);
        ResultSet resultSet4 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId2, plateNumber2, connection);
        ResultSet resultSet5 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId2, plateNumber3, connection);
        ResultSet resultSet6 = vehicleDao.retrieveVehicleInfoByOwnerIdAndPlateNumber(ownerId3, plateNumber1, connection);
        assertNotNull(resultSet1.getString("type"));
        assertNotNull(resultSet1.getString("make"));
        assertNotNull(resultSet1.getString("model"));
        assertNotNull(resultSet1.getString("fuel"));
        assertNotNull(resultSet1.getString("manufacture_date"));
        assertTrue(resultSet1.next());
        assertFalse(resultSet2.next());
        assertFalse(resultSet3.next());
        assertNotNull(resultSet4.getString("type"));
        assertNotNull(resultSet4.getString("make"));
        assertNotNull(resultSet4.getString("model"));
        assertNotNull(resultSet4.getString("fuel"));
        assertNotNull(resultSet4.getString("manufacture_date"));
        assertTrue(resultSet4.next());
        assertNotNull(resultSet5.getString("type"));
        assertNotNull(resultSet5.getString("make"));
        assertNotNull(resultSet5.getString("model"));
        assertNotNull(resultSet5.getString("fuel"));
        assertNotNull(resultSet5.getString("manufacture_date"));
        assertTrue(resultSet5.next());
        assertFalse(resultSet6.next());
    }

    @Test
    public void testCheckOwnerExisted() throws SQLException {
        Vehicle vehicle1 = new Vehicle("ycu20@uclive.ac.nz","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle2 = new Vehicle("chris.paul@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        Vehicle vehicle3 = new Vehicle("chris.paul@gmail.com","MC", "CP3333", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        boolean checkOwnerExisted1 = vehicleDao.checkOwnerExisted(connection, vehicle1.getOwnerId(), vehicle1.getPlateNumber());
        boolean checkOwnerExisted2 = vehicleDao.checkOwnerExisted(connection, vehicle2.getOwnerId(), vehicle2.getPlateNumber());
        boolean checkOwnerExisted3 = vehicleDao.checkOwnerExisted(connection, vehicle3.getOwnerId(), vehicle3.getPlateNumber());
        assertFalse(checkOwnerExisted1);
        assertFalse(checkOwnerExisted2);
        assertTrue(checkOwnerExisted3);
    }

    @Test
    public void testUpdateVehicleInfo(){
        Vehicle vehicle = new Vehicle("chris.paul@gmail.com","MC", "CP3333", "Toyota", "LandCruiser", "diesel", "11/11/2016");
        boolean result1 = vehicleDao.updateVehicleInfo(vehicle, connection, false);
        boolean result2 = vehicleDao.updateVehicleInfo(vehicle, connection, true);
        assertFalse(result1);
        assertTrue(result2);
    }

    @Test
    public void testRemoveVehicleRegistration(){
        boolean result1 = vehicleDao.removeVehicleRegistration(connection, "JH1313", "harden.james@gmail.com", false);
        boolean result2 = vehicleDao.removeVehicleRegistration(connection, "JH1313", "harden.james@gmail.com", true);
        assertFalse(result1);
        assertTrue(result2);
    }

}
