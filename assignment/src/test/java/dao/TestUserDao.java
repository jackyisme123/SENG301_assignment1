package dao;

import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

public class TestUserDao {

    UserDao userDao = null;
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
    public void testFetchOwnerIds() throws SQLException {
        HashSet<String> ownerIds = new HashSet<>();
        HashSet<String> fetchIds = new HashSet<>();
        ownerIds.add("harden.james@gmail.com");
        ownerIds.add("kyre.irving@gmail.com");
        ownerIds.add("chris.paul@gmail.com");
        ownerIds.add("lebron.james@gmail.com");
        ResultSet resultSet = userDao.fetchOwnerIds(connection);
        while(resultSet.next()){
            String ownerId = resultSet.getString( "own_id");
            if(!ownerId.equals("ycu20@uclive.ac.nz"))fetchIds.add(ownerId);
        }
        assertEquals(ownerIds, fetchIds);
    }

    @Test
    public void testCheckSubmission(){
        User user1 = userDao.checkSubmission("Yuan", "Cui", "ycu20@uclive.ac.nz", "12345" );
        User user2 = userDao.checkSubmission("", "Cui", "ycu20@uclive.ac.nz", "12345" );
        User user3 = userDao.checkSubmission("Yuan", "", "ycu20@uclive.ac.nz", "12345" );
        User user4 = userDao.checkSubmission("Yuan", "Cui", "", "12345" );
        User user5 = userDao.checkSubmission("Yuan", "Cui", "ycu20@uclive.ac.nz", "" );
        User user6 = userDao.checkSubmission("Yuan1", "Cui", "ycu20@uclive.ac.nz", "12345" );
        User user7 = userDao.checkSubmission("Yuan", "Cui*", "ycu20@uclive.ac.nz", "12345" );
        User user8 = userDao.checkSubmission("Yuan", "Cui", "ycu20uclive.ac.nz", "12345" );
        assertNotNull(user1);
        assertNull(user2);
        assertNull(user3);
        assertNull(user4);
        assertNull(user5);
        assertNull(user6);
        assertNull(user7);
        assertNull(user8);
    }

    @Test
    public void testCheckOwnerId() throws SQLException {
        ResultSet resultSet = userDao.fetchOwnerIds(connection);
        String owner_id1 = "harden.james@gmail.com";
        String owner_id2 = "kyre.irving@gmail.com";
        String owner_id3 = "chris.paul@gmail.com";
        String owner_id4 = "lebron.james@gmail.com";
        String owner_id5 = "ycu21@uclive.ac.nz";
        boolean resutl1 = userDao.checkOwnerId(resultSet, owner_id1);
        resultSet = userDao.fetchOwnerIds(connection);
        boolean resutl2 = userDao.checkOwnerId(resultSet, owner_id2);
        resultSet = userDao.fetchOwnerIds(connection);
        boolean resutl3 = userDao.checkOwnerId(resultSet, owner_id3);
        resultSet = userDao.fetchOwnerIds(connection);
        boolean resutl4 = userDao.checkOwnerId(resultSet, owner_id4);
        resultSet = userDao.fetchOwnerIds(connection);
        boolean resutl5 = userDao.checkOwnerId(resultSet, owner_id5);
        assertFalse(resutl1);
        assertFalse(resutl2);
        assertFalse(resutl3);
        assertFalse(resutl4);
        assertTrue(resutl5);
    }

    @Test
    public void checkInsertUserInfo(){
        User insert_user = new User("Yuan", "Cui", "ycu20@uclive.ac.nz", "12345");
        boolean result2 = userDao.insertUserInfo(insert_user, connection, false);
        boolean result1 = userDao.insertUserInfo(insert_user, connection, true);
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void checkRetrieveInfoByOwnerId() throws SQLException {
        String owner_id1 = "harden.james@gmail.com";
        String owner_id2 = "kyre.irving@gmail.com";
        String owner_id3 = "chris.paul@gmail.com";
        String owner_id4 = "lebron.james@gmail.com";
        String owner_id5 = "kobe.briant@gmail.com";
        ResultSet resultSet1 = userDao.retrieveInfoByOwnerId(owner_id1, connection);
        ResultSet resultSet2 = userDao.retrieveInfoByOwnerId(owner_id2, connection);
        ResultSet resultSet3 = userDao.retrieveInfoByOwnerId(owner_id3, connection);
        ResultSet resultSet4 = userDao.retrieveInfoByOwnerId(owner_id4, connection);
        ResultSet resultSet5 = userDao.retrieveInfoByOwnerId(owner_id5, connection);
        assertNotNull(resultSet1.getString("first_name"));
        assertNotNull(resultSet1.getString("last_name"));
        assertNotNull(resultSet1.getString("password"));
        assertNotNull(resultSet2.getString("first_name"));
        assertNotNull(resultSet2.getString("last_name"));
        assertNotNull(resultSet2.getString("password"));
        assertNotNull(resultSet3.getString("first_name"));
        assertNotNull(resultSet3.getString("last_name"));
        assertNotNull(resultSet3.getString("password"));
        assertNotNull(resultSet4.getString("first_name"));
        assertNotNull(resultSet4.getString("last_name"));
        assertNotNull(resultSet4.getString("password"));
        assertTrue(resultSet1.next());
        assertTrue(resultSet2.next());
        assertTrue(resultSet3.next());
        assertTrue(resultSet4.next());
        assertFalse(resultSet5.next());
    }

}
