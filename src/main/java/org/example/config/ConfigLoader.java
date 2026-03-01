package org.example.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties = new Properties();

    public ConfigLoader() {
        try (InputStream input =
                     getClass().getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException("Nie znaleziono pliku application.properties");
            }

            properties.load(input);

        } catch (IOException e) {
            throw new RuntimeException("Błąd ładowania konfiguracji", e);
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }
}

