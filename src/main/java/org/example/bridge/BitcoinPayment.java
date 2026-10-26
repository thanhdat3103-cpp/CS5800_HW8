package org.example.bridge;

public class BitcoinPayment extends PaymentNotification {

    public BitcoinPayment(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void notifyPayment(double amount) {
        String message = "Bitcoin payment of $" + amount
                + " was successfully processed.";

        channel.send(message);
    }
}