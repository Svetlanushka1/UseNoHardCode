package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties = new Properties();
    public static String getProperty(String parameters) {
        if(properties.toString().equals("{}")){
            initProperty();
        }
        return properties.getProperty(parameters);
    }
    private static void initProperty(){
        try(FileReader fileReader = new FileReader("src/test/resources/config.properties")) {
            properties.load(fileReader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
