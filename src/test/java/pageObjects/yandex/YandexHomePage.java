package pageObjects.yandex;

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
public class YandexHomePage {

    private WebDriver driver;

    @FindBy(xpath = "//title['Яндекс']")
    private WebElement title;

    @FindBy(xpath = "//a[@href='https://mail.yandex.ru']")
    private WebElement buttonMail;

    private By userPicture = By.ByClassName.xpath("//div[@class='mail-User-Picture js-user-picture']");

    public YandexHomePage(WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//title['Яндекс']")));
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickButtonMail() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(buttonMail)).click();
    }

    public void checkLogoutOfMail() {
        Assert.assertTrue(new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated(userPicture)));
    }
}