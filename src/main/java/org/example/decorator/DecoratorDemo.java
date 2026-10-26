package org.example.decorator;

import java.util.Locale;

public class DecoratorDemo {

    public static void main(String[] args) {

        System.out.println("=== Decorator Pattern Demo ===");

        // Scenario 1: Burger with Cheese and Ketchup
        FoodItem burger = new Burger();
        burger = new CheeseDecorator(burger);
        burger = new KetchupDecorator(burger);

        // Scenario 2: Fries with Onions
        FoodItem fries = new Fries();
        fries = new OnionDecorator(fries);

        // Scenario 3: Hot Dog with Cheese and Onions
        FoodItem hotDog = new HotDog();
        hotDog = new CheeseDecorator(hotDog);
        hotDog = new OnionDecorator(hotDog);

        // Create a customer order
        CustomerOrder order = new CustomerOrder();

        order.addItem(burger);
        order.addItem(fries);
        order.addItem(hotDog);

        // Display ordered items
        System.out.println("\n=== Customer Order ===");

        for (FoodItem item : order.getItems()) {
            System.out.printf(
                    Locale.US,
                    "%s: $%.2f%n",
                    item.getDescription(),
                    item.getCost()
            );
        }

        // Calculate total cost
        double total = order.calculateTotal();

        System.out.printf(
                Locale.US,
                "%nTotal before discount: $%.2f%n",
                total
        );

        // Apply loyalty discounts
        System.out.println("\n=== Loyalty Discounts ===");

        for (LoyaltyStatus.Level level :
                LoyaltyStatus.Level.values()) {

            LoyaltyStatus loyalty = new LoyaltyStatus(level);

            double discountedTotal =
                    loyalty.applyDiscount(total);

            double discount = total - discountedTotal;

            System.out.printf(
                    Locale.US,
                    "%s: Discount $%.2f, Final Total $%.2f%n",
                    level,
                    discount,
                    discountedTotal
            );
        }
    }
}