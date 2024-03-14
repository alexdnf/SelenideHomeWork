package ru.netology.selenide;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Task2Tests {

    @Test
    public void shouldCheckCityFromListTest() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ни");
        $(byText("Нижний Новгород")).click();
        $("button").click();
        $("[data-day='1710968400000']").click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));

    }

    @Test
    public void shouldCheckCityFromListTest2() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ка");
        $(byText("Казань")).click();
        $("button").click();
        $("[data-day='1710968400000']").click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $("[data-test-id='notification']").shouldBe(visible, Duration.ofSeconds(15));
    }

    @Test
    public void shouldCheckCityFromListTest3() {
        open("http://localhost:9999");

        $("[data-test-id='city'] input").setValue("Ка");
        $(byText("Калининград")).click();
        $("button").click();
        $("[data-day='1710968400000']").click();
        $("[data-test-id='name'] input").setValue("Иванов Александр");
        $("[data-test-id='phone'] input").setValue("+79999999999");
        $("[data-test-id='agreement']").click();
        $(byText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));

    }

}
