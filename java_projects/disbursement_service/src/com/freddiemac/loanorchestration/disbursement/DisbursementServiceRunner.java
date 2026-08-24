package com.freddiemac.loanorchestration.disbursement;

import com.freddiemac.loanorchestration.disbursement.types.DisbursementRequest;
import com.freddiemac.loanorchestration.disbursement.types.DisbursementResponse;

public class DisbursementServiceRunner {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   VERIFYING STANDALONE DISBURSEMENT SERVICE");
        System.out.println("==================================================");

        DisbursementProcessPort service = new DisbursementProcessImpl();

        System.out.println("\n--- Test Case 1: Disburse Loan #1003 ($300000.00) ---");
        DisbursementResponse res = service.processDisbursement(new DisbursementRequest(1003L, 300000.0));
        System.out.println("Status: " + res.getStatus());
        System.out.println("Message: " + res.getMessage());

        System.out.println("\n==================================================");
    }
}
