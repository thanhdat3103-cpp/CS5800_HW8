package org.example.bridge;

public class OnlinePaymentNotification extends PaymentNotification {

    private final String paymentMethod;

    public OnlinePaymentNotification(NotificationChannel channel, String paymentMethod) {
        super(channel);
        this.paymentMethod = paymentMethod;
    }

    @Override
    public void notifyPayment(double amount) {
        String message = "Online payment of $" + amount
                + " using " + paymentMethod
                + " was successful.";

        channel.send(message);
    }
}