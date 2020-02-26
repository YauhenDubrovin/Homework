package mailRu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    private WebDriver driver;
    private static final int TIMEOUT = 10;
    private static final String FIRST_MAIL_LINK = "(//div[@class=\"llc__item llc__item_title\"])[1]";
    private static final String SPAM_BUTTON_LINK = "//span[@title=\"Спам\"]";
    private static final String ALL_FOLDERS_LINK = "//a[@title=\"Все папки\"]";
    private static final String SPAM_FOLDER_LINK = "(//a[@href=\"/spam/\"])[2]//div[contains(text(),'Спам')]";
    private static final String LOGOUT_LINK = "//a[@id=\"PH_logoutLink\"]";
    private static final String SPAM_MAIL_TO_MOVE_LINK = "//div[@class=\"llc__item llc__item_title\"]";
    private static final String NOT_SPAM_BUTTON_LINK = "//span[@title=\"Не спам\"]";

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(xpath = FIRST_MAIL_LINK)
    private WebElement firstMail;

    @FindBy(xpath = SPAM_BUTTON_LINK)
    private WebElement spamButton;

    @FindBy(xpath = ALL_FOLDERS_LINK)
    private WebElement allFoldersButton;

    @FindBy(xpath = SPAM_FOLDER_LINK)
    private WebElement spamFolder;

    @FindBy(xpath = "//div[@class=\"letter-list letter-list_has-letters\"]")
    private WebElement spamMail;

    @FindBy(xpath = LOGOUT_LINK)
    private WebElement logOutButton;

    @FindBy(xpath = SPAM_MAIL_TO_MOVE_LINK)
    private WebElement spamMailToMove;

    @FindBy(xpath = NOT_SPAM_BUTTON_LINK)
    private WebElement notSpamButton;

    @FindBy(xpath = "//div[@class=\"dataset__empty\"]")
    private WebElement isEmptySign;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logIn(String login, String password) {
        loginField = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(loginField));
        loginField.clear();
        loginField.sendKeys(login);
        loginField.submit();
        passwordField = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(passwordField));
        passwordField.clear();
        passwordField.sendKeys(password);
        passwordField.submit();
    }

    public void logOut() {
        logOutButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
        logOutButton.click();
    }

    public void moveToSpam() {
        firstMail = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(FIRST_MAIL_LINK)));
        firstMail.click();
        spamButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SPAM_BUTTON_LINK)));
        spamButton.click();
    }

    public void goToSpamFolder() {
        allFoldersButton = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(ALL_FOLDERS_LINK)));
        allFoldersButton.click();
        spamFolder = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SPAM_FOLDER_LINK)));
        spamFolder.click();
    }

    public boolean isSpamFolderNotEmpty() {
        spamMail = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(spamMail));
        return spamMail.isDisplayed();
    }

    public void moveFromSpam() {
        spamMailToMove = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(SPAM_MAIL_TO_MOVE_LINK)));
        spamMailToMove.click();
        notSpamButton = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(NOT_SPAM_BUTTON_LINK)));
        notSpamButton.click();
    }

    public boolean isSpamFolderEmpty() {
        isEmptySign = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(isEmptySign));
        return isEmptySign.isDisplayed();
    }
}
