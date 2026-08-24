package com.freddiemac.loanorchestration.disbursement;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.disbursement.types.DisbursementRequest;
import com.freddiemac.loanorchestration.disbursement.types.DisbursementResponse;

@WebService(name = "DisbursementProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/DisbursementProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface DisbursementProcessPort {

    @WebMethod(action = "processDisbursement")
    @WebResult(name = "DisbursementResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public DisbursementResponse processDisbursement(
        @WebParam(name = "DisbursementRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        DisbursementRequest payload
    );
}
