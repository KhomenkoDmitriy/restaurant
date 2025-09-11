package app.repository;

import app.domain.Dish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishRepository {

    private final Map<Long, Dish> database = new HashMap<>();
    private long maxId;

    // Method Create:
    public Dish save(Dish dish) {
        dish.setId(++maxId);
        database.put(maxId, dish);
        return dish;
    }

    // Read database
    public List<Dish> findAll() {
        return new ArrayList<>(database.values());
    }

    //Возвращаем одно блюдо из базы данных по его id
    public Dish findById(Long id) {
        return database.get(id);
    }

    //(Update) Метод который изменяет цену продукта в базе данных (Update)
    public void update(Long id, double newPrice) {
        Dish dish = findById(id);
        if (dish != null) {
            dish.setPrice(newPrice);
        }
    }

    //(Delete) Метод который удаляет блюда из базы данных (Delete)
    public void deleteById(Long id) {
        database.remove(id);
    }


}
