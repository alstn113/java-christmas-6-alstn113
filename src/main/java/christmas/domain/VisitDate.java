package christmas.domain;

public record VisitDate(int date) {
    public VisitDate {
        validateVisitDate();
    }

    private void validateVisitDate() {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}