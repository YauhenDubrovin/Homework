package finalProject;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import finalProject.DataBaseConnection.ConnectionPool;
import finalProject.browser.DriverManager;
import finalProject.browser.DriverManagerFactory;
import finalProject.configuration.Configuration;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class MailRuPageTests {
    private static Logger logger = Logger.getLogger(MailRuPageTests.class);
    private static final String MAIN_URL = Configuration.getMainPageUrl();
    private static final String LOGIN = ConnectionPool.getDataBaseUsers().get(0).getLogin();
    private static final String PASSWORD = ConnectionPool.getDataBaseUsers().get(0).getPassword();
    private DriverManager driverManager;
    private WebDriver driver;
    private MailRuPage mailRuPage;

    public MailRuPageTests() {
        driverManager = DriverManagerFactory.getManager(Configuration.getDriverType());
        driver = driverManager.getDriver();
        mailRuPage = new MailRuPage(driver);
    }

    public void makeVisualTest(String testName) {
        EyesRunner runner = new ClassicRunner();
        Eyes eyes = new Eyes(runner);
        eyes.setApiKey("RvHxZxA4fOeNmUAg98BPoXgzw63onNO97ltZf4FFoh6kU110");
        eyes.open(driver, "Demo App", testName);
        eyes.setMatchLevel(MatchLevel.LAYOUT);
        eyes.checkWindow(testName);
        eyes.closeAsync();
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults();
        TestResultContainer[] testResultContainers = allTestResults.getAllResults();
        for (TestResultContainer result : testResultContainers) {
            Assert.assertTrue(result.getTestResults().isPassed());
        }
    }

    @Before
    public void signIn() {
        driver.get(MAIN_URL);
        driver.manage().window().maximize();
        mailRuPage.enterLogin(LOGIN);
        mailRuPage.enterPassword(PASSWORD);
    }

    @After
    public void signOut() {
        mailRuPage.logOut();
        driverManager.quitDriver();
    }

    @Given("^I click 'compose a mail' button$")
    public void startComposing() {
        mailRuPage.startComposing();
    }

    @When("^I click 'to whom' button$")
    public void getAddresseeList() {
        mailRuPage.getAddresseeList();
    }

    @When("^I choose the first email address in the list$")
    public void selectFirstAddressee() {
        mailRuPage.chooseFirstAddressee();
    }

    @When("^I click 'send' button$")
    public void sendMessage() {
        mailRuPage.sendMessage();
    }

    @When("^I confirm sending the empty mail$")
    public void sendEmptyMessage() {
        mailRuPage.sendEmptyMessage();
    }

    @Then("^the mail is sent$")
    public void isSent() {
        logger.info("test is being performed");
        Assert.assertTrue(mailRuPage.getMessageNotification());
        makeVisualTest("first test");
    }

    @When("^I choose the first mail$")
    public void chooseFirstMail() {
        mailRuPage.chooseFirstMail();
    }

    @When("^I click 'spam' button$")
    public void moveToSpam() {
        mailRuPage.moveToSpam();
    }

    @Then("^the mail is moved to the spam folder$")
    public void isMovedToSpam() {
        logger.info("test is being performed");
        Assert.assertTrue(mailRuPage.getMovedToSpamNotification());
        makeVisualTest("second test");
    }

    @Given("^I go to the spam folder$")
    public void goToSpam() {
        mailRuPage.goToSpam();
    }

    @When("^I click 'not spam' button$")
    public void sendFromSpamToInbox() {
        mailRuPage.moveSpamToInbox();
    }

    @Then("^the mail is moved to the inbox folder$")
    public void isMovedFromSpamToInbox() {
        logger.info("test is being performed");
        Assert.assertTrue(mailRuPage.getMovedToInboxNotification());
        makeVisualTest("third test");
    }

    @When("^I click 'delete' button$")
    public void deleteMail() {
        mailRuPage.deleteMail();
    }

    @Then("^the mail is moved to the trash folder$")
    public void isDeleted() {
        logger.info("test is being performed");
        Assert.assertTrue(mailRuPage.getMovedToTrashNotification());
        makeVisualTest("fourth test");
    }

    @Given("^I go to the trash folder$")
    public void goToTrash() {
        mailRuPage.goToTrash();
    }

    @When("^I click 'to folder' button$")
    public void goToFoldersList() {
        mailRuPage.clickMoveToFolderButton();
    }

    @When("^I choose the inbox folder from the list$")
    public void moveToInbox() {
        mailRuPage.moveToInbox();
    }

    @Given("^I go to the sent folder$")
    public void goToSent() {
        mailRuPage.goToSent();
    }

    @Given("^I put the flag on the first mail$")
    public void putFlag() {
        mailRuPage.clickFlagCheckbox();
    }

    @When("^I press 'filter' button$")
    public void clickFilterButton() {
        mailRuPage.clickFilterButton();
    }

    @When("^I choose 'with flag' option$")
    public void filterFlaggedMails() {
        mailRuPage.filterFlaggedMails();
    }

    @Then("^there is only one mail in inbox folder$")
    public void isSecondMailVisible() {
        boolean thrown = false;
        try {
            makeVisualTest("fifth test");
            mailRuPage.isSecondMailPresent();
        } catch (Exception e) {
            thrown = true;
            logger.info("exception is caught");
        }
        Assert.assertTrue(thrown);
    }

    @Given("^I unflag the first mail in inbox folder$")
    public void unflagMail() {
        mailRuPage.clickFlagCheckbox();
    }

    @Then("^there are no mails in inbox folder$")
    public void isFirstMailVisible() {
        boolean thrown = false;
        try {
            makeVisualTest("sixth test");
            mailRuPage.isFirstMailPresent();
        } catch (Exception e) {
            thrown = true;
            logger.info("exception is caught");
        }
        Assert.assertTrue(thrown);
    }

    @Given("^I press 'select all' button$")
    public void selectAllMails() {
        mailRuPage.selectAllMails();
    }

    @When("^I confirm deleting$")
    public void confirmDeleting() {
        mailRuPage.confirmDeleting();
    }
}