package com.freddiemac.loanorchestration.approval;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import com.freddiemac.loanorchestration.approval.types.LoanApplicationRequest;
import com.freddiemac.loanorchestration.approval.types.LoanApplicationResponse;

@WebService(name = "LoanApprovalProcess", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/LoanApprovalProcess")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface LoanApprovalProcessPort {

    @WebMethod(action = "initiateLoan")
    @WebResult(name = "LoanApplicationResponse", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
    public LoanApplicationResponse initiateLoan(
        @WebParam(name = "LoanApplicationRequest", targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/types", partName = "payload")
        LoanApplicationRequest payload
    ) throws SanctionListFaultException;
}
