# Java Clients Build and Run Script
# Compiles and runs the SOA Suite client demos

$ErrorActionPreference = "Stop"

Write-Host "=========================================="
Write-Host "  Java Clients - Build and Run Script"
Write-Host "=========================================="
Write-Host ""

# Set paths
$projectRoot = Split-Path -Parent $PSScriptRoot
$clientsRoot = $PSScriptRoot
$javaProjectsRoot = Join-Path $projectRoot "java_projects"
$srcPath = Join-Path $clientsRoot "src"
$outPath = Join-Path $clientsRoot "out"

# Create output directory
if (-not (Test-Path $outPath)) {
    New-Item -ItemType Directory -Path $outPath | Out-Null
}

Write-Host "Project Root: $projectRoot"
Write-Host "Clients Source: $srcPath"
Write-Host "Java Projects (shared): $javaProjectsRoot"
Write-Host "Output: $outPath"
Write-Host ""

# Build classpath from java_projects
$sharedStubs = Join-Path $javaProjectsRoot "shared_stubs\src"
$creditService = Join-Path $javaProjectsRoot "credit_service\src"
$valuationService = Join-Path $javaProjectsRoot "valuation_service\src"
$underwritingService = Join-Path $javaProjectsRoot "underwriting_service\src"
$disbursementService = Join-Path $javaProjectsRoot "disbursement_service\src"
$notificationService = Join-Path $javaProjectsRoot "notification_service\src"
$loanApprovalService = Join-Path $javaProjectsRoot "loan_approval_service\src"

$classpath = "$srcPath;$sharedStubs;$creditService;$valuationService;$underwritingService;$disbursementService;$notificationService;$loanApprovalService;$outPath"

Write-Host "=========================================="
Write-Host "  Step 1: Compiling shared types..."
Write-Host "=========================================="

# Compile shared stubs and types first
$javacCmd = "javac -d `"$outPath`" -sourcepath `"$classpath`" " +
    "`"$sharedStubs\javax\jws\*.java`" " +
    "`"$sharedStubs\javax\jws\soap\*.java`" " +
    "`"$sharedStubs\javax\xml\bind\annotation\*.java`" " +
    "`"$sharedStubs\javax\xml\ws\*.java`""

Write-Host "Compiling: javax.jws, javax.xml.bind, javax.xml.ws"
Invoke-Expression $javacCmd

Write-Host ""
Write-Host "=========================================="
Write-Host "  Step 2: Compiling service types..."
Write-Host "=========================================="

# Compile service types
$serviceTypes = @(
    "$creditService\com\freddiemac\loanorchestration\credit\types\*.java",
    "$valuationService\com\freddiemac\loanorchestration\valuation\types\*.java",
    "$underwritingService\com\freddiemac\loanorchestration\underwriting\types\*.java",
    "$disbursementService\com\freddiemac\loanorchestration\disbursement\types\*.java",
    "$notificationService\com\freddiemac\loanorchestration\notification\types\*.java",
    "$loanApprovalService\com\freddiemac\loanorchestration\approval\types\*.java"
)

foreach ($typeDir in $serviceTypes) {
    if (Test-Path (Split-Path $typeDir)) {
        Write-Host "Compiling: $typeDir"
        javac -d "$outPath" -cp "$outPath" -sourcepath "$classpath" $typeDir 2>$null
    }
}

Write-Host ""
Write-Host "=========================================="
Write-Host "  Step 3: Compiling service ports..."
Write-Host "=========================================="

# Compile service ports
$servicePorts = @(
    "$creditService\com\freddiemac\loanorchestration\credit\CreditProcessPort.java",
    "$valuationService\com\freddiemac\loanorchestration\valuation\ValuationProcessPort.java",
    "$underwritingService\com\freddiemac\loanorchestration\underwriting\UnderwritingProcessPort.java",
    "$disbursementService\com\freddiemac\loanorchestration\disbursement\DisbursementProcessPort.java",
    "$notificationService\com\freddiemac\loanorchestration\notification\NotificationProcessPort.java",
    "$loanApprovalService\com\freddiemac\loanorchestration\approval\LoanApprovalProcessPort.java",
    "$loanApprovalService\com\freddiemac\loanorchestration\approval\SanctionListFaultException.java"
)

foreach ($port in $servicePorts) {
    if (Test-Path $port) {
        Write-Host "Compiling: $(Split-Path -Leaf $port)"
        javac -d "$outPath" -cp "$outPath" -sourcepath "$classpath" $port 2>$null
    }
}

Write-Host ""
Write-Host "=========================================="
Write-Host "  Step 4: Compiling client classes..."
Write-Host "=========================================="

# Compile clients
javac -d "$outPath" -cp "$outPath" -sourcepath "$classpath" "$srcPath\com\freddiemac\loanorchestration\clients\*.java"

Write-Host ""
Write-Host "=========================================="
Write-Host "  Build Complete!"
Write-Host "=========================================="
Write-Host ""

# Run LoanApprovalClient
$clientClass = "com.freddiemac.loanorchestration.clients.LoanApprovalClient"

Write-Host ""
Write-Host "=========================================="
Write-Host "  Running: $clientClass"
Write-Host "=========================================="
Write-Host ""
java -cp "$outPath" $clientClass

Write-Host ""
Write-Host "Done."
