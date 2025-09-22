package cat.itacademy.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DatabaseConnection {

    private DatabaseConnection() {
        // Evitar instanciación
    }

    public static Connection getConnection() {
        try (InputStream input = DatabaseConnection.class.getClassLoader()
                .getResourceAsStream("db.properties")) {

            if (input == null) {
                throw new RuntimeException("❌ No se encontró el archivo db.properties en el classpath");
            }

            Properties props = new Properties();
            props.load(input);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");
            String driver = props.getProperty("db.driver");

            if (url == null || user == null || password == null || driver == null) {
                throw new RuntimeException("❌ Faltan propiedades de conexión en db.properties");
            }

            Class.forName(driver);

            Connection connection = DriverManager.getConnection(url, user, password);
            //System.out.println("✅ Conexión exitosa a la base de datos");
            return connection;

        } catch (IOException e) {
            throw new RuntimeException("❌ Error al leer db.properties: " + e.getMessage(), e);
        } catch (SQLException e) {
            throw new RuntimeException("❌ Error de SQL: " + e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ Driver JDBC no encontrado: " + e.getMessage(), e);
        }
    }
}

