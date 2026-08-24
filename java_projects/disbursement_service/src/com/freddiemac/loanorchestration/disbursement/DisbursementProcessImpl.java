package com.freddiemac.loanorchestration.disbursement;

import javax.jws.WebService;
import com.freddiemac.loanorchestration.disbursement.types.DisbursementRequest;
import com.freddiemac.loanorchestration.disbursement.types.DisbursementResponse;

@WebService(
    serviceName = "DisbursementProcessService",
    portName = "DisbursementProcessPort",
    targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/DisbursementProcess",
    endpointInterface = "com.freddiemac.loanorchestration.disbursement.DisbursementProcessPort"
)
public class DisbursementProcessImpl implements DisbursementProcessPort {

    @Override
    public DisbursementResponse processDisbursement(DisbursementRequest payload) {
        long loanId = payload.getLoanId();
        double loanAmount = payload.getLoanAmount();
        
        System.out.println("[DisbursementService] Ingesting disbursement request for Loan ID: " + loanId);
        System.out.println("[DisbursementService] Processing account posting of amount: $" + loanAmount);
        
        String status = "SUCCESS";
        String message = "Funds posted to account. Amount disbursed: $" + loanAmount;
        
        System.out.println("[DisbursementService] Posting complete. Message: " + message);
        return new DisbursementResponse(status, message);
    }
}
