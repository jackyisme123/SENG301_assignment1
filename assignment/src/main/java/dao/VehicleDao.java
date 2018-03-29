package dao;

import entity.Vehicle;

import java.sql.*;

public class VehicleDao {

    /*
        fetch all plate numbers in table vehicle from given connection by login user
        @param connection a non null connection to database
               owenId login user email address
        @return the result set (sql cursor) with all plate numbers in vehicle table
        @throws SQLException if any error occurs regrading database
     */
    public static ResultSet fetchPlateNumbers(Connection connection) throws SQLException {
        assert null != connection;
        System.out.println("fetch all plate numbers by owner id");
        String query = "SELECT plate_number FROM vehicle;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    /*
        check owner id, type, plate number, make, model, fuel, manufacture date were submitted all at once
        @param owner_id format as xxx@xxx.xxx. "xxx" means "a-zA-Z0-9"and allow "." or "_" at left side of "@"
               type belongs to "MA", "MB", "MC", "T" or "O"
               plateNumber is made of at least one alphabet letter or digital number
               make is made of at least one alphabet letter
               model is made of at least one alphabet letter or digital number
               fuel belongs to "diesel", "petrol", "electric", "gas" or "other"
               manufactrueDate is format as xx/xx/xxxx
        @return Vehicle object with information if passing check, otherwise null;
     */
    public static Vehicle checkSubmission(String ownerId, String type, String plateNumber, String make, String model, String fuel, String manufactureDate){
        System.out.println("Check submission!");
        Vehicle vehicle = null;
        boolean result = true;
        String idReg="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String plateNumberReg = "^[A-Za-z0-9]+$";
        String makeReg = "^\\p{Alpha}+$";
        String modelReg = "^[A-Za-z0-9]+$";
        String manufactureDateReg = "[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}";
        if(!ownerId.matches(idReg)){
            result = false;
            System.out.println("Check your owner id.");
        }
        if(!(type.equals("MA") || type.equals("MB") || type.equals("MC") || type.equals("T") || type.equals("O"))){
            result = false;
            System.out.println("Check your vehicle type.");
        }
        if(!plateNumber.matches(plateNumberReg)){
            result =false;
            System.out.println("Check your vehicle plate number.");
        }
        if(!make.matches(makeReg)){
            result =false;
            System.out.println("Check your vehicle make.");
        }
        if(!model.matches(modelReg)){
            result =false;
            System.out.println("Check your vehicle model.");
        }
        if(!(fuel.equals("diesel") || fuel.equals("petrol") || fuel.equals("electric") || fuel.equals("gas") || fuel.equals("other"))){
            result =false;
            System.out.println("Check your vehicle fuel type.");
        }
        if(!manufactureDate.matches(manufactureDateReg)){
            result =false;
            System.out.println("Check your vehicle manufacture date.");
        }
        if(result){
            vehicle = new Vehicle();
            vehicle.setOwnerId(ownerId);
            vehicle.setType(type);
            vehicle.setPlateNumber(plateNumber);
            vehicle.setMake(make);
            vehicle.setModel(model);
            vehicle.setFuel(fuel);
            vehicle.setManufactureDate(manufactureDate);
        }
        return vehicle;
    }

    /*
        check if plate number has been occupied
        @param plateNumbers result set from vehicle table by function fetchPlateNumberByOwner
               plateNumber get from owner input
        @return a boolean value if true plate number can be registered, otherwise cannot
        @throws SQLException if any error occurs regarding database
     */
    public static boolean checkPlateNumber(ResultSet plateNumbers, String plateNumber) throws SQLException {
        assert null != plateNumber;
        System.out.println("Check if plate number has been registered");
        boolean result = true;
        while(plateNumbers.next()){
            String plateNumberInDatabase = plateNumbers.getString("plate_number");
            System.out.println(plateNumberInDatabase);
            if(plateNumberInDatabase.equals(plateNumber)){
                result = false;
                System.out.println("plate number has been occupied");
                break;
            }
        }
        return result;
    }

    /*
        show error message if plate number has been occupied
        @param checkPlateNumberResult result of function checkPlateNumber
     */
    public static void showErrorMessage(Boolean checkPlateNumberResult){
        assert checkPlateNumberResult != true;
        if(!checkPlateNumberResult){
            System.out.println("Plate number has been occupied");
        }
    }

    /*
        insert vehicle information into vehicle table
        @param vehicle a non null Vehicle object
               connection  a non null connection to database
               checkPlateNumberResult a boolean value from function checkPlateNumber
        @return true if vehicle information has been stored in vehicle table, otherwise false
     */
    public static boolean insertVehicleInfo(Vehicle vehicle, Connection connection, boolean checkPlateNumberResult){
        assert null!=connection;
        System.out.println("register vehicle");
        if(vehicle == null || !checkPlateNumberResult) return false;
        boolean result = true;
        String insert = "INSERT INTO vehicle (owner_id, type, plate_number, make, model, fuel, manufacture_date) VALUES (?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, vehicle.getOwnerId());
            preparedStatement.setString(2, vehicle.getType());
            preparedStatement.setString(3, vehicle.getPlateNumber());
            preparedStatement.setString(4, vehicle.getMake());
            preparedStatement.setString(5, vehicle.getModel());
            preparedStatement.setString(6, vehicle.getFuel());
            preparedStatement.setString(7, vehicle.getManufactureDate());
            int num = preparedStatement.executeUpdate();
            if (num != 1){
                result = false;
            }else{
                System.out.println("rows added: " + num);
                System.out.println("Successful vehicle registration");
            }
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /*
        show confirmation message if vehicle has been registered
        @param insertVehicleResult result of function insertPlateNumber
     */
    public static void displayInsertConfirmation(boolean insertVehicleResult){
        assert insertVehicleResult != false;
        if (insertVehicleResult){
            System.out.println("Vehicle has been registered");
        }
    }

    /*
        retrieve a vehicle information by owner id and plate number
        @param ownerId get from user input
               plateNumber get from user input
               connection a non null connection to database
        @return a ResultSet of the vehicle information
        @throws SQLException if any error occurs regard database
     */
    public static ResultSet retrieveVehicleInfoByOwnerIdAndPlateNumber(String ownerId, String plateNumber, Connection connection) throws SQLException {
        assert null!=ownerId && null!=plateNumber && null!=connection;
        System.out.println("fetch the vehicle information by owner id and plate number");
        String query = "SELECT type, make, model, fuel, manufacture_date FROM vehicle WHERE owner_id = ? AND plate_number = ?; ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ownerId);
        preparedStatement.setString(2, plateNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        return  resultSet;
    }

    /*
        check if there is any plate registration with specific owner id
        @param ownerId get from user input
               plateNumber get from user input
               connection a non null connection to database
        @return a boolean value true if existing, otherwise false
        @throws SQLException if any error occurs regard database
     */
    public static boolean checkOwnerExisted(Connection connection, String ownerId, String plateNumber) throws SQLException {
        assert null!=connection && null!=ownerId;
        String query = "SELECT * FROM vehicle WHERE owner_id = ? AND plate_number = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ownerId);
        preparedStatement.setString(2, plateNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            return true;
        }else{
            return false;
        }
    }

    /*
        update vehicle information with vehicle table excluding plate number and owner id
        @param vehicle a non null Vehicle object
               connection  a non null connection to database
               checkPlateNumberResult a boolean value from function checkPlateNumber
               checkOwnerExistedResult a boolean value from function checkOwnerExisted
        @return true if vehicle information has been updated in vehicle table, otherwise false
     */
    public static boolean updateVehicleInfo(Vehicle vehicle, Connection connection, boolean checkOwnerExistedResult){
        assert null!=connection;
        System.out.println("update vehicle registration");
        if(vehicle == null || !checkOwnerExistedResult) return false;
        boolean result = true;
        String update = "UPDATE vehicle SET type = ?, make = ?, model = ?, fuel = ?, manufacture_date = ? WHERE owner_id = ? AND plate_number = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(update);
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getFuel());
            preparedStatement.setString(5, vehicle.getManufactureDate());
            preparedStatement.setString(6, vehicle.getOwnerId());
            preparedStatement.setString(7, vehicle.getPlateNumber());
            int num = preparedStatement.executeUpdate();
            if (num != 1){
                result = false;
            }else{
                System.out.println("rows updated: " + num);
                System.out.println("Successfully update vehicle registration");
            }
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /*
        show confirmation message if vehicle has been updated
        @param updateVehicleResult result of function updateVehicleInfo
     */
    public static void displayUpdateConfirmation(boolean updateVehicleResult){
        assert updateVehicleResult != false;
        if (updateVehicleResult){
            System.out.println("Vehicle registration has been updated");
        }
    }

    /*
        remove vehicle registration in vehicle table
        @param connection  a non null connection to database
               plateNumber plate number of deleting registration
               ownerId owner id of deleting registration
               retrieveVehicleInfoByOwnerIdAndPlateNumberResult a Result for the result of retrieveVehicleInfoByOwnerIdAndPlateNumber function
        @return true if vehicle information has been deleted in vehicle table, otherwise false
     */
    public static boolean removeVehicleRegistration(Connection connection, String plateNumber, String ownerId, boolean checkOwnerExistedResult){
        assert null!=connection && null!=plateNumber && null!=ownerId;
        System.out.println("remove vehicle registration");
        String remove = "DELETE FROM vehicle WHERE owner_id = ? AND plate_number = ?;";
        boolean result = true;
        try {
            if (!checkOwnerExistedResult) {
                result = false;
            }
            else {
                PreparedStatement preparedStatement = connection.prepareStatement(remove);
                preparedStatement.setString(1, ownerId);
                preparedStatement.setString(2, plateNumber);
                int num = preparedStatement.executeUpdate();
                if(num != 1){
                    result = false;
                }else{
                    System.out.println("rows removed: " + num);
                    System.out.println("Successfully remove vehicle registration");
                }
            }
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }finally {
            return result;
        }
    }

    /*
        show confirmation message if vehicle has been removed
        @param removeVehicleResult result of function removeVehicleRegistration
     */
    public static void displayRemoveConfirmation(boolean removeVehicleResult){
        assert removeVehicleResult != false;
        if (removeVehicleResult){
            System.out.println("Vehicle registration has been removed");
        }
    }

    public static void main(String[] args){
        String url = "jdbc:sqlite:./assignment.sqlite";
        System.out.println("open connection to " + url);
        Connection connection = null;
        ResultSet resultSet;
        boolean checkPlateNumberResult;
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Vehicle vehicle;
        try {
            vehicle = checkSubmission("kyre.irving@gmail.com","MC", "KI1111", "Toyota", "LandCruiser", "diesel", "11/11/2016");
            resultSet = fetchPlateNumbers(connection);
            checkPlateNumberResult = checkPlateNumber(resultSet, vehicle.getPlateNumber());
            if(!checkPlateNumberResult){
                showErrorMessage(checkPlateNumberResult);
            }else{
                Boolean insertVehicleResult = insertVehicleInfo(vehicle, connection, checkPlateNumberResult);
                if(insertVehicleResult){
                    displayInsertConfirmation(insertVehicleResult);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            vehicle = checkSubmission("kyre.irving@gmail.com","MA", "KI1111", "Honda", "Fit", "petrol", "12/12/2017");
            boolean checkOwnerExistedResult = checkOwnerExisted(connection, vehicle.getOwnerId(), vehicle.getPlateNumber());
            if(!checkOwnerExistedResult){
                System.out.println("Owner has not registered with the plate number");
            }else{
                Boolean updateVehicleResult = updateVehicleInfo(vehicle, connection, checkOwnerExistedResult);
                if(updateVehicleResult){
                    displayUpdateConfirmation(updateVehicleResult);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String removeOwnerId = "chris.paul@gmail.com";
        String removePlateNumber = "TRA111";
        try {
            boolean checkOwnerExistedResult = checkOwnerExisted(connection, removeOwnerId, removePlateNumber);
            boolean removeVehicleResult = removeVehicleRegistration(connection, removePlateNumber, removeOwnerId, checkOwnerExistedResult);
            if(removeVehicleResult){
                displayRemoveConfirmation(removeVehicleResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
