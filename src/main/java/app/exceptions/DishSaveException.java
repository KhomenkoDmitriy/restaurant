package app.exceptions;

public class DishSaveException extends RuntimeException {
    public DishSaveException(String message) {
        super(message);
    }
}
