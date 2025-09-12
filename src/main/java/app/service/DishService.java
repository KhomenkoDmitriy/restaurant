package app.service;

import app.domain.Dish;
import app.exceptions.DishNotFoundException;
import app.exceptions.DishSaveException;
import app.exceptions.DishUpdateException;
import app.repository.DishRepository;

import java.util.List;

public class DishService {

    private static DishService instance;
    private final DishRepository repository = new DishRepository();

    private DishService() {
    }

    public static DishService getInstance() {
        if (instance == null) {
            instance = new DishService();
        }
        return instance;
    }
    //    Сохранить блюдо в базе данных (при сохранении блюдо автоматически считается активным).

    public Dish save(Dish dish){
        if (dish == null) {
            throw new DishSaveException("Блюдо не может быть null");
        }
        String title = dish.getTitle();
        if (title == null || title.trim().isEmpty()){
            throw new DishSaveException("Наименование блюдо не должно быть пустым");
        }
        if (dish.getPrice() < 0) {
            throw new DishSaveException("Цена блюдо не должна быть отрицательной");
        }
        dish.setActive(true);
        return repository.save(dish);

    }


//    Вернуть все блюдо из базы данных (активные).

    public List<Dish> getAllActiveDishes() {
        return repository.findAll()
                .stream()
                .filter(Dish::isActive)
                .toList();
    }

//    Вернуть один блюдо из базы данных по его идентификатору (если он активен).

    public Dish getActiveDishById(Long id) {
        Dish dish = repository.findById(id);

        if (dish == null || !dish.isActive()) {
            throw new DishNotFoundException(id);

        }
        return dish;
    }


//    Изменить один блюдо в базе данных по его идентификатору.

    public void update(Long id, double newPrice) {
        if (newPrice < 0) {
            throw new DishUpdateException("Цена блюдо не должна быть отрицательной");
        }
        repository.update(id, newPrice);
    }

//    Удалить блюдо из базы данных по его идентификатору.

    public void deleteById(Long id) {
        Dish dish = getActiveDishById(id);
        dish.setActive(false);
    }

//    Удалить блюдо из базы данных по его наименованию.

    public void deleteByTitle(String title) {
        getAllActiveDishes()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(x -> x.setActive(false));
    }

//    Восстановить удалённый блюдо в базе данных по его идентификатору.

    public void restoreById(Long id) {
        Dish dish = repository.findById(id);
        if (dish == null) {
          throw new DishNotFoundException(id);
        }
        dish.setActive(true);
    }

//    Вернуть общее количество блюд в базе данных (активных).

    public int getActiveDishesNumber() {
        return getAllActiveDishes().size();
    }



//    Вернуть суммарную стоимость всех блюд в базе данных (активных).

    public double getActiveDishesTotalCost() {
        return getAllActiveDishes()
                .stream()
                .mapToDouble(Dish::getPrice)
                .sum();
    }


//    Вернуть среднюю стоимость продукта в базе данных (из активных).


    public double getActiveDishesAveragePrice() {
        return getAllActiveDishes()
                .stream()
                .mapToDouble(Dish::getPrice)
                .average()
                .orElse(0.0);
    }

}
