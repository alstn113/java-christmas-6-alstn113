package christmas.domain;

import java.util.Arrays;

public enum Category {
    APPETIZER("애피타이저", new Menu[]{
            Menu.MUSHROOM_SOUP,
            Menu.TAPAS,
            Menu.CAESAR_SALAD
    }),
    MAIN_COURSE("메인", new Menu[]{
            Menu.T_BONE_STEAK,
            Menu.BBQ_RIB,
            Menu.SEAFOOD_PASTA,
            Menu.CHRISTMAS_PASTA
    }),
    DESSERT("디저트", new Menu[]{
            Menu.CHOCO_CAKE,
            Menu.ICE_CREAM
    }),
    DRINK("음료", new Menu[]{
            Menu.ZERO_COLA,
            Menu.RED_WINE,
            Menu.CHAMPAGNE,
    }),
    NONE("기타", new Menu[]{});

    private final String viewName;
    private final Menu[] menus;

    Category(String viewName, Menu[] menus) {
        this.viewName = viewName;
        this.menus = menus;
    }

    public static Category findByMenuName(Menu menu) {
        return Arrays.stream(Category.values())
                .filter(category -> hasMenu(category, menu))
                .findFirst()
                .orElse(NONE);
    }

    private static boolean hasMenu(Category from, Menu searchTarget) {
        return Arrays.stream(from.getMenus())
                .anyMatch(containMenu -> containMenu == searchTarget);
    }

    public Menu[] getMenus() {
        return menus;
    }

    public String getViewName() {
        return viewName;
    }
}
