package ru.netology.delivery.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.delivery.data.DataGenerator;

import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FormDeliveryRequestTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldSendFormAndChangeDate() {
        int step = 3;
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue(DataGenerator.generateCity());
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE, DataGenerator.generateDate(step));
        $(".input__control[name='name']").setValue(DataGenerator.generateName("ru"));
        $(".input__control[name='phone']").setValue(DataGenerator.generatePhone("RU"));
        $(".checkbox__box").click();
        $(".button_view_extra").click();
        $(withText("Успешно!")).shouldBe(appear, Duration.ofSeconds(15));
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(step)));

        step = 6;
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.BACK_SPACE, DataGenerator.generateDate(step));
        $(".button_view_extra").click();
        $(withText("Необходимо подтверждение")).shouldBe(appear, Duration.ofSeconds(5));
        $(withText("У вас уже запланирована встреча на другую дату. Перепланировать?")).shouldBe(appear);
        $(byText("Перепланировать")).click();
        $(withText("Успешно!")).shouldBe(appear);
        $(".notification__content").shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(step)));
    }
}
