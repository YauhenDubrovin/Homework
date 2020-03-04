package mailRu;

import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuSendMessage {
    private WebDriver driver;
    private EyesRunner runner;
    private Eyes eyes;
    private static final int TIMEOUT = 10;
    private static final String MAILBOX_PASSWORD = "mailbox:password";
    private static final String COMPOSE_BUTTON_LINK = "//span[@class=\"compose-button__txt\"]";
    private static final String TO_WHOM_BUTTON_LINK = "(//button[@type=\"button\"]//div[contains(text(),'Кому')])[1]";
    private static final String FIRST_ADDRESSEE_CHOICE = "(//div[@class=\"b-userpic b-userpic_small\"])[1]";
    private static final String SENT_MESSAGE_NOTIFICATION_LINK = "//span[text()='отправлено']";

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = MAILBOX_PASSWORD)
    private WebElement passwordField;

    @FindBy(xpath = COMPOSE_BUTTON_LINK)
    private WebElement composeButton;

    @FindBy(xpath = "//button[@title=\"Увеличить\"]")
    private WebElement expandButton;

    @FindBy(xpath = "//button[@title=\"Уменьшить\"]")
    private WebElement narrowButton;

    @FindBy(xpath = TO_WHOM_BUTTON_LINK)
    private WebElement toWhomButton;

    @FindBy(xpath = FIRST_ADDRESSEE_CHOICE)
    private WebElement firstAddressee;

    @FindBy(xpath = "//span[@class=\"button2__txt\" and text()='Добавить']")
    private WebElement addAddresseeButton;

    @FindBy(xpath = "//span[@class=\"button2__txt\" and text()='Отправить']")
    private WebElement sendButton;

    @FindBy(xpath = "//span[@style=\"visibility: visible;\" and text()='Отправить']")
    private WebElement sendEmptyConfirmationButton;

    @FindBy(xpath = SENT_MESSAGE_NOTIFICATION_LINK)
    private WebElement sentMessageNotification;

    public MailRuSendMessage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey("RXOsbTk1040dBg1tYj699FqkuV6kZ1086Jk9LsTUbcP105AMCg110");
        eyes.open(driver, "Demo App", "Smoke Test");
        eyes.setMatchLevel(MatchLevel.LAYOUT);
    }

    public EyesRunner getEyesRunner() {
        return this.runner;
    }

    public Eyes getEyes() {
        return this.eyes;
    }

    public void enterLogin(String login) {
        loginField.clear();
        eyes.checkWindow("Main page");
        loginField.sendKeys(login);
        loginField.submit();
    }

    public void enterPassword(String password) {
        passwordField = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        eyes.checkWindow("Password page");
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void startComposing() {
        composeButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(COMPOSE_BUTTON_LINK)));
        eyes.checkWindow("Inbox folder");
        composeButton.click();
    }

    public void getAddresseeList() {
        expandButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(expandButton));
        expandButton.click();
        eyes.checkWindow("Compose mail window");
        toWhomButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(TO_WHOM_BUTTON_LINK)));
        toWhomButton.click();
    }

    public void chooseFirstAddressee() {
        firstAddressee = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_ADDRESSEE_CHOICE)));
        eyes.checkWindow("List of contacts");
        firstAddressee.click();
        addAddresseeButton.click();
    }

    public void sendMessage() {
        narrowButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(narrowButton));
        narrowButton.click();
        eyes.checkWindow("Message ready for sending");
        sendButton.click();
    }

    public void sendEmptyMessage() {
        eyes.checkWindow("Sending empty message warning");
        sendEmptyConfirmationButton.click();
    }

    public void getMessageNotification() {
        sentMessageNotification = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SENT_MESSAGE_NOTIFICATION_LINK)));
        eyes.checkWindow("Sent message notification");
        eyes.closeAsync();
    }
}
