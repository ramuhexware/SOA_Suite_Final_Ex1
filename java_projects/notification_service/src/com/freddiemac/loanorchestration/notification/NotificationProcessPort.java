package com.freddiemac.loanorchestration.notification;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.notification.types.NotificationRequest;
import com.freddiemac.loanorchestration.notification.types.NotificationResponse;

@WebService(name = "NotificationProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/NotificationProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface NotificationProcessPort {

    @WebMethod(action = "processNotification")
    @WebResult(name = "NotificationResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public NotificationResponse processNotification(
        @WebParam(name = "NotificationRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        NotificationRequest payload
    );
}
