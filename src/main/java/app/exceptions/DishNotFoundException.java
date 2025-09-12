package app.exceptions;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super(String.format("Блюдо с идентификатором %d не найден", id));
    }
}
