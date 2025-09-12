package app.controller;

import app.domain.Dish;
import app.service.DishService;

import java.util.List;

public class DishController {

    private final DishService service = DishService.getInstance();
    //             Функционал контроллера блюда
//
// Сохранить блюдо в базе данных (при сохранении продукт автоматически считается активным).

    public Dish save(String title, double price) {
        Dish dish = new Dish(title, price);
        return service.save(dish);
    }


// Вернуть все блюда из базы данных (активные).

    public List<Dish> getAll() {
        return service.getAllActiveDishes();
    }

// Вернуть одно блюдо из базы данных по его идентификатору (если он активен).

    public Dish getById(Long id) {
        return service.getActiveDishById(id);
    }

// Изменить одно блюдо в базе данных по его идентификатору.

    public void update(Long id, double newPrice) {
        service.update(id, newPrice);
    }

// Удалить блюдо из базы данных по его идентификатору.

    public void deleteById(Long id) {
        service.deleteById(id);
    }

// Удалить блюдо из базы данных по его наименованию.

    public void deleteByTitle(String title) {
        service.deleteByTitle(title);
    }

// Восстановить удалённое блюдо в базе данных по его идентификатору.

    public void restoreById(Long id) {
        service.restoreById(id);
    }

// Вернуть общее количество блюд в базе данных (активных).

    public int getDishesNumber() {
        return service.getActiveDishesNumber();
    }

// Вернуть суммарную стоимость всех блюд в базе данных (активных).

    public double getDishesTotalCost() {
        return service.getActiveDishesTotalCost();
    }

// Вернуть среднюю стоимость продукта в базе данных (из активных).

    public double getDishesAveragePrice() {
        return service.getActiveDishesAveragePrice();
    }

}
