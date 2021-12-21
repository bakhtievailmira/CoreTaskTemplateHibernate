package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Util util;
    private static PreparedStatement preparedStatement;
    private static long myId = 1;
    {
        try {
            util = new Util();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            String sql = "create table if not exists user ( id decimal , name varchar(25), lastname varchar(25), age decimal );";
            preparedStatement = util.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            String sql = "drop table if  exists user ;";
            preparedStatement = util.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            String sql = "insert into user (user.id,user.name,user.lastname,user.age) value (?, ?, ?, ?)";
            preparedStatement = util.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, myId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setByte(4, age);
            preparedStatement.executeUpdate();
            myId++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            String sql = "delete from user where id = ?;";
            preparedStatement = util.getConnection().prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> myList = new ArrayList<>();
        try {
            String sql = "select name, lastname,age from user;";
            preparedStatement = util.getConnection().prepareStatement(sql);
            ResultSet rs= preparedStatement.executeQuery();
            while(rs.next())
             myList.add( new User(  rs.getString(1),rs.getString(2) , (byte)rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }


    public void cleanUsersTable() {
        try {
            String sql = "delete  from user;";
            preparedStatement = util.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
