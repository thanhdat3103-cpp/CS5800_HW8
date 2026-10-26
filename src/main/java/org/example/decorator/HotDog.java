package org.example.decorator;

public class HotDog implements FoodItem {

    @Override
    public String getDescription() {
        return "Hot Dog";
    }

    @Override
    public double getCost() {
        return 4.00;
    }
}