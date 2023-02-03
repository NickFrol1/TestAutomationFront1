package ru.geekbrains.my.first.at.example2;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Execution(ExecutionMode.CONCURRENT)
public class NavigationTest {

    @BeforeEach
    public void beforeEach(){
        Selenide.open("https://geekbrains.ru/courses");
    }

    @Test
    public void courses(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/courses\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Курсы"));
    }

    @Test
    public void topics(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/topics\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Форум"));
    }

    @Test
    public void events(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [hrPaef=\"/events\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Вебинары"));
    }

    @Test
    public void posts(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/posts\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Блог"));
    }

    @Test
    public void tests(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/tests\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Тесты"));
    }

    @Test
    public void career(){
        Selenide.$("[class=\"gb-left-menu__nav\"] [href=\"/career\"]").click();
        Selenide.$("[class=\"gb-header__title\"]").shouldHave(Condition.exactText("Карьера"));
    }

}
