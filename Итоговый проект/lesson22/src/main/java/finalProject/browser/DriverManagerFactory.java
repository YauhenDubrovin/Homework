package finalProject.browser;

public class DriverManagerFactory {
    public static DriverManager getManager(DriverType type) {
        DriverManager driverManager;
        switch (type) {
            case IE:
                driverManager = new IEDriverManager();
                break;
            default:
                driverManager = ChromeDriverManager.getInstance();
        }
        return driverManager;
    }
}
