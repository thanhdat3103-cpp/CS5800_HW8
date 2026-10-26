
package org.example.bridge;

public class BridgeDemo {

    public static void main(String[] args) {

        System.out.println("=== Bridge Pattern Demo ===");

        // Scenario 1: Online Payment + Email
        System.out.println("\nScenario 1: Online Payment + Email");
        PaymentNotification onlineEmail =
                new OnlinePaymentNotification(
                        new EmailChannel(), "Credit Card");
        onlineEmail.notifyPayment(100.0);

        // Scenario 2: Online Payment + SMS
        System.out.println("\nScenario 2: Online Payment + SMS");
        PaymentNotification onlineSms =
                new OnlinePaymentNotification(
                        new SmsChannel(), "PayPal");
        onlineSms.notifyPayment(75.0);

        // Scenario 3: Cash on Delivery + Email
        System.out.println("\nScenario 3: Cash on Delivery + Email");
        PaymentNotification cashEmail =
                new CashOnDeliveryPayment(new EmailChannel());
        cashEmail.notifyPayment(50.0);

        // Scenario 4: Cash on Delivery + SMS
        System.out.println("\nScenario 4: Cash on Delivery + SMS");
        PaymentNotification cashSms =
                new CashOnDeliveryPayment(new SmsChannel());
        cashSms.notifyPayment(30.0);


        // Scenario 5: Bitcoin Payment + Email
        System.out.println("\nScenario 5: Bitcoin Payment + Email");
        PaymentNotification bitcoinEmail =
                new BitcoinPayment(new EmailChannel());
        bitcoinEmail.notifyPayment(200.0);

        // Scenario 6: Bitcoin Payment + SMS
        System.out.println("\nScenario 6: Bitcoin Payment + SMS");
        PaymentNotification bitcoinSms =
                new BitcoinPayment(new SmsChannel());
        bitcoinSms.notifyPayment(150.0);


        // Scenario 7: Online Payment + Push
        System.out.println("\nScenario 7: Online Payment + Push");
        PaymentNotification onlinePush =
                new OnlinePaymentNotification(
                        new PushChannel(), "Credit Card");
        onlinePush.notifyPayment(120.0);

        // Scenario 8: Bitcoin Payment + Push
        System.out.println("\nScenario 8: Bitcoin Payment + Push");
        PaymentNotification bitcoinPush =
                new BitcoinPayment(new PushChannel());
        bitcoinPush.notifyPayment(250.0);

    }
}
