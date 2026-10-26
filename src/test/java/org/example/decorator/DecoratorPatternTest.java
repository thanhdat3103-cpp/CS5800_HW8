package org.example.decorator;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class DecoratorPatternTest {

    private static final double DELTA = 0.0001;

    // Test 1: Burger
    @Test
    void testBurger() {
        FoodItem burger = new Burger();

        assertEquals("Burger", burger.getDescription());
        assertEquals(5.00, burger.getCost(), DELTA);
    }

    // Test 2: Fries
    @Test
    void testFries() {
        FoodItem fries = new Fries();

        assertEquals("Fries", fries.getDescription());
        assertEquals(3.00, fries.getCost(), DELTA);
    }

    // Test 3: Hot Dog
    @Test
    void testHotDog() {
        FoodItem hotDog = new HotDog();

        assertEquals("Hot Dog", hotDog.getDescription());
        assertEquals(4.00, hotDog.getCost(), DELTA);
    }

    // Test 4: Cheese Decorator
    @Test
    void testCheeseDecorator() {
        FoodItem item = new CheeseDecorator(new Burger());

        assertEquals("Burger + Cheese", item.getDescription());
        assertEquals(6.00, item.getCost(), DELTA);
    }

    // Test 5: Ketchup Decorator
    @Test
    void testKetchupDecorator() {
        FoodItem item = new KetchupDecorator(new Burger());

        assertEquals("Burger + Ketchup", item.getDescription());
        assertEquals(5.50, item.getCost(), DELTA);
    }

    // Test 6: Onion Decorator
    @Test
    void testOnionDecorator() {
        FoodItem item = new OnionDecorator(new Fries());

        assertEquals("Fries + Onions", item.getDescription());
        assertEquals(3.75, item.getCost(), DELTA);
    }

    // Test 7: Multiple Toppings
    @Test
    void testMultipleToppings() {
        FoodItem item = new OnionDecorator(
                new KetchupDecorator(
                        new CheeseDecorator(new Burger())));

        assertEquals(
                "Burger + Cheese + Ketchup + Onions",
                item.getDescription());

        assertEquals(7.25, item.getCost(), DELTA);
    }

    // Test 8: Empty Customer Order
    @Test
    void testEmptyOrder() {
        CustomerOrder order = new CustomerOrder();

        assertTrue(order.getItems().isEmpty());
        assertEquals(0.0, order.calculateTotal(), DELTA);
    }

    // Test 9: Add Items
    @Test
    void testAddItems() {
        CustomerOrder order = new CustomerOrder();

        FoodItem burger = new Burger();
        FoodItem fries = new Fries();

        order.addItem(burger);
        order.addItem(fries);

        assertEquals(2, order.getItems().size());
        assertSame(burger, order.getItems().get(0));
        assertSame(fries, order.getItems().get(1));
    }

    // Test 10: Calculate Total
    @Test
    void testCalculateTotal() {
        CustomerOrder order = new CustomerOrder();

        order.addItem(new KetchupDecorator(
                new CheeseDecorator(new Burger())));

        order.addItem(new OnionDecorator(new Fries()));

        order.addItem(new OnionDecorator(
                new CheeseDecorator(new HotDog())));

        assertEquals(16.00, order.calculateTotal(), DELTA);
    }

    // Test 11: Reject Null Food Item
    @Test
    void testAddNullItem() {
        CustomerOrder order = new CustomerOrder();

        assertThrows(NullPointerException.class,
                () -> order.addItem(null));
    }

    // Test 12: Unmodifiable Item List
    @Test
    void testUnmodifiableItems() {
        CustomerOrder order = new CustomerOrder();
        order.addItem(new Burger());

        assertThrows(UnsupportedOperationException.class,
                () -> order.getItems().add(new Fries()));
    }

    // Test 13: Regular Loyalty
    @Test
    void testRegularDiscount() {
        LoyaltyStatus loyalty = new LoyaltyStatus(
                LoyaltyStatus.Level.REGULAR);

        assertEquals(100.00,
                loyalty.applyDiscount(100.00), DELTA);
    }

    // Test 14: Silver Loyalty
    @Test
    void testSilverDiscount() {
        LoyaltyStatus loyalty = new LoyaltyStatus(
                LoyaltyStatus.Level.SILVER);

        assertEquals(95.00,
                loyalty.applyDiscount(100.00), DELTA);
    }

    // Test 15: Gold Loyalty
    @Test
    void testGoldDiscount() {
        LoyaltyStatus loyalty = new LoyaltyStatus(
                LoyaltyStatus.Level.GOLD);

        assertEquals(90.00,
                loyalty.applyDiscount(100.00), DELTA);
    }

    // Test 16: Invalid Order Totals
    @Test
    void testInvalidOrderTotal() {
        LoyaltyStatus loyalty = new LoyaltyStatus(
                LoyaltyStatus.Level.GOLD);

        assertThrows(IllegalArgumentException.class,
                () -> loyalty.applyDiscount(-10.0));

        assertThrows(IllegalArgumentException.class,
                () -> loyalty.applyDiscount(Double.NaN));

        assertThrows(IllegalArgumentException.class,
                () -> loyalty.applyDiscount(
                        Double.POSITIVE_INFINITY));
    }

    // Test 17: Null Loyalty Level
    @Test
    void testNullLoyaltyLevel() {
        assertThrows(NullPointerException.class,
                () -> new LoyaltyStatus(null));
    }

    // Test 18: Driver Program
    @Test
    void testDecoratorDemo() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output =
                new ByteArrayOutputStream();

        try (PrintStream stream = new PrintStream(
                output, true, StandardCharsets.UTF_8)) {

            System.setOut(stream);
            DecoratorDemo.main(new String[0]);

        } finally {
            System.setOut(originalOut);
        }

        String result =
                output.toString(StandardCharsets.UTF_8);

        assertAll(
                () -> assertTrue(result.contains(
                        "Burger + Cheese + Ketchup")),
                () -> assertTrue(result.contains(
                        "Fries + Onions")),
                () -> assertTrue(result.contains(
                        "Hot Dog + Cheese + Onions")),
                () -> assertTrue(result.contains(
                        "Total before discount: $16.00")),
                () -> assertTrue(result.contains(
                        "Final Total $15.20")),
                () -> assertTrue(result.contains(
                        "Final Total $14.40"))
        );
    }
}