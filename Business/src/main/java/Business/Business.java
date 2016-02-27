package Business;

import Business.Exceptions.CoreException;
import DataBase.DAO.DBAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Business {

    private DBAccess dbAccess;
    private Properties properties;
    private final String PROPERTY_FILE_NAME = "application.properties";
    private final String DB_PROPERTY_NAME = "databaseSystem";

    private static final Logger logger = LogManager.getLogger(Business.class);


    private static Business business;

    public static Business getBusiness() throws IOException, CoreException {
        if(business == null){
            business = new Business();
        }
        return business;
    }

    private Business() throws IOException, CoreException {
        logger.info("Business Initialization started");
        properties = getPropertiesFile(PROPERTY_FILE_NAME);
        dbAccess = new DBAccess(getProperty(DB_PROPERTY_NAME));
        logger.info("Business Initialization ended successfully");
    }


    public Properties getPropertiesFile(String file) throws IOException {
        Properties properties = new Properties();
        String propFileName = file;

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        if (inputStream != null) {
            properties.load(inputStream);
        } else {
            throw new FileNotFoundException("Property file '" + propFileName + "' not found in the classpath. Could not initiate Business Module.");
        }
        return properties;
    }

    public String getProperty(String propName){
        if(properties == null)
            return "";
        return properties.getProperty(propName);
    }

    public DBAccess getDbAccess() {
        return dbAccess;
    }
}
