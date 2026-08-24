package com.freddiemac.loanorchestration.underwriting;

import com.freddiemac.loanorchestration.underwriting.types.UnderwritingRequest;
import com.freddiemac.loanorchestration.underwriting.types.UnderwritingResponse;

public class UnderwritingServiceRunner {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("   VERIFYING STANDALONE UNDERWRITING SERVICE");
        System.out.println("==================================================");

        UnderwritingProcessPort service = new UnderwritingProcessImpl();

        // 1. Test Auto Approved
        System.out.println("\n--- Test Case 1: Auto Approval (Score 720, LTV 0.75) ---");
        UnderwritingResponse res1 = service.processUnderwriting(new UnderwritingRequest(720, 300000.0, 10000.0, 400000.0));
        System.out.println("Decision: " + res1.getDecision());
        System.out.println("Notes: " + res1.getNotes());

        // 2. Test Auto Rejected
        System.out.println("\n--- Test Case 2: Auto Rejection (Score 450) ---");
        UnderwritingResponse res2 = service.processUnderwriting(new UnderwritingRequest(450, 300000.0, 10000.0, 400000.0));
        System.out.println("Decision: " + res2.getDecision());
        System.out.println("Notes: " + res2.getNotes());

        // 3. Test Manual Review
        System.out.println("\n--- Test Case 3: Manual Review (Score 620) ---");
        UnderwritingResponse res3 = service.processUnderwriting(new UnderwritingRequest(620, 300000.0, 10000.0, 400000.0));
        System.out.println("Decision: " + res3.getDecision());
        System.out.println("Notes: " + res3.getNotes());

        System.out.println("\n==================================================");
    }
}
