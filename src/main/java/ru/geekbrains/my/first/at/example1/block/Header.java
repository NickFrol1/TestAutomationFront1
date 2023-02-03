package ru.geekbrains.my.first.at.example1.block;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import lombok.Data;

@Data
public class Header {
    private final SelenideElement title = Selenide.$("[class=\"gb-header__title\"]");
}
