package christmas.exception;

public class AppException extends IllegalArgumentException {
    private static final String PREFIX = "[ERROR]";

    public AppException(String message) {

        super(String.format("%s %s", PREFIX, message));
    }
}
