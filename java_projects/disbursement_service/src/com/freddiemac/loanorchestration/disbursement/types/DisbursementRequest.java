package com.freddiemac.loanorchestration.disbursement.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loanId",
    "loanAmount"
})
@XmlRootElement(name = "DisbursementRequest", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class DisbursementRequest {

    @XmlElement(required = true)
    protected long loanId;

    @XmlElement(required = true)
    protected double loanAmount;

    public DisbursementRequest() {}

    public DisbursementRequest(long loanId, double loanAmount) {
        this.loanId = loanId;
        this.loanAmount = loanAmount;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }
}
