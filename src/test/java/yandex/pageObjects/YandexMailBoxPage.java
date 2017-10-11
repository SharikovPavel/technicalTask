package yandex.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sharikov Pavel on 11.10.2017.
 */
public class YandexMailBoxPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div[2]")
    private WebElement SecondMessageIncomingBlock;

    @FindBy(xpath = "//span[@class='mail-Message-Sender-Email mail-ui-HoverLink-Content']")
    private WebElement sender;

    @FindBy(xpath = "//div/span[@class='mail-Message-Toolbar-Subject-Wrapper']")
    private WebElement subject;

    @FindBy(xpath = "//td[contains(text(),'Здравствуйте!')]")
    private WebElement message;

    @FindBy(xpath = "//div[@class='mail-User-Picture js-user-picture']")
    private WebElement userPicture;

    @FindBy(xpath = "//a[@data-metric='Меню сервисов:Выход']")
    private WebElement buttonLogout;

    public YandexMailBoxPage(WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title[contains(text(),'Входящие — Яндекс.Почта')]")));
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void openMessage() {
        SecondMessageIncomingBlock.click();
    }

    public void checkSender(String expectedSender) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(sender));
            Assert.assertTrue(sender.getAttribute("title").equals(expectedSender));
        } catch (Exception e) {
            e.getCause().getMessage();
        }
    }

    public void checkSubject(String expectedSubject) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(subject));
            Assert.assertTrue(subject.getText().contains(expectedSubject));
        } catch (Exception e) {
            System.err.println("Совпадений в тексте не обнаружено");
            e.getCause().getMessage();
        }

    }

    public void checkMessage(String expectedMessage) {
        try {
            new WebDriverWait(driver, 10)
                    .until(ExpectedConditions.visibilityOf(message));
            Assert.assertTrue(message.getText().contains(expectedMessage));
        } catch (Exception e) {
            e.getCause().getMessage();
        }
    }

    public void logoutUser() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(userPicture)).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(buttonLogout)).click();
    }

    /**
     * Метод позволяющий выбрать номер письма, которое необходимо открыть
     *
     * @param numberMessage номер письма в списке
     */
    public void openMessage(int numberMessage) {
        try {
            driver.findElement(By
                    .xpath("//div[@class='ns-view-container-desc mail-MessagesList js-messages-list']/div[" +
                            numberMessage + "]")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}