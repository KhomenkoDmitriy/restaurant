package app.exceptions;

public class DishUpdateException extends RuntimeException {
    public DishUpdateException(String message) {
        super(message);
    }
}
