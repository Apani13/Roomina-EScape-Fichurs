package cat.itacademy.repository;

import cat.itacademy.messages.error.DBErrorMessages;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseConnection {

    private DatabaseConnection() { }

    public static Connection getConnection() {
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException(DBErrorMessages.ERROR_DB_PROPERTIES_NOT_FOUND);
            }

            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            if (url == null || user == null || password == null || driver == null) {
                throw new RuntimeException(DBErrorMessages.ERROR_DB_PROPERTIES_MISSING_FIELDS);
            }

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;

        } catch (IOException e) {
            throw new RuntimeException(String.format(DBErrorMessages.ERROR_READING_PROPERTIES, e.getMessage()), e);
        } catch (SQLException e) {
            throw new RuntimeException(String.format(DBErrorMessages.ERROR_DB_PROPERTIES_READ, e.getMessage()), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(String.format(DBErrorMessages.ERROR_DB_DRIVER_NOT_FOUND, e.getMessage()), e);
        }
    }
}

