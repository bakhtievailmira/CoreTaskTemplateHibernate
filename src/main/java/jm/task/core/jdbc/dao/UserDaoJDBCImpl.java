package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util;
    private long myId = 1;
    private Connection connection;

    {
        try {
            util = new Util();
            connection = util.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("create table if not exists user ( id decimal , name varchar(25), lastname varchar(25), age decimal );")) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void dropUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("drop table if  exists user ;")) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("insert into user (user.id,user.name,user.lastname,user.age) value (?, ?, ?, ?)");) {
            preparedStatement.setLong(1, myId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
            connection.commit();
            myId++;
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public void removeUserById(long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete from user where id = ?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> myList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select name, lastname,age from user;")) {
            ResultSet rs = preparedStatement.executeQuery();
            connection.commit();
            while (rs.next())
                myList.add(new User(rs.getString(1), rs.getString(2), (byte) rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
        return myList;
    }


    public void cleanUsersTable() throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("delete  from user;")) {
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            connection.rollback();
        }
    }
}
