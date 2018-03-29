/**
 * This class for user database
 * @author Yuan Cui
 * @version 1.0
 */

package dao;

import entity.User;

import java.sql.*;

public class UserDao {

    /*
        select all owner id in table user from given connection
        @param connection a non null connection to database
        @return the result set (sql cursor) with all owner id in database
        @throws SQLException if any error occurs regrading database
     */
    public static ResultSet fetchOwnerIds(Connection connection) throws SQLException {
        assert null != connection;
        System.out.println("get all owner ids");
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT own_id from user;");
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    /*
        check first name, last name, email address, password were submitted all at once
        @param first_name at least one letter from alphabet
               last_name at least one letter from alphabet
               owner_id format as xxx@xxx.xxx. "xxx" means "a-zA-Z0-9"and allow "." or "_" at left side of "@"
               password at least one letter from ASCII table
        @return User object with information if passing check, otherwise null;
     */

    public static User checkSubmission(String firstName, String lastName, String ownerId, String password){
        System.out.println("Check submission!");
        User user =null;
        String name_reg = "^\\p{Alpha}+$";
        String id_reg="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        String pwd_reg = "^\\p{ASCII}+$";
        boolean result = true;
        if(!firstName.matches(name_reg)) {
            result=false;
            System.out.println("Check your first name.");
        }
        if(!lastName.matches(name_reg)) {
            result=false;
            System.out.println("Check your last name.");
        }
        if(!ownerId.matches(id_reg)) {
            result=false;
            System.out.println("Check your email address.");
        }
        if(!password.matches(pwd_reg)) {
            result=false;
            System.out.println("Check your password.");
        }

        if(result){
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setOwnerId(ownerId);
            user.setPassword(password);
        }

        return user;
    }

    /*
        check if owner id has been occupied
        @param ownerIdSet result set from database by function fetchOwnerIds
               ownerId get from user input
        @return ownerId a boolean value if true owner id can be used, otherwise cannot
        @throws SQLException if any error occurs regarding database
     */
    public static boolean checkOwnerId(ResultSet ownerIdSet, String ownerId) throws SQLException {
        assert null != ownerId;
        System.out.println("Check owner id occupied");
        boolean result = true;
        while (ownerIdSet.next()){
            String ownerIdInDatabase = ownerIdSet.getString("own_id");
            if(ownerIdInDatabase.equals(ownerId)){
                result=false;
                System.out.println("Owner id has been occupied!");
                break;
            }
        }
        return result;
    }

    /*
        show error message if email has been occupied
        @param checkOwnerIdResult result of function checkOwnerId
     */
    public static void displayErrorMessage(Boolean checkOwnerIdResult){
        assert checkOwnerIdResult != true;
        if(!checkOwnerIdResult){
            System.out.println("Email address has been occupied");
        }
    }

    /*
        insert user information into database
        @param user a non null User object
               connection  a non null connection to database
               checkOwenIdResult a boolean value from function checkOwnerId
        @return true if user information has been stored in user table, otherwise false
     */
    public static boolean insertUserInfo(User user, Connection connection, Boolean checkOwnerIdResult){
        assert null!=connection;
        System.out.println("Insert User Info");
        if(user==null || !checkOwnerIdResult) return false;
        Boolean result = true;
        String insert = "INSERT INTO user (first_name, last_name, own_id, password) VALUES(?, ?, ?, ?);";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getOwnerId());
            preparedStatement.setString(4, user.getPassword());
            int num = preparedStatement.executeUpdate();
            if (num != 1) {
                result = false;
            } else {
                System.out.println("rows added: " + num);
                System.out.println("Successful user insertion");
            }
        }catch (SQLException e) {
            result = false;
            System.out.println(e.getMessage());
            System.out.println("Failure user insertion");
        }finally {
            return result;
        }
    }

    /*
        show confirmation message if user account has been created
        @param insertUserInfoResult result of function insertUserInfo
     */
    public static void displayConfirmation(Boolean insertUserInfoResult){
        assert insertUserInfoResult!=false;
        if(insertUserInfoResult){
            System.out.println("User has been created");
        }
    }

    /*
        retrieve a user information by owner id
        @param ownerId get from user input
               connection a non null connection to database
        @return a ResultSet of the user information
        @throws SQLException if any error occurs regard database
     */
    public static ResultSet retrieveInfoByOwnerId(String ownerId, Connection connection) throws SQLException {
        assert null != ownerId && null != connection;
        System.out.println("fetch the user information by owner id");
        String query = "SELECT first_name, last_name, password FROM user WHERE own_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, ownerId);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static void main(String[] args){
        String url = "jdbc:sqlite:./assignment.sqlite";
        System.out.println("open connection to " + url);
        try {
            Connection connection = DriverManager.getConnection(url);
            User user = checkSubmission("Yuan", "Cui", "ycu20@uclive.ac.nz", "12345");
            ResultSet resultSet = fetchOwnerIds(connection);
            Boolean checkOwnerIdResult = checkOwnerId(resultSet, user.getOwnerId());
            if (!checkOwnerIdResult){
                displayErrorMessage(checkOwnerIdResult);
            }else{
                boolean insertUserResult = insertUserInfo(user, connection, checkOwnerIdResult);
                if(insertUserResult){
                    displayConfirmation(insertUserResult);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
