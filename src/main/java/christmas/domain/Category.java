package christmas.domain;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.Arrays;
import java.util.Objects;

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
    });

    private final String viewName;
    private final Menu[] menus;

    Category(String viewName, Menu[] menus) {
        this.viewName = viewName;
        this.menus = menus;
    }

    public static Category findByMenuName(String menuName) {
        return Arrays.stream(Category.values())
                .filter(category -> category.contains(menuName))
                .findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMessage.MENU_NOT_EXIST));
    }

    private boolean contains(String menuName) {
        return Arrays.stream(menus)
                .anyMatch(menu -> menu.getViewName().equals(menuName));
    }

    public Menu[] getMenus() {
        return menus;
    }

    public String getViewName() {
        return viewName;
    }
}
