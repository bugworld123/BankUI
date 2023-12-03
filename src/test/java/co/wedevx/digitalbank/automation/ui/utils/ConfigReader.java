package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

// build a logic that reads the config file(properties file)
public class ConfigReader {

    private static Properties properties;


    // static initializer run the block only once for the whole project
    // instance initializer run the block once for every object creation from the class
    static{
        //filePath-> the directory of your properties file
        String filePath="src/test/resources/properties/digitalbank.properties";


        // this a class that enables you to read files
        FileInputStream input=null;

        try {
            input=new FileInputStream(filePath);
            properties=new Properties();
            properties.load(input);
            input.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }



    }

    public static String getPropertyValue(String key){
      return properties.getProperty(key);
    }


}
