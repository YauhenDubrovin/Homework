package mailRu;

import com.applitools.eyes.TestResultContainer;
import com.applitools.eyes.TestResultsSummary;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MailRuTest {
    private WebDriver driver;
    private MailRuSendMessage mailRuSendMessage;
    private static final String MAIN_URL = "http://mail.ru";
    private static final String LOGIN = "yauhen.dubrovin";
    private static final String PASSWORD = "ABC13579!";

    public MailRuTest() {
        System.setProperty("webdriver.chrome.driver", "D:/installation/chromedriver.exe");
        driver = new ChromeDriver();
        mailRuSendMessage = new MailRuSendMessage(driver);
    }

    @Given("^I am on the main page$")
    public void goToMainPage() {
        driver.get(MAIN_URL);
        driver.manage().window().maximize();
    }

    @When("^I enter a user name$")
    public void enterUserName() {
        mailRuSendMessage.enterLogin(LOGIN);
    }

    @When("^I enter a password and get to the inbox folder$")
    public void enterPassword() {
        mailRuSendMessage.enterPassword(PASSWORD);
    }

    @When("^I click \"compose a mail\" button$")
    public void startComposingMail() {
        mailRuSendMessage.startComposing();
    }

    @When("^I click \"to whom\" button$")
    public void goToAddresseeList() {
        mailRuSendMessage.getAddresseeList();
    }

    @When("^I choose the first email address in the list$")
    public void chooseAddressee() {
        mailRuSendMessage.chooseFirstAddressee();
    }

    @When("^I click \"send\" button$")
    public void sendNewMessage() {
        mailRuSendMessage.sendMessage();
    }

    @When("^I click \"confirm sending the empty mail\"$")
    public void sendEmptyMessage() {
        mailRuSendMessage.sendEmptyMessage();
        mailRuSendMessage.getMessageNotification();
    }

    @Then("^The mail is sent$")
    public void checkScreenshots() {
        driver.quit();
        mailRuSendMessage.getEyes().abortIfNotClosed();
        TestResultsSummary allTestResults = mailRuSendMessage.getEyesRunner().getAllTestResults();
        TestResultContainer[] testResultContainers = allTestResults.getAllResults();
        for (TestResultContainer result : testResultContainers) {
            Assert.assertTrue(result.getTestResults().isPassed());
        }
    }
}
