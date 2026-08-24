package com.freddiemac.loanorchestration.valuation;

import javax.jws.WebService;
import com.freddiemac.loanorchestration.valuation.types.ValuationRequest;
import com.freddiemac.loanorchestration.valuation.types.ValuationResponse;
import com.freddiemac.loanorchestration.valuation.types.RefundRequest;
import com.freddiemac.loanorchestration.valuation.types.RefundResponse;

@WebService(
    serviceName = "ValuationProcessService",
    portName = "ValuationProcessPort",
    targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/ValuationProcess",
    endpointInterface = "com.freddiemac.loanorchestration.valuation.ValuationProcessPort"
)
public class ValuationProcessImpl implements ValuationProcessPort {

    @Override
    public ValuationResponse appraise(ValuationRequest payload) {
        double estimatedValue = payload.getEstimatedValue();
        String address = payload.getPropertyAddress();
        
        System.out.println("[ValuationService] Running appraisal for property: " + address);
        System.out.println("[ValuationService] Estimated value: $" + estimatedValue);
        
        // BPEL assigns appraisedValue = estimatedValue
        double appraisedValue = estimatedValue;
        String status = "APPRAISED";
        
        System.out.println("[ValuationService] Appraisal completed. Value: $" + appraisedValue);
        return new ValuationResponse(appraisedValue, status);
    }

    @Override
    public RefundResponse refund(RefundRequest payload) {
        String address = payload.getPropertyAddress();
        System.out.println("[ValuationService] Processing refund for property: " + address);
        
        String status = "REFUNDED";
        String message = "Appraisal fee refunded for address: " + address;
        
        System.out.println("[ValuationService] Saga Compensation Refund completed: " + message);
        return new RefundResponse(status, message);
    }
}
