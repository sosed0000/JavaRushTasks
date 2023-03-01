package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        initDishes();
        ConsoleHelper.writeMessage(toString());
    }

    protected void initDishes() throws IOException {
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        if (dishes.size() == 0)
            return "";
        StringBuffer order = new StringBuffer("Your order: [");
        dishes.forEach(d -> order.append(d).append(", "));
        order.replace(order.lastIndexOf(", "), order.length(), "] of ").append(tablet);
        return order.toString();
    }

    public int getTotalCookingTime(){
        int duration = 0;
        for (Dish dish : dishes) {
            duration+=dish.getDuration();
        }
        return duration;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Tablet getTablet() {
        return tablet;
    }
}
