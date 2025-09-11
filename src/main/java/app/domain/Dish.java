package app.domain;

import java.util.Objects;

public class Dish {

    private Long id;
    private String title;
    private double price;
    private boolean active;

    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(price, dish.price) == 0 && active == dish.active && Objects.equals(id, dish.id) && Objects.equals(title, dish.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price, active);
    }

    @Override
    public String toString() {
        return String.format("Dish: id - %d, title - %s, price - %.2f, active - %s", id, title, price, active
        ? "yes" : "no");
    }
}
