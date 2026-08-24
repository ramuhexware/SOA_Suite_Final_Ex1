package com.freddiemac.loanorchestration.credit.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ssn"
})
@XmlRootElement(name = "CreditScoreRequest", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class CreditScoreRequest {

    @XmlElement(required = true)
    protected String ssn;

    public CreditScoreRequest() {}

    public CreditScoreRequest(String ssn) {
        this.ssn = ssn;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
