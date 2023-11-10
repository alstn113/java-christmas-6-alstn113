package christmas.domain;

public record OrderItem(Menu menu, int quantity) {
    public int price() {
        return menu.price() * quantity;
    }
}