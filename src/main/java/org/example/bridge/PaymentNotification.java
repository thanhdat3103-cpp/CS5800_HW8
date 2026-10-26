package org.example.bridge;

public abstract class PaymentNotification {

    protected final NotificationChannel channel;

    public PaymentNotification(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void notifyPayment(double amount);
}
