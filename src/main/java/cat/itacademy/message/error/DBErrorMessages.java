package cat.itacademy.message.error;

public class DBErrorMessages {
    public DBErrorMessages() { }

    public static final String ERROR_DB_PROPERTIES_NOT_FOUND = "No se encontró el archivo db.properties en el classpath";
    public static final String ERROR_DB_PROPERTIES_MISSING_FIELDS = "Faltan propiedades de conexión en db.properties";
    public static final String ERROR_READING_PROPERTIES = "Error al leer db.properties: %s";
    public static final String ERROR_DB_PROPERTIES_READ = "Error de SQL: %s";
    public static final String ERROR_DB_DRIVER_NOT_FOUND = "Driver JDBC no encontrado: %s";
    public static final String ERROR_DB_UNEXPECTED_PROBLEM = "Error inesperado: %s";
}
