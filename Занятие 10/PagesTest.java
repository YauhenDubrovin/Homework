package mailRuPage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PagesTest {

    private WebDriver driver;
    private Pages pages;

    @BeforeEach
    public void beforeMethod() {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://mail.ru");
        driver.manage().window().maximize();
        pages = new Pages(driver);
        pages.logIn("yauhen.dubrovin", "ABC13579!");
    }

    @AfterEach
    public void afterMethod() {
        pages.logOut();
        driver.quit();
    }

    @Test
    public void moveToSpamTest() {
        pages.moveToSpam();
    }

    @Test
    public void moveFromSpamTest() {
        pages.moveFromSpam();
    }

    @Test
    public void sendMessageTest() {
        pages.sendMessage();
    }

    @Test
    public void flagMessagesTest() {
        pages.flagMessages();
    }

    @Test
    public void deflagMessagesTest() {
        pages.flagMessages();
    }
}
