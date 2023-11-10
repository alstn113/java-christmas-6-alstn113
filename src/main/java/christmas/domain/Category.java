package christmas.domain;

import java.util.List;

public enum Category {
    APPETIZER("에피타이저", List.of(
            new Menu("양송이수프", 6_000),
            new Menu("타파스", 5_500),
            new Menu("시저샐러드", 8_000)
    )),
    MAIN("메인", List.of(
            new Menu("티본스테이크", 55_000),
            new Menu("바비큐립", 54_000),
            new Menu("해산물파스타", 35_000),
            new Menu("크리스마스파스타", 25_000)
    )),
    DESSERT("디저트", List.of(
            new Menu("초코케이크", 15_000),
            new Menu("아이스크림", 5_000)
    )),
    DRINK("음료", List.of(
            new Menu("제로콜라", 3_000),
            new Menu("레드와인", 60_000),
            new Menu("샴페인", 25_000)
    ));

    private final String viewName;
    private final List<Menu> menus;

    Category(String viewName, List<Menu> menus) {
        this.viewName = viewName;
        this.menus = menus;
    }
}
