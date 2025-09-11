package app.repository;

import app.domain.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepository {

 private  final Map<Long, Customer> database = new HashMap<>();
 private long maxId;

 // Сохранение клиента в базе данных
    public Customer save(Customer customer) {
        customer.setId(++maxId);
        database.put(maxId, customer);
        return customer;
    }

    // Read
    public List<Customer> findAll() {
        return new ArrayList<>(database.values());
    }

    // Прочитать клиента по его id
    public Customer findById(Long id) {
        return database.get(id);
    }

    // Метод который изменяет имя клиента в базе данных
    public void update(Long id, String newName) {
        Customer customer = findById(id);
        if (customer != null) {
            customer.setName(newName);
        }
    }

    //Метод который удаляет клиента из базы данных
    public void deleteById(Long id) {
        database.remove(id);
    }





}
