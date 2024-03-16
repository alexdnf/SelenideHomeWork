package ru.netology.selenide;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class CardDeliveryTests {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    @Test
    public void shouldSendCorrectFormTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!")).shouldHave(text(neededDate));

    }

    @Test
    public void shouldSendCorrectFormDoubleSurnameTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов-Петров Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15)).shouldHave(text("Успешно!")).shouldHave(text(neededDate));
    }

    @Test
    public void wrongDateTest() {

        String neededDate = generateDate(0);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    public void wrongDateTomorrowTest() {

        String neededDate = generateDate(1);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    public void wrongDate2DayTest() {

        String neededDate = generateDate(2);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='date'] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }


    @Test
    public void wrongCityTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Павлово");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    public void wrongCityNullTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void wrongCityLatinTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void wrongCityNumbersTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("12345");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    public void wrongPhoneNumberLettersTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("qwerty");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void wrongPhoneNumberSymbolsTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+7999999-999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void wrongPhoneNumberLessSymbolsTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+7999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void wrongPhoneNumberIsNullTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void wrongNameLatinTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Ivanov Alexandr");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void wrongNameNumbersTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("12345 12345");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void wrongNameSymbolsTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='name'] input").setValue("Иванов Петр:ов.");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }


    @Test
    public void wrongNameIsNullTest() {

        String neededDate = generateDate(3);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(neededDate);
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

}



