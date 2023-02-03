package ru.geekbrains.my.first.at.example1;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.fasterxml.jackson.core.util.BufferRecycler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Execution(ExecutionMode.CONCURRENT)
public class NavigationTest {

    @Test
    public void checkNavigation(){
        String tmpLine;
        String resLine ="172.18.0.2";
        String port = "80";
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec("docker inspect 4a | grep IPAddress");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InputStream in = proc.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        try {
            while ((tmpLine = br.readLine()) != null) {
                if (tmpLine.contains("\"IPAddress\": \"172")) {
                    resLine = tmpLine.replace("\"IPAddress\": \"","").
                            replaceAll(" ", "").
                            replace("\"","").
                            replace(",", "");
                    System.out.println(resLine);
                }
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


//        Selenide.open(" https://localhost:8088/index.html");
        Selenide.open("https://"+resLine+port+":/");
//        Selenide.$(By.linkText("Продажа авто в России")).click();

//        assert Selenide.$(By.cssSelector(".css-hqbmxg")).getText().contains("Продажа автомобилей");
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
