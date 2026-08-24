package com.freddiemac.loanorchestration.underwriting;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.underwriting.types.UnderwritingRequest;
import com.freddiemac.loanorchestration.underwriting.types.UnderwritingResponse;

@WebService(name = "UnderwritingProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/UnderwritingProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface UnderwritingProcessPort {

    @WebMethod(action = "processUnderwriting")
    @WebResult(name = "UnderwritingResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public UnderwritingResponse processUnderwriting(
        @WebParam(name = "UnderwritingRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        UnderwritingRequest payload
    );
}
