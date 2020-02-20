package mailRuPage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class Pages {
    private final String mailboxPassword = "mailbox:password";
    private final String logOutLink = "//a[@id=\"PH_logoutLink\"]";
    private final String firstMailLink = "(//div[@class=\"llc__item llc__item_title\"])[1]";
    private final String spamLink = "//span[@title=\"Спам\"]";
    private final String allFoldersLink = "//a[@title=\"Все папки\"]";
    private final String spamFolderLink = "(//a[@href=\"/spam/\"])[2]//div[contains(text(),'Спам')]";
    private final String spamMailLink = "//div[@class=\"llc__item llc__item_title\"]";
    private final String notSpamLink = "//span[@title=\"Не спам\"]";
    private final String composeMessageLink = "//span[@class=\"compose-button__wrapper\"]";
    private final String toWhomLink = "(//button[@type=\"button\"]//div[contains(text(),'Кому')])[1]";
    private final String firstAddresseeChoice = "(//div[@class=\"b-userpic b-userpic_small\"])[1]";
    private final String secondAddresseeChoice = "(//div[@class=\"b-userpic b-userpic_small\"])[2]";
    private final String firstFlagLink = "(//div[@class=\"llc__item llc__item_flag\"])[1]";
    private final String secondFlagLink = "(//div[@class=\"llc__item llc__item_flag\"])[2]";
    private final String thirdFlagLink = "(//div[@class=\"llc__item llc__item_flag\"])[3]";
    WebDriver driver;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(xpath = logOutLink)
    private WebElement logOutButton;

    @FindBy(id = mailboxPassword)
    private WebElement passwordField;

    @FindBy(xpath = firstMailLink)
    private WebElement firstMail;

    @FindBy(xpath = spamLink)
    private WebElement spamButton;

    @FindBy(xpath = allFoldersLink)
    private WebElement allFoldersButton;

    @FindBy(xpath = spamFolderLink)
    private WebElement spamFolder;

    @FindBy(xpath = spamMailLink)
    private WebElement spamMail;

    @FindBy(xpath = notSpamLink)
    WebElement notSpamButton;

    @FindBy(xpath = composeMessageLink)
    WebElement composeMessageButton;

    @FindBy(xpath = toWhomLink)
    WebElement toWhomButton;

    @FindBy(xpath = firstAddresseeChoice)
    WebElement firstAddressee;

    @FindBy(xpath = secondAddresseeChoice)
    WebElement secondAddressee;

    @FindBy(xpath = "//span[@class=\"button2__txt\" and text()='Добавить']")
    WebElement addButton;

    @FindBy(xpath = "//span[@class=\"button2__txt\" and text()='Отправить']")
    WebElement sendButton;

    @FindBy(xpath = "//span[@style=\"visibility: visible;\" and text()='Отправить']")
    WebElement sendEmptyButton;

    @FindBy(xpath = firstFlagLink)
    WebElement firstFlag;

    @FindBy(xpath = secondFlagLink)
    WebElement secondFlag;

    @FindBy(xpath = thirdFlagLink)
    WebElement thirdFlag;


    public Pages(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logIn(String login, String password) {
        loginField.clear();
        loginField.sendKeys(login);
        loginField.submit();
//        passwordField = (new WebDriverWait(driver, 10))
//                .until(ExpectedConditions.visibilityOf(passwordField));
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.pollingEvery(Duration.ofMillis(250));
        wait.withTimeout(Duration.ofSeconds(10));
        wait.ignoring(NoSuchElementException.class);
        Function<WebDriver, WebElement> function = driver -> {
            WebElement passwordField = driver.findElement(By.id(mailboxPassword));
            return passwordField;
        };
        wait.until(function);
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void logOut() {
        logOutButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(logOutLink)));
        logOutButton.click();
    }

    public void moveToSpam() {
        firstMail = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(firstMailLink)));
        firstMail.click();
        spamButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(spamLink)));
        spamButton.click();
    }

    public void moveFromSpam() {
        allFoldersButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(allFoldersLink)));
        allFoldersButton.click();
        spamFolder = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(spamFolderLink)));
        spamFolder.click();
        spamMail = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(spamMailLink)));
        spamMail.click();
        notSpamButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(notSpamLink)));
        notSpamButton.click();
    }

    public void sendMessage() {
        composeMessageButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(composeMessageLink)));
        composeMessageButton.click();
        toWhomButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(toWhomLink)));
        toWhomButton.click();
        firstAddressee = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(firstAddresseeChoice)));
        firstAddressee.click();
        secondAddressee = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(secondAddresseeChoice)));
        secondAddressee.click();
        addButton.click();
        sendButton.click();
        sendEmptyButton.click();
    }

    public void flagMessages() {
        firstFlag = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(firstFlagLink)));
        firstFlag.click();
        secondFlag = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(secondFlagLink)));
        secondFlag.click();
        thirdFlag = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(thirdFlagLink)));
        thirdFlag.click();
    }
}
