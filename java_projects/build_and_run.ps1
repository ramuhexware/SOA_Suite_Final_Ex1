# PowerShell build and verification script for Ex5 Java projects

Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "  Compiling all Java projects..." -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan

# Ensure bin directory exists and is clean
if (Test-Path bin) {
    Remove-Item -Recurse -Force bin
}
New-Item -ItemType Directory -Force -Path bin | Out-Null

# Find all Java source files recursively across all services and shared stubs
$javaSources = Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName }

if ($javaSources.Count -eq 0) {
    Write-Error "No Java source files found!"
    Exit 1
}

# Compile all files into the bin directory
javac -d bin $javaSources

if ($LASTEXITCODE -ne 0) {
    Write-Error "Compilation FAILED!"
    Exit 1
}

Write-Host "Compilation successful!" -ForegroundColor Green
Write-Host ""

# Run Standalone Sub-Service Runners
Write-Host "==================================================" -ForegroundColor Yellow
Write-Host "  Executing Standalone Sub-Service Tests..." -ForegroundColor Yellow
Write-Host "==================================================" -ForegroundColor Yellow

Write-Host "Running Credit Service Test..." -ForegroundColor DarkYellow
java -cp bin com.freddiemac.loanorchestration.credit.CreditServiceRunner

Write-Host "Running Valuation Service Test..." -ForegroundColor DarkYellow
java -cp bin com.freddiemac.loanorchestration.valuation.ValuationServiceRunner

Write-Host "Running Underwriting Service Test..." -ForegroundColor DarkYellow
java -cp bin com.freddiemac.loanorchestration.underwriting.UnderwritingServiceRunner

Write-Host "Running Disbursement Service Test..." -ForegroundColor DarkYellow
java -cp bin com.freddiemac.loanorchestration.disbursement.DisbursementServiceRunner

Write-Host "Running Notification Service Test..." -ForegroundColor DarkYellow
java -cp bin com.freddiemac.loanorchestration.notification.NotificationServiceRunner

# Run Core Orchestration Runner
Write-Host "==================================================" -ForegroundColor Magenta
Write-Host "  Executing Core Loan Approval Orchestrator..." -ForegroundColor Magenta
Write-Host "==================================================" -ForegroundColor Magenta

java -cp bin com.freddiemac.loanorchestration.approval.LoanApprovalServiceRunner

Write-Host "==================================================" -ForegroundColor Cyan
Write-Host "  Verification Complete!" -ForegroundColor Cyan
Write-Host "==================================================" -ForegroundColor Cyan
