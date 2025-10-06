package cat.itacademy.repository.util;

import cat.itacademy.message.error.DBErrorMessages;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBPropertiesLoader {

    private static final String DEFAULT_FILE = "db.properties";

    private DBPropertiesLoader() {}

    public static Properties load() {
        try (InputStream input = DBPropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream(DEFAULT_FILE)) {

            if (input == null) {
                throw new RuntimeException(DBErrorMessages.ERROR_DB_PROPERTIES_NOT_FOUND);
            }

            Properties props = new Properties();
            props.load(input);

            validateProperties(props);
            return props;

        } catch (IOException e) {
            throw new RuntimeException(String.format(DBErrorMessages.ERROR_READING_PROPERTIES, e.getMessage()), e);
        }
    }

    private static void validateProperties(Properties props) {
        if (props.getProperty("db.url") == null ||
                props.getProperty("db.user") == null ||
                props.getProperty("db.password") == null ||
                props.getProperty("db.driver") == null) {
            throw new RuntimeException(DBErrorMessages.ERROR_DB_PROPERTIES_MISSING_FIELDS);
        }
    }
}
