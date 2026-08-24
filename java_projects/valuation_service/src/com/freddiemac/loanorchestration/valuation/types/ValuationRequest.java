package com.freddiemac.loanorchestration.valuation.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyAddress",
    "estimatedValue"
})
@XmlRootElement(name = "ValuationRequest", namespace = "http://xmlns.oracle.com/LoanOrchestration/types")
public class ValuationRequest {

    @XmlElement(required = true)
    protected String propertyAddress;

    @XmlElement(required = true)
    protected double estimatedValue;

    public ValuationRequest() {}

    public ValuationRequest(String propertyAddress, double estimatedValue) {
        this.propertyAddress = propertyAddress;
        this.estimatedValue = estimatedValue;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public double getEstimatedValue() {
        return estimatedValue;
    }

    public void setEstimatedValue(double estimatedValue) {
        this.estimatedValue = estimatedValue;
    }
}
