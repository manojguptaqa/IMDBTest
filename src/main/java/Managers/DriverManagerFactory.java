package Managers;

import Enum.DriverType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type) {

        DriverManager driverManager;

        switch (type) {
            case CHROME:
                driverManager = new ChromeDriverManager();
                break;
            case FIREFOX:
               driverManager = new ChromeDriverManager(); // TBD
                break;
            default:
                driverManager = new ChromeDriverManager(); //TBD
                break;
        }
        return driverManager;

    }
}