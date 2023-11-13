package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    public OrderItem(String menuName, int quantity) {
        validateQuantity(quantity);
        this.menu = validateMenu(menuName);
        this.quantity = quantity;
    }

    public OrderItem(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private Menu validateMenu(String menuName) {
        try {
            return Menu.findMenuByName(menuName);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    public int getTotalPrice() {
        return menu.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean isSameCategory(Category category) {
        return category == Category.from(menu);
    }
}