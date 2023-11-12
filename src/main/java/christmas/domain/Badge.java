package christmas.domain;

public enum Badge {
    SANTA(20000, "산타"),
    TREE(10000, "트리"),
    STAR(5000, "별"),
    NONE(0, "없음");

    private final String viewName;
    private final int condition;

    Badge(int condition, String viewName) {
        this.condition = condition;
        this.viewName = viewName;
    }

    public static Badge getBadgeByCondition(int totalBenefits) {
        for (Badge badge : Badge.values()) {
            if (badge.condition <= totalBenefits) {
                return badge;
            }
        }
        return NONE;
    }

    public String getViewName() {
        return viewName;
    }
}
