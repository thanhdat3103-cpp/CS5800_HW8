package org.example.decorator;

public abstract class ToppingDecorator implements FoodItem {

    protected final FoodItem foodItem;

    public ToppingDecorator(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    @Override
    public String getDescription() {
        return foodItem.getDescription();
    }

    @Override
    public double getCost() {
        return foodItem.getCost();
    }
}