package com.freddiemac.loanorchestration.credit;

import javax.jws.WebService;
import com.freddiemac.loanorchestration.credit.types.CreditScoreRequest;
import com.freddiemac.loanorchestration.credit.types.CreditScoreResponse;

@WebService(
    serviceName = "CreditProcessService",
    portName = "CreditProcessPort",
    targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/CreditProcess",
    endpointInterface = "com.freddiemac.loanorchestration.credit.CreditProcessPort"
)
public class CreditProcessImpl implements CreditProcessPort {

    @Override
    public CreditScoreResponse processCreditCheck(CreditScoreRequest payload) {
        String ssn = payload.getSsn();
        int score = 720; // Default mock score from BPEL literal

        System.out.println("[CreditService] Checking credit for SSN: " + ssn);
        
        if (ssn != null) {
            // Remove dashes for uniform checking
            String cleanSsn = ssn.replace("-", "").trim();
            if (cleanSsn.startsWith("999")) {
                score = 450; // Simulate auto-reject (credit < 500)
                System.out.println("[CreditService] Low credit profile detected. Score: " + score);
            } else if (cleanSsn.startsWith("888")) {
                score = 620; // Simulate manual review (500 <= credit < 700)
                System.out.println("[CreditService] Mid-range credit profile detected. Score: " + score);
            } else if (cleanSsn.equals("000000000")) {
                System.out.println("[CreditService] Simulating Credit Service Down System Fault!");
                throw new RuntimeException("System Fault: CreditServiceDownFault");
            } else {
                System.out.println("[CreditService] Excellent credit profile detected. Score: " + score);
            }
        }
        
        return new CreditScoreResponse(score);
    }
}
