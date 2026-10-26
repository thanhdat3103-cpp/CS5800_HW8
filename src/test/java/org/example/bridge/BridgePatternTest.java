package org.example.bridge;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class BridgePatternTest {

    // Helper method to capture console output
    private String captureOutput(Runnable action) {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        try (PrintStream stream = new PrintStream(
                output, true, StandardCharsets.UTF_8)) {
            System.setOut(stream);
            action.run();
        } finally {
            System.setOut(originalOut);
        }

        return output.toString(StandardCharsets.UTF_8);
    }

    // Test 1: Email Channel
    @Test
    void testEmailChannel() {
        String output = captureOutput(
                () -> new EmailChannel().send("Test message"));

        assertEquals(
                "Email Notification: Test message",
                output.trim());
    }

    // Test 2: SMS Channel
    @Test
    void testSmsChannel() {
        String output = captureOutput(
                () -> new SmsChannel().send("Test message"));

        assertEquals(
                "SMS Notification: Test message",
                output.trim());
    }

    // Test 3: Push Channel
    @Test
    void testPushChannel() {
        String output = captureOutput(
                () -> new PushChannel().send("Test message"));

        assertEquals(
                "Push Notification: Test message",
                output.trim());
    }

    // Test 4: Online Payment Notification
    @Test
    void testOnlinePaymentNotification() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new OnlinePaymentNotification(
                            new EmailChannel(), "Credit Card");
            payment.notifyPayment(100.0);
        });

        assertTrue(output.contains(
                "Online payment of $100.0 using Credit Card was successful."));
    }

    // Test 5: Cash on Delivery
    @Test
    void testCashOnDeliveryPayment() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new CashOnDeliveryPayment(new SmsChannel());
            payment.notifyPayment(50.0);
        });

        assertTrue(output.contains(
                "Cash on Delivery order of $50.0 has been placed."));
    }

    // Test 6: Bitcoin Payment
    @Test
    void testBitcoinPayment() {
        String output = captureOutput(() -> {
            PaymentNotification payment =
                    new BitcoinPayment(new PushChannel());
            payment.notifyPayment(200.0);
        });

        assertTrue(output.contains(
                "Bitcoin payment of $200.0 was successfully processed."));
    }

    // Test 7: Driver Program
    @Test
    void testBridgeDemo() {
        String output = captureOutput(
                () -> BridgeDemo.main(new String[0]));

        for (int i = 1; i <= 8; i++) {
            assertTrue(output.contains("Scenario " + i + ":"),
                    "Missing scenario " + i);
        }

        assertTrue(output.contains("Email Notification:"));
        assertTrue(output.contains("SMS Notification:"));
        assertTrue(output.contains("Push Notification:"));
    }
}