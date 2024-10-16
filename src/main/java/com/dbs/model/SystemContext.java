package com.dbs.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class SystemContext {
    private String OS;
    private String driver;
    private String driverdir;
    private String testdatadir
    ;
    public SystemContext(String propertiesFilePath) {
        loadProperties(propertiesFilePath);
    }

    private void loadProperties(String filePath) {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Map<String, Object> data = yaml.load(inputStream);

            this.OS = (String) data.get("OS");
            this.driver = (String) data.get("driver");
            this.driverdir = (String) data.get("driverdir");
            this.testdatadir = (String) data.get("testdatadir");
        } catch (FileNotFoundException e) {
            System.err.println("Properties file not found: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error reading properties file: " + filePath);
            e.printStackTrace();
        }
    }

    // Getters
    public String getOS() {
        return OS;
    }

    public String getDriver() {
        if (this.driver.equals("chrome")) {
            return "webdriver.chrome.driver";
        } else if (this.driver.equals("firefox")) {
            return "webdriver.firefox.driver";
        } else {
            return "webdriver.edge.driver";
        }
    }

    public String getDriverdir() {
        return driverdir;
    }
    public String getTestDatadir() {
        return testdatadir;
    }

    // ToString method for easy debugging
    @Override
    public String toString() {
        return "SystemContext{" +
                "OS='" + OS + '\'' +
                ", driver='" + driver + '\'' +
                ", driverdir='" + driverdir + '\'' +
                '}';
    }
}
