package com.freddiemac.loanorchestration.approval;

import javax.xml.ws.WebFault;
import com.freddiemac.loanorchestration.approval.types.FaultDetail;

@WebFault(name = "FaultDetail", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class SanctionListFaultException extends Exception {
    
    private final FaultDetail faultInfo;

    public SanctionListFaultException(String message, FaultDetail faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    public SanctionListFaultException(String message, FaultDetail faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    public FaultDetail getFaultInfo() {
        return faultInfo;
    }
}
