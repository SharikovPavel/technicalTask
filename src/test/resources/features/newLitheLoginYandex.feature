#language:ru
@yandex
Функционал: {Логин и проверка почты в yandex}

  @newLithelLoginYandex
  Сценарий: {newLithelLoginYandex}
    * Подготавливаем и отправляем тестовое письмо адресату "Это супер тема" "Привет, как дела?"
    * Пользователь заходит на стартовую страницу поисковика yandex
    * Пользователь нажимает кнопку перехода в почту
    * Пользователь вводит логин и пароль на странице авторизации почты
    * Пользователь открывает ранее отправленное письмо "bumaga.medicor@yandex.ru" "Это супер тема"
    * Пользователь проверяет отправителя письма
    * Пользователь проверяет тему письма
    * Пользователь проверяет содержимое письма
    * Пользователь удаляет письмо
    * Пользователь совершает выход из аккаунта почты
    * Пользователь совершает проверку выхода из почты