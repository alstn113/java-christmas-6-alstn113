package christmas.domain.order;

public class OrderItem {
    private final Menu menu;
    private final int quantity;

    public OrderItem(String menuName, int quantity) {
        validateQuantity(quantity);
        this.menu = Menu.findMenuByName(menuName);
        this.quantity = quantity;
    }

    public OrderItem(Menu menu, int quantity) {
        validateQuantity(quantity);
        this.menu = menu;
        this.quantity = quantity;
    }

    private void validateQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("수량은 1개 이상이어야 합니다.");
        }
    }

    public int getTotalPrice() {
        return menu.getPrice() * quantity;
    }

    public Category getCategory() {
        return Category.from(menu);
    }

    public int getQuantity() {
        return quantity;
    }

    public Menu getMenu() {
        return menu;
    }
}