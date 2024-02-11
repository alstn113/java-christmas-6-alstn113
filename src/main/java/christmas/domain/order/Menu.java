package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.Arrays;

public enum Menu {
    // 애피타이저
    MUSHROOM_SOUP(Category.APPETIZER, "양송이수프", 6000),
    TAPAS(Category.APPETIZER, "타파스", 5500),
    CAESAR_SALAD(Category.APPETIZER, "시저샐러드", 8000),

    // 메인
    T_BONE_STEAK(Category.MAIN, "티본스테이크", 55000),
    BBQ_RIBS(Category.MAIN, "바비큐립", 54000),
    SEAFOOD_PASTA(Category.MAIN, "해산물파스타", 35000),
    CHRISTMAS_PASTA(Category.MAIN, "크리스마스파스타", 25000),

    // 디저트
    CHOCOLATE_CAKE(Category.DESSERT, "초코케이크", 15000),
    ICE_CREAM(Category.DESSERT, "아이스크림", 5000),

    // 음료
    ZERO_COLA(Category.DRINK, "제로콜라", 3000),
    RED_WINE(Category.DRINK, "레드와인", 60000),
    CHAMPAGNE(Category.DRINK, "샴페인", 25000);

    private final Category category;
    private final String name;
    private final int price;

    Menu(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static Menu findByName(String name) {
        return Arrays.stream(values())
                .filter(menu -> menu.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.MENU_NOT_FOUND));
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
