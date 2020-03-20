package finalProject.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;

public class IEDriverManager extends DriverManager {
    private InternetExplorerDriverService internetExplorerDriverService;

    @Override
    public void startService() {
        WebDriverManager.iedriver().setup();
        if (null == internetExplorerDriverService) {
            try {
                internetExplorerDriverService = new InternetExplorerDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                internetExplorerDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != internetExplorerDriverService && internetExplorerDriverService.isRunning())
            internetExplorerDriverService.stop();
    }

    @Override
    public void createDriver() {
        driver = new InternetExplorerDriver(internetExplorerDriverService);
    }
}
