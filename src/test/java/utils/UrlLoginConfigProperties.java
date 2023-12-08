package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class UrlLoginConfigProperties {
    static Properties properties = new Properties();

    public static String getProperty(String parameters) {
        if (properties.toString().equals("{}")) {
            initProperty();
        }
        return properties.getProperty(parameters);
    }

    private static void initProperty() {
        String target = System.getProperty("target", "production");//read command from terminal
        String path = String.format("src/test/resources/%s.properties", target);
        try (FileReader fileReader = new FileReader(path)) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
