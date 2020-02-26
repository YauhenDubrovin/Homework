package mailRu;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SpamSteps {
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "yauhen.dubrovin";
    private static final String PASSWORD = "ABC13579!";
    private Page page;
    private WebDriver driver;

    public SpamSteps() {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");
        driver = new ChromeDriver();
        page = new Page(driver);
    }

    @After
    public void closeWindow() {
        page.logOut();
        driver.quit();
    }

    @Given("^I am in inbox folder$")
    public void goToInboxFolder() {
        driver.get(MAIN_URL);
        driver.manage().window().maximize();
        page.logIn(LOGIN, PASSWORD);
    }

    @When("^I open a mail and press spam button$")
    public void sendToSpam() {
        page.moveToSpam();
    }

    @Then("^Mail is moved to spam folder$")
    public void spamFolderIsNotEmpty() {
        page.goToSpamFolder();
        Assert.assertTrue(page.isSpamFolderNotEmpty());
    }

    @Given("^I am in spam folder$")
    public void goToSpamFolder() {
        goToInboxFolder();
        page.goToSpamFolder();
    }

    @When("^I open a mail and press not-spam button$")
    public void sendFromSpam() {
        page.moveFromSpam();
    }

    @Then("^Mail is moved from spam folder$")
    public void spamFolderIsEmpty() {
        Assert.assertTrue(page.isSpamFolderEmpty());
    }
}
