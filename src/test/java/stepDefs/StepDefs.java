package stepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.ru.Допустим;
import cucumber.api.java.ru.Когда;
import helperClasses.SentMail;
import pageObjects.yandex.YandexHomePage;
import pageObjects.yandex.YandexLoginMailPage;
import pageObjects.yandex.YandexMailBoxPage;

import javax.mail.MessagingException;

import static helperClasses.Hooks.*;

/**
 * Created by Sharikov Pavel on 13.10.2017.
 */
public class StepDefs {

    //    Шаги для новой фичи, с изменением всех параметров теста в feature файле

    @Допустим("^Подготавливаем и отправляем тестовое письмо адресату \"([^\"]*)\" \"([^\"]*)\"$")
    public void sentMessage(String arg1, String arg2) throws MessagingException {
        SentMail.sendMessageToMailBox(arg1, arg2);
    }

    @Когда("^Пользователь открывает ранее отправленное письмо$")
    public void userOpenPreviouslyMessage() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().userOpenPreviouslySentMessage(
                props.getProperty("yandexSentEmail"), stash.get("subjectMessage"));
    }

    @Когда("^Пользователь открывает ранее отправленное письмо \"([^\"]*)\" \"([^\"]*)\"$")
    public void userOpenPreviouslyMessage(String arg1, String arg2)
            throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().userOpenPreviouslySentMessage(
                arg1, arg2);
    }

    @Когда("^Пользователь проверяет отправителя письма$")
    public void userCheckSender() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().checkSender(props.getProperty("yandexSentEmail"));
    }

    @Когда("^Пользователь проверяет тему письма$")
    public void userCheckSubject() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().checkSubject(stash.get("subjectMessage"));
    }

    @Когда("^Пользователь проверяет содержимое письма$")
    public void userCheckMessage() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().checkMessage(stash.get("bodyMessage"));
    }

    @Допустим("^Пользователь заходит на стартовую страницу поисковика yandex$")
    public void openHomePageYandex() throws Throwable {
        try {
            driver.get(props.getProperty("yandexHomePage"));
        } catch (PendingException e) {
            throw new PendingException("Все плохо");
        }
    }

    @Когда("^Пользователь нажимает кнопку перехода в почту$")
    public void userOpenMail() {
        YandexHomePage home = new YandexHomePage(driver);
        home.clickButtonMail();
    }

    @Когда("^Пользователь вводит логин и пароль на странице авторизации почты$")
    public void userOpenLoginMailPage() {
        YandexLoginMailPage loginMailPage = new YandexLoginMailPage(driver);
        loginMailPage.userLoginInMail(props.getProperty("yandexLogin"),
                props.getProperty("yandexPassword"));
    }

    @Когда("^Пользователь совершает выход из аккаунта почты$")
    public void userLogoutMail() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().logoutUser();
    }

    @Когда("^Пользователь совершает проверку выхода из почты$")
    public void userCheckLogout() throws IllegalAccessException, InstantiationException {
        YandexHomePage homePage = new YandexHomePage(driver);
        homePage.checkLogoutOfMail();
    }

    @Когда("^Пользователь удаляет письмо$")
    public void deleteMessageOfMailBox() throws IllegalAccessException, InstantiationException {
        YandexMailBoxPage.class.newInstance().deleteMessage();
        driver.switchTo().defaultContent();
    }
}