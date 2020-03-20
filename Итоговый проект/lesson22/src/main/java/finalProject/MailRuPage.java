package finalProject;

import finalProject.timeouts.ParseRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailRuPage {
    private WebDriver driver;
    private static final String TIMEOUT_NAME = "Long timeout";
    private static final int TIMEOUT = ParseRunner.readTimeouts().stream().filter(timeout -> timeout
            .getName().equals(TIMEOUT_NAME)).findFirst().get().getLength();
    private static final String LOGIN_FIELD_ID = "mailbox:login";
    private static final String PASSWORD_FIELD_ID = "mailbox:password";
    private static final String COMPOSE_MAIL_BUTTON_LINK = "(//span[contains(@class,'compose-button')])[3]";
    private static final String TO_WHOM_BUTTON_LINK = "(//button[@type='button']//div[contains(text(),'Кому')])[1]";
    private static final String FIRST_ADDRESSEE_CHOICE_LINK = "(//div[@class='b-userpic b-userpic_small'])[1]";
    private static final String ADD_ADDRESSEE_BUTTON_LINK = "//span[@class='button2__txt' and text()='Добавить']";
    private static final String SEND_BUTTON_LINK = "//span[@class='button2__txt' and text()='Отправить']";
    private static final String SEND_EMPTY_BUTTON_LINK = "//span[@style='visibility: visible;' and text()='Отправить']";
    private static final String SENT_MESSAGE_NOTIFICATION_LINK = "//span[text()='отправлено']";
    private static final String LOGOUT_BUTTON_LINK = "//a[@id='PH_logoutLink']";
    private static final String FIRST_MAIL_LINK = "(//div[@class='llc__container'])[1]";
    private static final String SECOND_MAIL_LINK = "(//div[@class='llc__container'])[2]";
    private static final String SPAM_BUTTON_LINK = "//div[contains(@class,'portal-menu-element_spam')]";
    private static final String MOVED_TO_SPAM_NOTIFICATION_LINK = "//span[contains(text(),'Перемещено в спам')]";
    private static final String SPAM_FOLDER_BUTTON_LINK = "//a[@href='/spam/']//div[@class='nav__folder']";
    private static final String NOT_SPAM_BUTTON_LINK = "//span[contains(@class,'spam-off')]";
    private static final String MOVED_TO_INBOX_NOTIFICATION_LINK = "//span[contains(text(),'Перемещено в папку «Входящие»')]";
    private static final String DELETE_BUTTON_LINK = "//div[contains(@class,'portal-menu-element_remove')]";
    private static final String MOVED_TO_TRASH_NOTIFICATION_LINK = "//span[contains(text(),'Перемещено в папку «Корзина»')]";
    private static final String TRASH_FOLDER_BUTTON_LINK = "//a[@href='/trash/']//div[@class='nav__folder']";
    private static final String MOVE_TO_FOLDER_BUTTON_LINK = "//div[contains(@class,'portal-menu-element_move')]";
    private static final String INBOX_FOLDER_TOP_BUTTON_LINK = "(//div[contains(text(),'Входящие')])[1]";
    private static final String SENT_FOLDER_BUTTON_LINK = "//a[@href='/sent/']//div[@class='nav__folder']";
    private static final String FIRST_MAIL_FLAG_CHECKBOX_LINK = "(//div[@class='llc__item llc__item_flag'])[1]";
    private static final String FILTER_BUTTON_LINK = "(//div[contains(@class, 'filters-control')])[1]";
    private static final String WITH_FLAG_BUTTON_LINK = "//span[contains(text(),'С флажком')]";
    private static final String SELECT_ALL_BUTTON_LINK = "//span[contains(@class,'select-all')]";
    private static final String CONFIRM_DELETING_BUTTON_LINK = "//div[contains(@class,'submit-button')]";

    @FindBy(id = LOGIN_FIELD_ID)
    private WebElement loginField;

    @FindBy(id = PASSWORD_FIELD_ID)
    private WebElement passwordField;

    @FindBy(xpath = COMPOSE_MAIL_BUTTON_LINK)
    private WebElement composeMailButton;

    @FindBy(xpath = TO_WHOM_BUTTON_LINK)
    private WebElement toWhomButton;

    @FindBy(xpath = FIRST_ADDRESSEE_CHOICE_LINK)
    private WebElement firstAddresseeChoice;

    @FindBy(xpath = ADD_ADDRESSEE_BUTTON_LINK)
    private WebElement addAddresseeButton;

    @FindBy(xpath = SEND_BUTTON_LINK)
    private WebElement sendButton;

    @FindBy(xpath = SEND_EMPTY_BUTTON_LINK)
    private WebElement sendEmptyButton;

    @FindBy(xpath = SENT_MESSAGE_NOTIFICATION_LINK)
    private WebElement sentMessageNotification;

    @FindBy(xpath = LOGOUT_BUTTON_LINK)
    private WebElement logoutButton;

    @FindBy(xpath = FIRST_MAIL_LINK)
    private WebElement firstMail;

    @FindBy(xpath = SECOND_MAIL_LINK)
    private WebElement secondMail;

    @FindBy(xpath = SPAM_BUTTON_LINK)
    private WebElement spamButton;

    @FindBy(xpath = MOVED_TO_SPAM_NOTIFICATION_LINK)
    private WebElement movedToSpamNotification;

    @FindBy(xpath = SPAM_FOLDER_BUTTON_LINK)
    private WebElement spamFolderButton;

    @FindBy(xpath = NOT_SPAM_BUTTON_LINK)
    private WebElement notSpamButton;

    @FindBy(xpath = MOVED_TO_INBOX_NOTIFICATION_LINK)
    private WebElement movedToInboxNotification;

    @FindBy(xpath = DELETE_BUTTON_LINK)
    private WebElement deleteButton;

    @FindBy(xpath = MOVED_TO_TRASH_NOTIFICATION_LINK)
    private WebElement movedToTrashNotification;

    @FindBy(xpath = TRASH_FOLDER_BUTTON_LINK)
    private WebElement trashFolderButton;

    @FindBy(xpath = MOVE_TO_FOLDER_BUTTON_LINK)
    private WebElement moveToFolderButton;

    @FindBy(xpath = INBOX_FOLDER_TOP_BUTTON_LINK)
    private WebElement inboxFolderTopButton;

    @FindBy(xpath = SENT_FOLDER_BUTTON_LINK)
    private WebElement sentFolderButton;

    @FindBy(xpath = FIRST_MAIL_FLAG_CHECKBOX_LINK)
    private WebElement firstMailFlagCheckbox;

    @FindBy(xpath = FILTER_BUTTON_LINK)
    private WebElement filterButton;

    @FindBy(xpath = WITH_FLAG_BUTTON_LINK)
    private WebElement withFlagButton;

    @FindBy(xpath = SELECT_ALL_BUTTON_LINK)
    private WebElement selectAllButton;

    @FindBy(xpath = CONFIRM_DELETING_BUTTON_LINK)
    private WebElement confirmDeletingButton;

    public MailRuPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterLogin(String login) {
        submitText(loginField, login);
    }

    public void enterPassword(String password) {
        submitText(passwordField, password);
    }

    public void logOut() {
        clickElement(logoutButton, LOGOUT_BUTTON_LINK);
    }

    public void startComposing() {
        clickElement(composeMailButton, COMPOSE_MAIL_BUTTON_LINK);
    }

    public void getAddresseeList() {
        clickElement(toWhomButton, TO_WHOM_BUTTON_LINK);
    }

    public void chooseFirstAddressee() {
        clickElement(firstAddresseeChoice, FIRST_ADDRESSEE_CHOICE_LINK);
        clickElement(addAddresseeButton, ADD_ADDRESSEE_BUTTON_LINK);
    }

    public void sendMessage() {
        clickElement(sendButton, SEND_BUTTON_LINK);
    }

    public void sendEmptyMessage() {
        clickElement(sendEmptyButton, SEND_EMPTY_BUTTON_LINK);
    }

    public boolean getMessageNotification() {
        return isVisible(sentMessageNotification, SENT_MESSAGE_NOTIFICATION_LINK);
    }

    public void chooseFirstMail() {
        clickElement(firstMail, FIRST_MAIL_LINK);
    }

    public void moveToSpam() {
        clickElement(spamButton, SPAM_BUTTON_LINK);
    }

    public boolean getMovedToSpamNotification() {
        return isVisible(movedToSpamNotification, MOVED_TO_SPAM_NOTIFICATION_LINK);
    }

    public void goToSpam() {
        clickElement(spamFolderButton, SPAM_FOLDER_BUTTON_LINK);
    }

    public void moveSpamToInbox() {
        clickElement(notSpamButton, NOT_SPAM_BUTTON_LINK);
    }

    public boolean getMovedToInboxNotification() {
        return isVisible(movedToInboxNotification, MOVED_TO_INBOX_NOTIFICATION_LINK);
    }

    public void deleteMail() {
        clickElement(deleteButton, DELETE_BUTTON_LINK);
    }

    public boolean getMovedToTrashNotification() {
        return isVisible(movedToTrashNotification, MOVED_TO_TRASH_NOTIFICATION_LINK);
    }

    public void goToTrash() {
        clickElement(trashFolderButton, TRASH_FOLDER_BUTTON_LINK);
    }

    public void clickMoveToFolderButton() {
        clickElement(moveToFolderButton, MOVE_TO_FOLDER_BUTTON_LINK);
    }

    public void moveToInbox() {
        clickElement(inboxFolderTopButton, INBOX_FOLDER_TOP_BUTTON_LINK);
    }

    public void goToSent() {
        clickElement(sentFolderButton, SENT_FOLDER_BUTTON_LINK);
    }

    public void clickFlagCheckbox() {
        clickElement(firstMailFlagCheckbox, FIRST_MAIL_FLAG_CHECKBOX_LINK);
    }

    public void clickFilterButton() {
        clickElement(filterButton, FILTER_BUTTON_LINK);
    }

    public void filterFlaggedMails() {
        clickElement(withFlagButton, WITH_FLAG_BUTTON_LINK);
    }

    public boolean isSecondMailPresent() {
        return isVisible(secondMail, SECOND_MAIL_LINK);
    }

    public boolean isFirstMailPresent() {
        return isVisible(firstMail, FIRST_MAIL_LINK);
    }

    public void selectAllMails() {
        clickElement(selectAllButton, SELECT_ALL_BUTTON_LINK);
    }

    public void confirmDeleting() {
        clickElement(confirmDeletingButton, CONFIRM_DELETING_BUTTON_LINK);
    }

    public void submitText(WebElement webElement, String text) {
        webElement = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.visibilityOf(webElement));
        webElement.clear();
        webElement.sendKeys(text);
        webElement.submit();
    }

    public void clickElement(WebElement webElement, String xpath) {
        webElement = (new WebDriverWait(driver, TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        webElement.click();
    }

    public boolean isVisible(WebElement webelement, String xpath) {
        webelement = (new WebDriverWait(driver, TIMEOUT)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(xpath)));
        return webelement.isDisplayed();
    }
}