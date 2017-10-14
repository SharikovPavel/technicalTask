package pageObjects.yandex;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Sharikov Pavel on 11.10.2017.
 */
public class YandexLoginMailPage {
    private WebDriver driver;
    final static String TITLE = "Яндекс.Почта — бесплатная электронная почта";

    @FindBy(xpath = "//title['Яндекс.Почта — бесплатная электронная почта']")
    private WebElement titleMail;

    @FindBy(xpath = "//input[@name='login']")
    private WebElement fieldLogin;

    @FindBy(xpath = "//input[@name='passwd']")
    private WebElement fieldPassword;

    @FindBy(xpath = "//button/span[contains(text(),'Войти')]")
    private WebElement buttonLogin;

    public YandexLoginMailPage(WebDriver driver) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.titleIs(TITLE));
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void userLoginInMail(String login, String password) {
        fieldLogin.sendKeys(login);
        fieldPassword.sendKeys(password);
        buttonLogin.submit();
    }
}