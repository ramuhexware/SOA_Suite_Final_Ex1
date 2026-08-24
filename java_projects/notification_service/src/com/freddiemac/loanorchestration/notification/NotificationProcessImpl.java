package com.freddiemac.loanorchestration.notification;

import javax.jws.WebService;
import com.freddiemac.loanorchestration.notification.types.NotificationRequest;
import com.freddiemac.loanorchestration.notification.types.NotificationResponse;

@WebService(
    serviceName = "NotificationProcessService",
    portName = "NotificationProcessPort",
    targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/NotificationProcess",
    endpointInterface = "com.freddiemac.loanorchestration.notification.NotificationProcessPort"
)
public class NotificationProcessImpl implements NotificationProcessPort {

    @Override
    public NotificationResponse processNotification(NotificationRequest payload) {
        long loanId = payload.getLoanId();
        String applicantName = payload.getApplicantName();
        String decision = payload.getDecision();
        
        System.out.println("[NotificationService] Ingesting notification task for Loan ID: " + loanId);
        System.out.println("[NotificationService] Dispatching email notice to " + applicantName + " about decision: " + decision);
        
        String status = "SUCCESS";
        String message = "Notification email successfully dispatched to applicant: " 
                         + applicantName + ". Subject: Loan Decision: " + decision;
        
        System.out.println("[NotificationService] Email dispatched. Message: " + message);
        return new NotificationResponse(status, message);
    }
}
