package christmas.exception;

public class ExpectedVisitDateException extends BaseException {
    public ExpectedVisitDateException() {
        super(ErrorMessage.INVALID_EXPECTED_VISIT_DATE.getMessage());
    }

    public ExpectedVisitDateException(ErrorMessage errorMessage) {
        super(errorMessage.getMessage());
    }
}
