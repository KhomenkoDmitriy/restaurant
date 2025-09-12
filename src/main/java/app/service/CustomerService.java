package app.service;

import app.domain.Customer;
import app.domain.Dish;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.repository.CustomerRepository;

import java.util.Iterator;
import java.util.List;

public class CustomerService {

    //         Функционал сервиса покупателей.
    private final CustomerRepository repository = new CustomerRepository();
    private final DishService dishService = DishService.getInstance();

// Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).

    public Customer save(Customer customer) {
        if (customer == null){
            throw new CustomerSaveException("Клиент не может быть null");
        }

        String name = customer.getName();
        if (name == null || name.trim().isEmpty()){
            throw new CustomerSaveException("Имя клиента не может быть пустым");
        }
        customer.setActive(true);
        return repository.save(customer);
    }

// Вернуть всех покупателей из базы данных (активных).

    public List<Customer> getAllActiveCustomers() {
        return repository.findAll()
                .stream()
                .filter(Customer::isActive)
                .toList();
    }

// Вернуть одного покупателя из базы данных по его идентификатору (если он активен).

    public Customer getActiveCustomerById(Long id) {
        Customer customer = repository.findById(id);
        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

// Изменить одного покупателя в базе данных по его идентификатору.

    public void update(Long id, String newName) {
        if (newName == null || newName.trim().isEmpty()) {
            throw new CustomerUpdateException("Имя клиента не может быть пустым");
        }
        repository.update(id, newName);
    }

// Удалить покупателя из базы данных по его идентификатору.

    public void deleteById(Long id) {
        Customer customer = getActiveCustomerById(id);
        customer.setActive(false);
    }

// Удалить покупателя из базы данных по его имени.

    public void deleteByName(String name) {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }

// Восстановить удалённого покупателя в базе данных по его идентификатору.

    public void restoreById(Long id) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }
        customer.setActive(true);
    }

    // Вернуть общее количество покупателей в базе данных (активных).

    public int getActiveCustomersNumber() {
        return getAllActiveCustomers().size();
    }

// Вернуть стоимость корзины покупателя по его идентификатору (если он активен).

    public double getCustomerDishesTotalCost(Long id) {
        return getActiveCustomerById(id)
                .getDishes()
                .stream()
                .filter(Dish::isActive)
                .mapToDouble(Dish::getPrice)
                .sum();
    }

// Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)

    public double getCustomersDishesAveragePrice(Long id) {
        return getActiveCustomerById(id)
                .getDishes()
                .stream()
                .filter(Dish::isActive)
                .mapToDouble(Dish::getPrice)
                .average()
                .orElse(0.0);
    }

// Добавить товар в корзину покупателя по их идентификаторам (если оба активны)

    public void addDishToCustomersDishes(Long customerId, Long dishId) {
        Customer customer = getActiveCustomerById(customerId);
        Dish dish = dishService.getActiveDishById(dishId);
        customer.getDishes().add(dish);
    }

// Удалить товар из корзины покупателя по их идентификаторам

    public void removeDishFromCustomersDishes (Long customerId, Long dishId) {
        Customer customer = getActiveCustomerById(customerId);
        List<Dish> dishes = customer.getDishes();
        Iterator<Dish> iterator = dishes.iterator();

        while (iterator.hasNext()){
            if (iterator.next().getId().equals(dishId)) {
                iterator.remove();
                break;
            }
        }
    }

// Полностью очистить корзину покупателя по его идентификатору (если он активен)

    public void clearCustomersDishes(Long id) {
        Customer customer = getActiveCustomerById(id);
        customer.getDishes().clear();
    }

}
