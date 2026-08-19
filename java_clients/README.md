# Java Clients for SOA Suite

This module contains Java client applications that call INTO the Oracle SOA Suite via WSDL contracts.

## Purpose

These clients demonstrate the **producer/caller** side of the SOA integration:

```
Java Client → WSDL Contract → SOA Suite SERVICE_ENTRY → COMPOSITE → BPEL
```

## Client Files

| Client | WSDL Contract | Operation | Endpoint |
|--------|--------------|-----------|----------|
| `LoanApprovalClient.java` | LoanApprovalProcess.wsdl | `initiateLoan` | localhost:8081 |
| `CreditProcessClient.java` | CreditProcess.wsdl | `processCreditCheck` | localhost:8082 |
| `ValuationProcessClient.java` | ValuationProcess.wsdl | `appraise`, `refund` | localhost:8083 |
| `UnderwritingProcessClient.java` | UnderwritingProcess.wsdl | `processUnderwriting` | localhost:8084 |
| `DisbursementProcessClient.java` | DisbursementProcess.wsdl | `processDisbursement` | localhost:8085 |
| `NotificationProcessClient.java` | NotificationProcess.wsdl | `processNotification` | localhost:8086 |
| `ClientRunner.java` | All | Demo runner | All |

## JAX-WS Client Pattern

Each client uses the standard JAX-WS dynamic proxy pattern:

```java
// 1. Create service from WSDL
URL wsdlURL = new URL("http://localhost:8081/soap/LoanApprovalProcess?wsdl");
QName serviceName = new QName(NAMESPACE, SERVICE_NAME);
Service service = Service.create(wsdlURL, serviceName);

// 2. Get port (proxy to WSDL portType)
LoanApprovalProcessPort port = service.getPort(LoanApprovalProcessPort.class);

// 3. Call operation
LoanApplicationResponse response = port.initiateLoan(request);
```

## Build and Run

### Using PowerShell Script

```powershell
cd java_clients
.\build_and_run.ps1
```

### Manual Build

```bash
# Compile (from java_clients directory)
javac -d out -sourcepath "src;../java_projects/shared_stubs/src;../java_projects/*/src" \
    src/com/freddiemac/loanorchestration/clients/*.java

# Run ClientRunner
java -cp out com.freddiemac.loanorchestration.clients.ClientRunner

# Run individual client
java -cp out com.freddiemac.loanorchestration.clients.LoanApprovalClient
```

## Dependencies

These clients reuse interfaces and types from `../java_projects/`:

- `*ProcessPort.java` - @WebService interfaces (define WSDL contract in Java)
- `types/*.java` - Request/Response DTOs

## Call Chain Visualization

```
┌─────────────────────────────────────────────────────────────────┐
│  LoanApprovalClient.java                                        │
│    ↓ Service.create() + getPort()                               │
│    ↓ port.initiateLoan(request)                                 │
└─────────────────────────────────────────────────────────────────┘
                              ↓ SOAP/HTTP
┌─────────────────────────────────────────────────────────────────┐
│  LoanApprovalProcess.wsdl                                       │
│    portType: LoanApprovalProcess                                │
│    operation: initiateLoan                                      │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│  composite.xml → SERVICE_ENTRY → COMPOSITE → BPEL               │
│    LoanApprovalProcess.bpel orchestrates calls to:              │
│    - CreditProcess.wsdl                                         │
│    - ValuationProcess.wsdl                                      │
│    - UnderwritingProcess.wsdl                                   │
│    - DisbursementProcess.wsdl                                   │
│    - NotificationProcess.wsdl                                   │
└─────────────────────────────────────────────────────────────────┘
                              ↓
┌─────────────────────────────────────────────────────────────────┐
│  Java Providers (@WebService implementations)                   │
│    CreditProcessImpl.java                                       │
│    ValuationProcessImpl.java                                    │
│    etc.                                                         │
└─────────────────────────────────────────────────────────────────┘
```

## Parser Detection

For CodeScout to detect and link these Java clients to WSDL operations, it will look for:

1. `Service.create(wsdlURL, serviceName)` - Links to WSDL file
2. `service.getPort(PortType.class)` - Links to WSDL portType
3. `port.operationName(...)` - Links to WSDL operation
4. WSDL URL constants - Identifies target endpoint
5. QName namespace - Matches WSDL targetNamespace
