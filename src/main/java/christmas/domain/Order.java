package christmas.domain;

public record Order(Menu menu, int quantity) {
    public int price() {
        return menu.price() * quantity;
    }
}