package cat.itacademy.repository;

import cat.itacademy.message.error.DBErrorMessages;
import cat.itacademy.repository.util.DBPropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    private static boolean initialized = false;

    private DatabaseConnection() {}

    private static void init() {
        if (!initialized) {
            Properties props = DBPropertiesLoader.load();

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");
            driver = props.getProperty("db.driver");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(String.format(DBErrorMessages.ERROR_DB_DRIVER_NOT_FOUND, e.getMessage()), e);
            }

            initialized = true;
        }
    }

    public static Connection getConnection() {
        try {
            if (!initialized) {
                init();
            }
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(String.format(DBErrorMessages.ERROR_DB_PROPERTIES_READ, e.getMessage()), e);
        }
    }
}
