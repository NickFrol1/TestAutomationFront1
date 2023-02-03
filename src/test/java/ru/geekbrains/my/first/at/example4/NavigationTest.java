package ru.geekbrains.my.first.at.example4;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.geekbrains.my.first.at.example2.page.Page;
@Execution(ExecutionMode.CONCURRENT)
public class NavigationTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "Курсы",
            "Вебинары",
            "Форум",
            "Блог",
            "Тесты",
            "Карьера"
    })
    public void button(String name) {
        Selenide.open("https://geekbrains.ru/courses");
        Page page = new Page();
        page.getNavigation().getButton(name).click();
        page.getHeader().getTitle().shouldHave(Condition.exactText(name));
    }
}
