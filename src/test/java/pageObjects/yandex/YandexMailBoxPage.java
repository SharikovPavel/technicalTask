package pageObjects.yandex;

import helperClasses.Hooks;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.qatools.allure.Allure;
import ru.yandex.qatools.allure.events.TestCaseFailureEvent;
import gherkin.formatter.model.Result;

/**
 * Created by Sharikov Pavel on 11.10.2017.
 */
public class YandexMailBoxPage extends Hooks {

    Logger LOG = LoggerFactory.getLogger(YandexMailBoxPage.class);
    private static final String FAILED = "failed";
    private static final String SKIPPED = "skipped";
    private static final String PASSED = "passed";
    private WebDriver webDriver;

    @FindBy(xpath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div[last()]")
    private WebElement lastMessageOnThePage;

    @FindBy(xpath = "//span[@class='mail-Message-Sender-Email mail-ui-HoverLink-Content']")
    private WebElement sender;

    @FindBy(xpath = "//div/span[@class='mail-Message-Toolbar-Subject-Wrapper']")
    private WebElement subject;

    @FindBy(xpath = "//div[@class='mail-Message-Body-Content mail-Message-Body-Content_plain']")
    private WebElement message;

    @FindBy(xpath = "//div[@class='mail-User-Picture js-user-picture']")
    private WebElement userPicture;

    @FindBy(xpath = "//a[@data-metric='Меню сервисов:Выход']")
    private WebElement buttonLogout;

    @FindBy(xpath = "//div[@title='Удалить (Delete)']")
    private WebElement deleteButon;

    public YandexMailBoxPage(WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Входящие — Яндекс.Почта')]")));
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    public YandexMailBoxPage() {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openLastMessageOnThePage() {
        lastMessageOnThePage.click();
    }

    public void userOpenPreviouslySentMessage(String email, String subjectMessage) {
        driver.findElements(By.xpath("//div[@class='mail-MessageSnippet-Content']" +
                "//span[@title='" + email + "']/../../..//" +
                "span[@title='" + subjectMessage + "']")).get(0).click();
    }

    public void checkSender(String expectedSender) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(sender));
            Assert.assertTrue(sender.getAttribute("title").equals(expectedSender));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkSubject(String expectedSubject) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(subject));
            Assert.assertTrue(subject.getText().contains(expectedSubject));
        } catch (Exception e) {
            Result result = new Result(FAILED, 1L, "Все плохо");
            e.printStackTrace();
            Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(result.getError()));
        }
    }

    /**
     * Проверка совпадения отправленного письма, с принятым
     *
     * @param expectedMessage письмо, которое мы отправили
     */
    public void checkMessage(String expectedMessage) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(message));
            Assert.assertTrue(message.getText().contains(expectedMessage));
        } catch (Exception e) {
            Result result = new Result(FAILED, 10L, "Все плохо");
            e.printStackTrace();
            Allure.LIFECYCLE.fire(new TestCaseFailureEvent().withThrowable(result.getError()));
        }
    }

    public void deleteMessage() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(deleteButon)).click();
    }

    public void logoutUser() {
        try {
            driver.switchTo().defaultContent();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.elementToBeClickable(userPicture)).click();
            userPicture.click();
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(buttonLogout)).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}