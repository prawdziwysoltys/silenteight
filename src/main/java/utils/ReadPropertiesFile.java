package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ReadPropertiesFile {

//    private static ResourceBundle rb = ResourceBundle.getBundle("config");

    public static String getPropertyByKey(String key){
        FileInputStream fileInputStream;
        ResourceBundle rb;
        try {
            fileInputStream = new FileInputStream("config.properties");
            rb = new PropertyResourceBundle(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return rb.getString(key);
    }
}
