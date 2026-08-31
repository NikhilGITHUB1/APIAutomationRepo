package com.qa.gorest.configuration;

import com.qa.gorest.fwexception.APIFrameworkException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {

    private Properties prop;
    private FileInputStream fis;

    public Properties initProp() {
        prop = new Properties();
        String envName = System.getProperty("env");

        try{
            if(envName==null){
                System.out.println("no env, running on test env");
                fis = new FileInputStream("./src/test/resources/config/qa.config.properties");
            }
            else {
                System.out.println("running on given env:" + envName);

                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fis = new FileInputStream("./src/test/resources/config/qa.config.propeties");
                        break;
                    case "dev":
                        fis = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    default:
                        System.out.println("Please pass the right env");
                        throw new APIFrameworkException("PLEASE PASS THE RIGHT ENV");
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            prop.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return prop;

    }
}
