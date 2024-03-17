package ru.netology.selenide;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class Task2Tests {
    public String generateDate1(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("d"));
    }
    @Test
    public void shouldCheckCityFromListTest() {

        String neededDay = generateDate1(7);
        int todayDay = LocalDate.now().getDayOfMonth();
        int neededDayInt = Integer.parseInt(neededDay);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ни");
        $$(".menu-item__control").find(text("Нижний Новгород")).click();
        $("button").click();

        if (todayDay >= neededDayInt) {
            $("[data-step='1']").click();
        }

        $$(".calendar__day").find(text(neededDay)).click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"))
                .shouldHave(text(neededDay));
    }
    @Test
    public void shouldCheckCityFromListTest2() {

        String neededDay = generateDate1(14);
        int todayDay = LocalDate.now().getDayOfMonth();
        int neededDayInt = Integer.parseInt(neededDay);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ни");
        $$(".menu-item__control").find(text("Нижний Новгород")).click();
        $("button").click();

        if (todayDay >= neededDayInt) {
            $("[data-step='1']").click();
        }

        $$(".calendar__day").find(text(neededDay)).click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"))
                .shouldHave(text(neededDay));
    }

    @Test
    public void shouldCheckCityFromListTest3() {

        String neededDay = generateDate1(21);
        int todayDay = LocalDate.now().getDayOfMonth();
        int neededDayInt = Integer.parseInt(neededDay);

        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ни");
        $$(".menu-item__control").find(text("Нижний Новгород")).click();
        $("button").click();

        if (todayDay >= neededDayInt) {
            $("[data-step='1']").click();
        }

        $$(".calendar__day").find(text(neededDay)).click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно!"))
                .shouldHave(text(neededDay));
    }
}