package christmas.domain;

import java.util.Arrays;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NONE("없음", 0);

    private final String viewName;
    private final int requiredTotalBenefitPrice;

    Badge(String viewName, int requiredTotalBenefitPrice) {
        this.viewName = viewName;
        this.requiredTotalBenefitPrice = requiredTotalBenefitPrice;
    }

    public static Badge of(int totalBenefitPrice) {
        return Arrays.stream(Badge.values())
                .filter(badge -> totalBenefitPrice >= badge.requiredTotalBenefitPrice)
                .findFirst()
                .orElse(NONE);
    }

    public String getViewName() {
        return viewName;
    }

    public int getRequiredTotalBenefitPrice() {
        return requiredTotalBenefitPrice;
    }
}
