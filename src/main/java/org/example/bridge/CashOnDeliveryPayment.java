package org.example.bridge;

public class CashOnDeliveryPayment extends PaymentNotification {

    public CashOnDeliveryPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyPayment(double amount) {
        String message = "Cash on Delivery order of $" + amount
                + " has been placed. Please pay upon delivery.";

        channel.send(message);
    }
}