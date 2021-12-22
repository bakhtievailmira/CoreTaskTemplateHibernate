package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;

import javax.security.auth.login.Configuration;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/test";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection;

    public Util() throws SQLException {
        connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        connection.setAutoCommit(false);
    }

    public Connection getConnection() {
        return this.connection;
    }
}
