package christmas.domain;

public record OrderItem(String menuName, int quantity) {
    public int price() {
        return menu().price() * quantity;
    }

    public Category category() {
        return Category.from(menuName);
    }

    public Menu menu() {
        return Category.findMenuByName(menuName);
    }
}