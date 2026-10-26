package org.example.decorator;

public class OnionDecorator extends ToppingDecorator {

    public OnionDecorator(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Onions";
    }

    @Override
    public double getCost() {
        return super.getCost() + 0.75;
    }
}