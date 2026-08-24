package com.freddiemac.loanorchestration.valuation;

import com.freddiemac.loanorchestration.valuation.types.ValuationRequest;
import com.freddiemac.loanorchestration.valuation.types.ValuationResponse;
import com.freddiemac.loanorchestration.valuation.types.RefundRequest;
import com.freddiemac.loanorchestration.valuation.types.RefundResponse;

public class ValuationServiceRunner {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   VERIFYING STANDALONE VALUATION SERVICE");
        System.out.println("==================================================");

        ValuationProcessPort service = new ValuationProcessImpl();

        // 1. Test appraise
        System.out.println("\n--- Test Case 1: Appraise Property (Expected: APPRAISED, $450000.00) ---");
        ValuationResponse valRes = service.appraise(new ValuationRequest("123 Maple Dr, McLean, VA", 450000.0));
        System.out.println("Status: " + valRes.getStatus());
        System.out.println("Appraised Value: $" + valRes.getAppraisedValue());

        // 2. Test refund
        System.out.println("\n--- Test Case 2: Refund Property Fee (Expected: REFUNDED) ---");
        RefundResponse refRes = service.refund(new RefundRequest("123 Maple Dr, McLean, VA"));
        System.out.println("Status: " + refRes.getStatus());
        System.out.println("Message: " + refRes.getMessage());

        System.out.println("\n==================================================");
    }
}
