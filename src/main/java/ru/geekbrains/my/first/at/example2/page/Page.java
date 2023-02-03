package ru.geekbrains.my.first.at.example2.page;

import lombok.Data;
import ru.geekbrains.my.first.at.example2.block.Header;
import ru.geekbrains.my.first.at.example2.block.Navigation;

@Data
public class Page {
    private Navigation navigation = new Navigation();
    private Header header = new Header();
}
