package com.freddiemac.loanorchestration.approval;

import com.freddiemac.loanorchestration.approval.types.LoanApplicationRequest;
import com.freddiemac.loanorchestration.approval.types.LoanApplicationResponse;

public class LoanApprovalServiceRunner {

    public static void main(String[] args) {
        System.out.println("=========================================================================");
        System.out.println("     VERIFYING OSB SOA SUITE EX5 BPEL ORCHESTRATION IN JAVA");
        System.out.println("=========================================================================");

        LoanApprovalProcessPort orchestrator = new LoanApprovalProcessImpl();

        // SCENARIO 1: AUTO-APPROVAL
        System.out.println("\n--- RUNNING SCENARIO 1: AUTO-APPROVAL (Excellent Credit, Low LTV) ---");
        LoanApplicationRequest req1 = new LoanApplicationRequest(
            "Alice Smith", "111-222-3333", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            LoanApplicationResponse res1 = orchestrator.initiateLoan(req1);
            printResponse(res1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SCENARIO 2: SECURITY SANCTION LIST WATCHLIST BLOCK
        System.out.println("\n--- RUNNING SCENARIO 2: SECURITY WATCHLIST HIT (OFAC Block) ---");
        LoanApplicationRequest req2 = new LoanApplicationRequest(
            "Lord Voldemort", "111-222-3333", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            orchestrator.initiateLoan(req2);
        } catch (SanctionListFaultException e) {
            System.out.println("[RESULT] Caught Expected WebFault: " + e.getMessage());
            System.out.println("[RESULT] Fault Code: " + e.getFaultInfo().getErrorCode());
            System.out.println("[RESULT] Fault Message: " + e.getFaultInfo().getErrorMessage());
            System.out.println("[RESULT] Timestamp: " + e.getFaultInfo().getTimestamp());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SCENARIO 3: FRAUD WATCHLIST MATCH & SAGA COMPENSATION (Appraisal Refund)
        System.out.println("\n--- RUNNING SCENARIO 3: FRAUD WATCHLIST MATCH (Ends in 9999 -> Saga Compensation) ---");
        LoanApplicationRequest req3 = new LoanApplicationRequest(
            "Bob Miller", "111-222-9999", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            LoanApplicationResponse res3 = orchestrator.initiateLoan(req3);
            printResponse(res3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SCENARIO 4: CREDIT SERVICE DOWN SYSTEM FAULT
        System.out.println("\n--- RUNNING SCENARIO 4: SYSTEM FAULT (Credit Service Down) ---");
        LoanApplicationRequest req4 = new LoanApplicationRequest(
            "Charlie Brown", "000-00-0000", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            LoanApplicationResponse res4 = orchestrator.initiateLoan(req4);
            printResponse(res4);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SCENARIO 5: AUTO REJECTION (Low Credit score)
        System.out.println("\n--- RUNNING SCENARIO 5: AUTO-REJECTION (Credit Score < 500) ---");
        LoanApplicationRequest req5 = new LoanApplicationRequest(
            "David Jones", "999-12-3456", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            LoanApplicationResponse res5 = orchestrator.initiateLoan(req5);
            printResponse(res5);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // SCENARIO 6: PENDING MANUAL UNDERWRITING REVIEW (Borderline case)
        System.out.println("\n--- RUNNING SCENARIO 6: MANUAL UNDERWRITING REVIEW (Credit Score 620) ---");
        LoanApplicationRequest req6 = new LoanApplicationRequest(
            "Emily Watson", "888-12-3456", 250000.0, 12000.0, "123 Maple Dr, McLean, VA", 400000.0
        );
        try {
            LoanApplicationResponse res6 = orchestrator.initiateLoan(req6);
            printResponse(res6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n=========================================================================");
    }

    private static void printResponse(LoanApplicationResponse res) {
        System.out.println("[RESULT] Loan ID: " + res.getLoanId());
        System.out.println("[RESULT] Process Status: " + res.getStatus());
        System.out.println("[RESULT] Rules Decision: " + res.getDecision());
        System.out.println("[RESULT] Decision Notes: " + res.getDecisionNotes());
    }
}
