package org.example.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CustomerOrder {

    private final List<FoodItem> items;

    public CustomerOrder() {
        items = new ArrayList<>();
    }

    public void addItem(FoodItem item) {
        items.add(Objects.requireNonNull(item,
                "Food item cannot be null"));
    }

    public List<FoodItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double calculateTotal() {
        double total = 0.0;

        for (FoodItem item : items) {
            total += item.getCost();
        }

        return total;
    }
}