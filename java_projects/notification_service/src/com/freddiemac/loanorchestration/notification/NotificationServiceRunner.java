package com.freddiemac.loanorchestration.notification;

import com.freddiemac.loanorchestration.notification.types.NotificationRequest;
import com.freddiemac.loanorchestration.notification.types.NotificationResponse;

public class NotificationServiceRunner {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   VERIFYING STANDALONE NOTIFICATION SERVICE");
        System.out.println("==================================================");

        NotificationProcessPort service = new NotificationProcessImpl();

        System.out.println("\n--- Test Case 1: Notify John Doe (APPROVED) ---");
        NotificationResponse res = service.processNotification(new NotificationRequest(1003L, "John Doe", "APPROVED"));
        System.out.println("Status: " + res.getStatus());
        System.out.println("Message: " + res.getMessage());

        System.out.println("\n==================================================");
    }
}
