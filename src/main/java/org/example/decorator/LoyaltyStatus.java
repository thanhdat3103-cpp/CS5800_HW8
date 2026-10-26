package org.example.decorator;

import java.util.Objects;

public class LoyaltyStatus {

    public enum Level {
        REGULAR,
        SILVER,
        GOLD
    }

    private final Level level;

    public LoyaltyStatus(Level level) {
        this.level = Objects.requireNonNull(
                level, "Loyalty level cannot be null");
    }

    public double applyDiscount(double total) {
        if (!Double.isFinite(total) || total < 0) {
            throw new IllegalArgumentException(
                    "Order total must be a non-negative finite number");
        }

        double discountRate = switch (level) {
            case REGULAR -> 0.0;
            case SILVER -> 0.05;
            case GOLD -> 0.10;
        };

        return total * (1 - discountRate);
    }
}