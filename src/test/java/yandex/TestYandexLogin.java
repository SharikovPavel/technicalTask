package yandex;

import helperClasses.BaseTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Title;
import yandex.pageObjects.YandexHomePage;
import yandex.pageObjects.YandexLoginMailPage;
import yandex.pageObjects.YandexMailBoxPage;

/**
 * Created by Sharikov Pavel on 10.10.2017.
 */
public class TestYandexLogin extends BaseTest {
    Logger LOG = Logger.getLogger(TestYandexLogin.class);

    @Test
    @Title("Логин в яндекс и просмотр почты")
    public void test() {
        driver.get(props.getProperty("yandexHomePage"));
        YandexHomePage home = new YandexHomePage(driver);
        home.clickButtonMail();

        YandexLoginMailPage loginMailPage = new YandexLoginMailPage(driver);
        loginMailPage.userLoginInMail(props.getProperty("yandexLogin"), props.getProperty("yandexPassword"));

        YandexMailBoxPage mailBoxPage = new YandexMailBoxPage(driver);
        mailBoxPage.openMessage(2);
        mailBoxPage.checkSender(props.getProperty("OneSenderYandex"));
        mailBoxPage.checkSubject(props.getProperty("OneSubjectYandex"));
        mailBoxPage.checkMessage(props.getProperty("message"));
        mailBoxPage.logoutUser();
    }
}