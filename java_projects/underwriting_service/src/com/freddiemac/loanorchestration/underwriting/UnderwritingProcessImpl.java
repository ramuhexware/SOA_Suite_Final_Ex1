package com.freddiemac.loanorchestration.underwriting;

import javax.jws.WebService;
import com.freddiemac.loanorchestration.underwriting.types.UnderwritingRequest;
import com.freddiemac.loanorchestration.underwriting.types.UnderwritingResponse;

@WebService(
    serviceName = "UnderwritingProcessService",
    portName = "UnderwritingProcessPort",
    targetNamespace = "http://xmlns.oracle.com/LoanOrchestration/UnderwritingProcess",
    endpointInterface = "com.freddiemac.loanorchestration.underwriting.UnderwritingProcessPort"
)
public class UnderwritingProcessImpl implements UnderwritingProcessPort {

    @Override
    public UnderwritingResponse processUnderwriting(UnderwritingRequest payload) {
        int creditScore = payload.getCreditScore();
        double loanAmount = payload.getLoanAmount();
        double monthlyIncome = payload.getMonthlyIncome();
        double propertyValue = payload.getPropertyValue();

        double ltv = propertyValue > 0 ? (loanAmount / propertyValue) : 0.0;
        // Estimate monthly housing payment as 0.5% of loan amount for simulated DTI
        double estimatedHousingPayment = loanAmount * 0.005;
        double dti = monthlyIncome > 0 ? (estimatedHousingPayment / monthlyIncome) : 0.0;

        System.out.println("[UnderwritingService] Evaluating loan application details:");
        System.out.println("  Credit Score: " + creditScore);
        System.out.printf("  Loan Amount: $%.2f | Property Value: $%.2f (LTV: %.2f)%n", loanAmount, propertyValue, ltv);
        System.out.printf("  Monthly Income: $%.2f (Simulated DTI: %.2f)%n", monthlyIncome, dti);

        String decision;
        String notes;

        // Auto Approved: creditScore >= 700 and LTV < 0.8
        if (creditScore >= 700 && ltv < 0.8) {
            decision = "AUTO_APPROVED";
            notes = "Credit score and Loan-to-Value ratios are optimal. Automatically approved by rules composite.";
        }
        // Auto Rejected: creditScore < 500
        else if (creditScore < 500) {
            decision = "AUTO_REJECTED";
            notes = "Credit score is below minimum threshold (500). Automatically rejected by rules composite.";
        }
        // Borderline -> Manual review queue
        else {
            decision = "MANUAL_REVIEW";
            notes = "Refer to manual underwriter for review due to elevated risk parameters.";
        }

        System.out.println("[UnderwritingService] Evaluation Decision: " + decision);
        System.out.println("[UnderwritingService] Notes: " + notes);
        
        return new UnderwritingResponse(decision, notes, dti, ltv);
    }
}
