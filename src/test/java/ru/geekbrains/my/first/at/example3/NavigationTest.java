package ru.geekbrains.my.first.at.example3;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import ru.geekbrains.my.first.at.example2.page.Page;
@Execution(ExecutionMode.CONCURRENT)
public class NavigationTest {

    private final Page page = new Page();

    @BeforeEach
    public void beforeEach() {
        Selenide.open("https://geekbrains.ru/courses");
    }

    @Test
    public void courses() {
        page.getNavigation().getCourses().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Курсы"));
    }

    @Test
    public void topics() {
        page.getNavigation().getTopics().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Форум"));
    }

    @Test
    public void events() {
        page.getNavigation().getEvents().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Вебинары"));
    }

    @Test
    public void posts() {
        page.getNavigation().getPosts().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Блог"));
    }

    @Test
    public void tests() {
        page.getNavigation().getTests().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Тесты"));
    }

    @Test
    public void career() {
        page.getNavigation().getCareer().click();
        page.getHeader().getTitle().shouldHave(Condition.exactText("Карьера"));
    }

}
