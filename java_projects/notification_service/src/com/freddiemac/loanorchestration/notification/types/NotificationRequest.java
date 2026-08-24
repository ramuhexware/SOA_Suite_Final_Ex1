package com.freddiemac.loanorchestration.notification.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loanId",
    "applicantName",
    "decision"
})
@XmlRootElement(name = "NotificationRequest", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class NotificationRequest {

    @XmlElement(required = true)
    protected long loanId;

    @XmlElement(required = true)
    protected String applicantName;

    @XmlElement(required = true)
    protected String decision;

    public NotificationRequest() {}

    public NotificationRequest(long loanId, String applicantName, String decision) {
        this.loanId = loanId;
        this.applicantName = applicantName;
        this.decision = decision;
    }

    public long getLoanId() {
        return loanId;
    }

    public void setLoanId(long loanId) {
        this.loanId = loanId;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }
}
