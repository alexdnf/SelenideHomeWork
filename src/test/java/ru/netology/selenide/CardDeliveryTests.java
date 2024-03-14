package ru.netology.selenide;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CardDeliveryTests {
    LocalDate today = LocalDate.now();
    LocalDate tomorrow = today.plusDays(1);
    LocalDate daysLaterNone1 = today.plusDays(2);
    LocalDate daysLaterCorrect = today.plusDays(3);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @Test
    public void shouldSendCorrectFormTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
        //$(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldSendCorrectFormDoubleSurnameTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов-Петров Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void wrongDateTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(today.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='date'] .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", text.trim());
    }

    @Test
    public void wrongDateTomorrowTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(tomorrow.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='date'] .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", text.trim());
    }

    @Test
    public void wrongDate2DayTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterNone1.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='date'] .input__sub").getText();
        assertEquals("Заказ на выбранную дату невозможен", text.trim());
    }


    @Test
    public void wrongCityTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Павлово");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='city'].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text.trim());
    }

    @Test
    public void wrongCityNullTest() {
        open("http://localhost:9999");

        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='city'].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void wrongCityLatinTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Moscow");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='city'].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text.trim());
    }

    @Test
    public void wrongCityNumbersTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("12345");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='city'].input_invalid .input__sub").getText();
        assertEquals("Доставка в выбранный город недоступна", text.trim());
    }

    @Test
    public void wrongPhoneNumberLettersTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("qwerty");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void wrongPhoneNumberSymbolsTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+7999999-999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void wrongPhoneNumberLessSymbolsTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+7999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", text.trim());
    }

    @Test
    public void wrongPhoneNumberIsNullTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='phone'].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

    @Test
    public void wrongNameLatinTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Ivanov Alexandr");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void wrongNameNumbersTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("12345 12345");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }

    @Test
    public void wrongNameSymbolsTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='name'] input").setValue("Иванов Петр:ов.");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", text.trim());
    }


    @Test
    public void wrongNameIsNullTest() {

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Нижний Новгород");
        $("[data-test-id='date'] input").doubleClick();
        $("[data-test-id='date'] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(daysLaterCorrect.format(formatter));
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();

        String text = $("[data-test-id='name'].input_invalid .input__sub").getText();
        assertEquals("Поле обязательно для заполнения", text.trim());
    }

}



