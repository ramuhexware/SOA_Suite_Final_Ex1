package com.freddiemac.loanorchestration.credit;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.credit.types.CreditScoreRequest;
import com.freddiemac.loanorchestration.credit.types.CreditScoreResponse;

@WebService(name = "CreditProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/CreditProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface CreditProcessPort {

    @WebMethod(action = "processCreditCheck")
    @WebResult(name = "CreditScoreResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public CreditScoreResponse processCreditCheck(
        @WebParam(name = "CreditScoreRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        CreditScoreRequest payload
    );
}
