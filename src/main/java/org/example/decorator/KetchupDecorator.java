package org.example.decorator;

public class KetchupDecorator extends ToppingDecorator {

    public KetchupDecorator(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Ketchup";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }
}