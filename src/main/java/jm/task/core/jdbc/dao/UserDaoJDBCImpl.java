package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Util util;
    private static Statement statement;
    private static long myId = 1;
    {
        try {
            util = new Util();
            statement = util.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            statement.executeUpdate("create table if not exists user ( id decimal , name varchar(25), lastname varchar(25), age decimal );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try {
            statement.executeUpdate("drop table if  exists user ;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            statement.executeUpdate("insert into user (user.id,user.name,user.lastname,user.age) value ('"+ myId + "','"+ name+ "','" + lastName +"','" + age +"')" );
            myId++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            statement.executeUpdate("delete from user where id = " + myId + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> myList = new ArrayList<>();
        try {
           ResultSet rs= statement.executeQuery("select name, lastname,age from user;");
            while(rs.next())
             myList.add( new User(  rs.getString(1),rs.getString(2) , (byte)rs.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }


    public void cleanUsersTable() {
        try {
            statement.executeUpdate("delete  from user;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
