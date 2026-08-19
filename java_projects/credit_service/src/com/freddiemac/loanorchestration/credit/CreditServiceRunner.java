package com.freddiemac.loanorchestration.credit;

import com.freddiemac.loanorchestration.credit.types.CreditScoreRequest;
import com.freddiemac.loanorchestration.credit.types.CreditScoreResponse;

public class CreditServiceRunner {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   VERIFYING STANDALONE CREDIT SERVICE");
        System.out.println("==================================================");

        CreditProcessPort service = new CreditProcessImpl();

        // Scenario 1: Standard / Excellent Credit score
        System.out.println("\n--- Test Case 1: Standard SSN (Expected: 720) ---");
        CreditScoreResponse res1 = service.processCreditCheck(new CreditScoreRequest("123-45-6789"));
        System.out.println("Result Score: " + res1.getCreditScore());

        // Scenario 2: Bad Credit score
        System.out.println("\n--- Test Case 2: SSN starting with 999 (Expected: 450) ---");
        CreditScoreResponse res2 = service.processCreditCheck(new CreditScoreRequest("999-12-3456"));
        System.out.println("Result Score: " + res2.getCreditScore());

        // Scenario 3: Mid Credit score
        System.out.println("\n--- Test Case 3: SSN starting with 888 (Expected: 620) ---");
        CreditScoreResponse res3 = service.processCreditCheck(new CreditScoreRequest("888-12-3456"));
        System.out.println("Result Score: " + res3.getCreditScore());

        System.out.println("\n==================================================");
    }
}
