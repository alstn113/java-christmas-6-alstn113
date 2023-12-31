package christmas.domain.order;

import christmas.exception.ErrorMessage;
import christmas.exception.InvalidInputException;
import java.util.Objects;

public class OrderItem {
    private static final int MIN_QUANTITY = 1;
    private final Menu menu;
    private final int quantity;

    public OrderItem(String menuName, int quantity) {
        validateQuantity(quantity);
        this.menu = findMenuByName(menuName);
        this.quantity = quantity;
    }

    public OrderItem(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private Menu findMenuByName(String menuName) {
        try {
            return Menu.findMenuByName(menuName);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException(ErrorMessage.INVALID_ORDER);
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < MIN_QUANTITY) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Objects.equals(menu, orderItem.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, quantity);
    }
}