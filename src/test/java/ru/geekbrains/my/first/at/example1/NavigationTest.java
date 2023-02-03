package ru.geekbrains.my.first.at.example1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

@Execution(ExecutionMode.CONCURRENT)
public class NavigationTest {

    @Test
    public void checkNavigation(){
        Selenide.open("https://drom.ru");
        Selenide.$(By.linkText("Продажа авто в России")).click();
        assert Selenide.$(By.className("css-hqbmxg e18vbajn0")).getText().contains("Продажа автомобилей");
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/courses\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Курсы"));
//
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/events\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Вебинары"));
//
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/topics\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Форум"));
//
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/posts\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Блог"));
//
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/tests\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Тесты"));
//
//        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/career\"]").click();
//        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Карьера"));
    }
}
