package com.freddiemac.loanorchestration.valuation;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.valuation.types.ValuationRequest;
import com.freddiemac.loanorchestration.valuation.types.ValuationResponse;
import com.freddiemac.loanorchestration.valuation.types.RefundRequest;
import com.freddiemac.loanorchestration.valuation.types.RefundResponse;

@WebService(name = "ValuationProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/ValuationProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ValuationProcessPort {

    @WebMethod(action = "appraise")
    @WebResult(name = "ValuationResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public ValuationResponse appraise(
        @WebParam(name = "ValuationRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        ValuationRequest payload
    );

    @WebMethod(action = "refund")
    @WebResult(name = "RefundResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public RefundResponse refund(
        @WebParam(name = "RefundRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        RefundRequest payload
    );
}
