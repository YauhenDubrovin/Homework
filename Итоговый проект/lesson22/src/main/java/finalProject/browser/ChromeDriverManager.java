package finalProject.browser;

import finalProject.configuration.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends DriverManager {
    private ChromeDriverService chromeDriverService;
    private static ChromeDriverManager instance;

    private ChromeDriverManager() {}

    public static ChromeDriverManager getInstance() {
        if (instance == null) {
            instance = new ChromeDriverManager();
        }
        return instance;
    }



    @Override
    public void startService() {
        WebDriverManager.chromedriver().setup();
        if (null == chromeDriverService) {
            try {
                chromeDriverService = new ChromeDriverService.Builder()
                        .usingAnyFreePort()
                        .build();
                chromeDriverService.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stopService() {
        if (null != chromeDriverService && chromeDriverService.isRunning())
            chromeDriverService.stop();
    }

    @Override
    public void createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");
        options.setHeadless(Configuration.getHeadlessOption());
        driver = new ChromeDriver(chromeDriverService, options);
    }
}
