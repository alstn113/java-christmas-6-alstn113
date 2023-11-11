package christmas.exception;

public class InvalidInputException extends AppException {
    public InvalidInputException(ErrorMesssage errorMesssage) {
        super(errorMesssage.getMessage());
    }
}
