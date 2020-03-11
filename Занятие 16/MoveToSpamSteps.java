package mailRu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MoveToSpamSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = ConnectionPool.getDataBaseUsers().get(0).getLogin();
    private static final String PASSWORD = ConnectionPool.getDataBaseUsers().get(0).getPassword();
    private MailRuPage mailRuPage;
    private WebDriver driver;

    public MoveToSpamSteps() {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");
        driver = new ChromeDriver();
        mailRuPage = new MailRuPage(driver);
    }

    @After
    public void closeWindow() {
        mailRuPage.logOut();
        driver.quit();
    }

    @Given("^I am in inbox folder$")
    public void goToInboxFolder() {
        driver.get(MAIN_URL);
        driver.manage().window().maximize();
        mailRuPage.enterLogin(LOGIN);
        mailRuPage.enterPassword(PASSWORD);
    }

    @When("^I open a mail and press spam button$")
    public void sendToSpam() {
        mailRuPage.chooseFirstMail();
        mailRuPage.moveFirstMailToSpam();
    }

    @Then("^mail is moved to spam folder$")
    public void spamFolderIsNotEmpty() {
        mailRuPage.goToAllFoldersList();
        mailRuPage.goToSpamFolder();
        Assert.assertTrue(mailRuPage.isSpamFolderNotEmpty());
    }
}
