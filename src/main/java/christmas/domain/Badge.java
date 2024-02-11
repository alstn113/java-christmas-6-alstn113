package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String name;
    private final int minimumPrice;

    Badge(String name, int minimumPrice) {
        this.name = name;
        this.minimumPrice = minimumPrice;
    }

    public static Badge of(int price) {
        return Arrays.stream(values())
                .filter(badge -> price >= badge.minimumPrice)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
