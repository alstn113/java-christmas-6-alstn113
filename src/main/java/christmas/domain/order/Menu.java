package christmas.domain.order;

import java.util.Arrays;

public enum Menu {
    // 애피타이저
    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000),

    // 메인
    T_BONE_STEAK("티본스테이크", 55_000),
    BARBECUE_RIBS("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000),

    // 디저트
    CHOCOLATE_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000),

    // 음료
    ZERO_COLA("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String viewName;
    private final int price;

    Menu(String viewName, int price) {
        this.viewName = viewName;
        this.price = price;
    }

    public static Menu findMenuByName(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.getViewName().equals(menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 메뉴입니다."));
    }

    public String getViewName() {
        return viewName;
    }

    public int getPrice() {
        return price;
    }
}
