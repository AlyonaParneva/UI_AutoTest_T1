package t1.core.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream is =
                     PropertyReader.class
                             .getClassLoader()
                             .getResourceAsStream("db.properties")) {

            if (is != null) {
                PROPERTIES.load(is);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load db.properties", e);
        }
    }

    public static String get(String key) {
        String envKey = key.toUpperCase().replace(".", "_");
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue;
        }

        return PROPERTIES.getProperty(key);
    }
}
