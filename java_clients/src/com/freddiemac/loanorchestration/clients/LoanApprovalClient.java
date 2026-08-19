package com.freddiemac.loanorchestration.clients;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

import com.freddiemac.loanorchestration.approval.LoanApprovalProcessPort;
import com.freddiemac.loanorchestration.approval.SanctionListFaultException;
import com.freddiemac.loanorchestration.approval.types.LoanApplicationRequest;
import com.freddiemac.loanorchestration.approval.types.LoanApplicationResponse;

/**
 * JAX-WS client that calls LoanApprovalProcess SOA composite via WSDL.
 * This is the main entry point that triggers the entire BPEL orchestration.
 *
 * WSDL Contract: loan-service-soa/LoanApprovalProcess.wsdl
 * Service: loanapproval_client_ep
 * PortType: LoanApprovalProcess
 * Operation: initiateLoan
 * Endpoint: http://localhost:8081/soap/LoanApprovalProcess
 */
public class LoanApprovalClient {

    private static final String WSDL_URL = "http://localhost:8081/soap/LoanApprovalProcess?wsdl";
    private static final String NAMESPACE = "http://xmlns.oracle.com/LoanOrchestration/LoanApprovalProcess";
    private static final String SERVICE_NAME = "loanapproval_client_ep";

    private final LoanApprovalProcessPort port;

    public LoanApprovalClient() throws Exception {
        URL wsdlURL = new URL(WSDL_URL);
        QName serviceName = new QName(NAMESPACE, SERVICE_NAME);
        Service service = Service.create(wsdlURL, serviceName);
        this.port = service.getPort(LoanApprovalProcessPort.class);
    }

    public LoanApprovalClient(String wsdlUrl) throws Exception {
        URL wsdlURL = new URL(wsdlUrl);
        QName serviceName = new QName(NAMESPACE, SERVICE_NAME);
        Service service = Service.create(wsdlURL, serviceName);
        this.port = service.getPort(LoanApprovalProcessPort.class);
    }

    /**
     * Calls the SOA Suite LoanApprovalProcess.initiateLoan operation.
     * This triggers the BPEL orchestration which calls:
     * - CreditProcess.processCreditCheck
     * - ValuationProcess.appraise
     * - UnderwritingProcess.processUnderwriting
     * - DisbursementProcess.processDisbursement (if approved)
     * - NotificationProcess.processNotification
     */
    public LoanApplicationResponse initiateLoan(LoanApplicationRequest request)
            throws SanctionListFaultException {
        System.out.println("[LoanApprovalClient] Calling SOA Suite: initiateLoan");
        System.out.println("[LoanApprovalClient] WSDL: " + WSDL_URL);
        System.out.println("[LoanApprovalClient] Applicant: " + request.getApplicantName());
        System.out.println("[LoanApprovalClient] Loan Amount: $" + request.getLoanAmount());

        LoanApplicationResponse response = port.initiateLoan(request);

        System.out.println("[LoanApprovalClient] Response received from SOA Suite");
        return response;
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================");
        System.out.println("     LOAN APPROVAL CLIENT - Calling SOA Suite via WSDL");
        System.out.println("=========================================================================");
        System.out.println("Target WSDL: " + WSDL_URL);
        System.out.println("Target Namespace: " + NAMESPACE);
        System.out.println("Service Name: " + SERVICE_NAME);
        System.out.println("=========================================================================");

        try {
            LoanApprovalClient client = new LoanApprovalClient();

            // Test Case 1: Standard loan application (should be AUTO_APPROVED)
            System.out.println("\n--- TEST CASE 1: Standard Application (Expected: AUTO_APPROVED) ---");
            LoanApplicationRequest request1 = new LoanApplicationRequest(
                "John Doe",           // applicantName
                "123-45-6789",        // ssn
                250000.0,             // loanAmount
                12000.0,              // monthlyIncome
                "123 Main St, VA",    // propertyAddress
                400000.0              // propertyValue
            );

            LoanApplicationResponse response1 = client.initiateLoan(request1);
            printResponse(response1);

            // Test Case 2: Low credit score (should be AUTO_REJECTED)
            System.out.println("\n--- TEST CASE 2: Low Credit Score (Expected: AUTO_REJECTED) ---");
            LoanApplicationRequest request2 = new LoanApplicationRequest(
                "Jane Smith",
                "999-12-3456",        // SSN starting with 999 triggers low credit
                250000.0,
                12000.0,
                "456 Oak Ave, MD",
                400000.0
            );

            LoanApplicationResponse response2 = client.initiateLoan(request2);
            printResponse(response2);

        } catch (SanctionListFaultException e) {
            System.out.println("[LoanApprovalClient] SANCTION FAULT: " + e.getMessage());
            System.out.println("  Error Code: " + e.getFaultInfo().getErrorCode());
            System.out.println("  Error Message: " + e.getFaultInfo().getErrorMessage());
        } catch (Exception e) {
            System.err.println("[LoanApprovalClient] Error connecting to SOA Suite: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n=========================================================================");
    }

    private static void printResponse(LoanApplicationResponse response) {
        System.out.println("[RESPONSE] Loan ID: " + response.getLoanId());
        System.out.println("[RESPONSE] Status: " + response.getStatus());
        System.out.println("[RESPONSE] Decision: " + response.getDecision());
        System.out.println("[RESPONSE] Notes: " + response.getDecisionNotes());
    }
}
