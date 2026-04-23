package product_service.config.exception;

public class NotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Resource not found";
    public NotFoundException(String message) {
        super(message.isEmpty() ? DEFAULT_MESSAGE : message);
    }
}
