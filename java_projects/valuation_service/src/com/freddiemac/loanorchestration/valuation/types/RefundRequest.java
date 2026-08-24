package com.freddiemac.loanorchestration.valuation.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyAddress"
})
@XmlRootElement(name = "RefundRequest", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class RefundRequest {

    @XmlElement(required = true)
    protected String propertyAddress;

    public RefundRequest() {}

    public RefundRequest(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }
}
