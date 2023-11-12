package christmas.domain.order;

import java.util.Arrays;

public enum Category {
    APPETIZER("에피타이저", new Menu[]{
            Menu.MUSHROOM_SOUP,
            Menu.TAPAS,
            Menu.CAESAR_SALAD
    }),
    MAIN("메인", new Menu[]{
            Menu.T_BONE_STEAK,
            Menu.BARBECUE_RIBS,
            Menu.SEAFOOD_PASTA,
            Menu.CHRISTMAS_PASTA
    }),
    DESSERT("디저트", new Menu[]{
            Menu.CHOCOLATE_CAKE,
            Menu.ICE_CREAM
    }),
    DRINK("음료", new Menu[]{
            Menu.ZERO_COLA,
            Menu.RED_WINE,
            Menu.CHAMPAGNE
    }),
    NONE("없음", new Menu[]{});

    private final String viewName;
    private final Menu[] menus;

    Category(String viewName, Menu[] menus) {
        this.viewName = viewName;
        this.menus = menus;
    }

    public static Category from(Menu menu) {
        for (Category category : values()) {
            if (Arrays.asList(category.menus).contains(menu)) {
                return category;
            }
        }
        return NONE;
    }
}
