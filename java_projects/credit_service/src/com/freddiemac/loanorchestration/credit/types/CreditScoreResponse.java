package com.freddiemac.loanorchestration.credit.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "creditScore"
})
@XmlRootElement(name = "CreditScoreResponse", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class CreditScoreResponse {

    @XmlElement(required = true)
    protected int creditScore;

    public CreditScoreResponse() {}

    public CreditScoreResponse(int creditScore) {
        this.creditScore = creditScore;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
