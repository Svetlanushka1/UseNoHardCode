package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    static Properties properties = new Properties();
    public static String getProperty(String parameters) {
        if(properties.toString().equals("{}")){//singleton - if it is null call method
            initProperty();
        }
        return properties.getProperty(parameters);//if it is not null do nothing
    }
    private static void initProperty(){
        try(FileReader fileReader = new FileReader("src/test/resources/login.properties")) {//reade file.txt
            properties.load(fileReader);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
