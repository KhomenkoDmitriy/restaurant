package client;

import app.controller.CustomerController;
import app.controller.DishController;

import java.util.Scanner;

public class Client {

    private static DishController dishController;
    private static CustomerController customerController;
    private static Scanner scanner;

    public static void main(String[] args) {

        try {
            // Создаём объекты контроллеров для взаимодействия с приложением
            dishController = new DishController();
            customerController = new CustomerController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        while (true) {
            System.out.println("Выберите желаемую операцию:");
            System.out.println("1 - операции с блюдами");
            System.out.println("2 - операции с клиентами");
            System.out.println("0 - выход");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    productOperations();
                    break;
                case "2":
                    customerOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод!");
                    break;
            }
        }
    }

    public static void productOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с блюдами:");
                System.out.println("1 - сохранить блюдо");
                System.out.println("2 - получить все блюдо");
                System.out.println("3 - получить блюдо по идентификатору");
                System.out.println("4 - изменить блюдо");
                System.out.println("5 - удалить блюдо по идентификатору");
                System.out.println("6 - удалить блюдо по названию");
                System.out.println("7 - восстановить блюдо по идентификатору");
                System.out.println("8 - получить количество блюд");
                System.out.println("9 - получить суммарную стоимость всех блюд");
                System.out.println("10 - получить среднюю стоимость блюд");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите название блюда");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену блюда");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.println(dishController.save(title, price));
                        break;
                    case "2":
                        dishController.getAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор блюда");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.println(dishController.getById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор блюда");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новую цену блюда");
                        price = Double.parseDouble(scanner.nextLine());
                        dishController.update(id, price);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор блюда");
                        id = Long.parseLong(scanner.nextLine());
                        dishController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите название блюда");
                        title = scanner.nextLine();
                        dishController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор блюда");
                        id = Long.parseLong(scanner.nextLine());
                        dishController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество блюд - " + dishController.getDishesNumber());
                        break;
                    case "9":
                        System.out.println("Суммарная стоимость блюд - " +
                                dishController.getDishesTotalCost());
                        break;
                    case "10":
                        System.out.println("Средняя стоимость блюд - " +
                                dishController.getDishesAveragePrice());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void customerOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с клиентами:");
                System.out.println("1 - сохранить клиента");
                System.out.println("2 - получить всех клиентов");
                System.out.println("3 - получить клиента по идентификатору");
                System.out.println("4 - изменить клиента");
                System.out.println("5 - удалить клиента по идентификатору");
                System.out.println("6 - удалить клиента по имени");
                System.out.println("7 - восстановить клиента по идентификатору");
                System.out.println("8 - получить количество клиентов");
                System.out.println("9 - получить стоимость корзины клиента");
                System.out.println("10 - получить среднюю стоимость блюда в корзине клиента");
                System.out.println("11 - добавить блюдо в корзину клиента");
                System.out.println("12 - удалить блюдо из корзины клиента");
                System.out.println("13 - очистить корзину клиента");
                System.out.println("0 - выход");

                String input = scanner.nextLine();

                switch (input) {
                    case "1":
                        System.out.println("Введите имя клиента");
                        String name = scanner.nextLine();
                        System.out.println(customerController.save(name));
                        break;
                    case "2":
                        customerController.getAll().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор клиента");
                        Long id = Long.parseLong(scanner.nextLine());
                        System.out.println(customerController.getById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор клиента");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите новое имя клиента");
                        name = scanner.nextLine();
                        customerController.update(id, name);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор клиента");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя клиента");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество клиентов - " + customerController.getCustomersNumber());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Стоимость корзины клиента - " +
                                customerController.getCustomersDishesTotalCost(id));
                        break;
                    case "10":
                        System.out.println("Введите идентификатор");
                        id = Long.parseLong(scanner.nextLine());
                        System.out.println("Средняя цена блюда в корзине - " +
                                customerController.getCustomersDishesAveragePrice(id));
                        break;
                    case "11":
                        System.out.println("Введите идентификатор ");
                        Long customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите идентификатор блюда");
                        Long productId = Long.parseLong(scanner.nextLine());
                        customerController.addDishToCustomerDishes(customerId, productId);
                        break;
                    case "12":
                        System.out.println("Введите идентификатор");
                        customerId = Long.parseLong(scanner.nextLine());
                        System.out.println("Введите идентификатор блюда");
                        productId = Long.parseLong(scanner.nextLine());
                        customerController.removeDishFromCustomersDishes(customerId, productId);
                        break;
                    case "13":
                        System.out.println("Введите идентификатор клиента");
                        id = Long.parseLong(scanner.nextLine());
                        customerController.clearCustomersDishes(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
