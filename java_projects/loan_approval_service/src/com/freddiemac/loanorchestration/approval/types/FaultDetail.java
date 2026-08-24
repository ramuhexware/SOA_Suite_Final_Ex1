package com.freddiemac.loanorchestration.approval.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "errorCode",
    "errorMessage",
    "timestamp"
})
@XmlRootElement(name = "FaultDetail", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class FaultDetail {

    @XmlElement(required = true)
    protected String errorCode;

    @XmlElement(required = true)
    protected String errorMessage;

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;

    public FaultDetail() {}

    public FaultDetail(String errorCode, String errorMessage, Date timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
