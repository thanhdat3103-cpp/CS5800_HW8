package org.example.decorator;

public class CheeseDecorator extends ToppingDecorator {

    public CheeseDecorator(FoodItem foodItem) {
        super(foodItem);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Cheese";
    }

    @Override
    public double getCost() {
        return super.getCost() + 1.00;
    }
}