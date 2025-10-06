package cat.itacademy.repository.util;

import cat.itacademy.repository.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseCleaner {

    private static final Logger LOGGER = Logger.getLogger(DatabaseCleaner.class.getName());

    private DatabaseCleaner() { }

    public static void clearAllTables() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            List<String> tables = getAllTableNames(conn);

            disableForeignKeyChecks(conn);

            for (String table : tables) {
                try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM " + table)) {
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    LOGGER.log(Level.WARNING, "No se pudo limpiar tabla " + table + ": " + e.getMessage(), e);
                }
            }

            enableForeignKeyChecks(conn);

            conn.commit();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "❌ Error general al limpiar la base de datos: " + e.getMessage(), e);
        }
    }

    private static List<String> getAllTableNames(Connection conn) throws SQLException {
        List<String> tables = new ArrayList<>();
        DatabaseMetaData metaData = conn.getMetaData();

        String catalog = conn.getCatalog();
        String schema = conn.getSchema();

        try (ResultSet rs = metaData.getTables(catalog, schema, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                tables.add(tableName);
            }
        }
        return tables;
    }

    private static void disableForeignKeyChecks(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0")) {
            stmt.execute();
        } catch (SQLException ignored) {
            // Si no soporta (por ejemplo, PostgreSQL), no pasa nada
        }
    }

    private static void enableForeignKeyChecks(Connection conn) {
        try (PreparedStatement stmt = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1")) {
            stmt.execute();
        } catch (SQLException ignored) { }
    }
}
