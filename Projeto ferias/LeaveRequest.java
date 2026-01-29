import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.ZonedDateTime;
import java.time.ZoneId;

public abstract class LeaveRequest {

    protected int requestId;
    protected Employee employee;
    protected String status; 
    protected String leaveType;
    private LocalDateTime deniedAt;
    protected LocalDate startDate;
    protected LocalDate endDate;
    protected LocalDateTime createdAt;
    protected LocalDateTime decisionAt; 
    protected ZonedDateTime approvalAt;

    //constructor 

    public LeaveRequest(int requestId, Employee employee, LocalDate startDate, LocalDate endDate, String leaveType) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("StartDate or EndDate cannot be null");
        }

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date");
        }
        
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "PENDING";
        this.leaveType = leaveType;
        this.createdAt = LocalDateTime.now();
        //padrao
    }

    public int getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getStatus() {
        return status;
    }

    public long getNumberOfDays() {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    //metodos simples 

    public void approve() {
        status = "Approved";
        approvalAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void deny() {
        status = "Denied";
        approvalAt = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public void approve1() {
        this.status = "APPROVED";
        this.decisionAt = LocalDateTime.now();
    }

    public void reject() {
        this.status = "REJECTED";
        this.decisionAt = LocalDateTime.now();
    }

    public String getLeaveType() {  
        return leaveType;
    }

    public String getCreatedAtFormatted() {
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return createdAt.format(formatter);
    }

    public String getDecisionAtFormatted() {
        if (decisionAt == null) {
            return "Ainda não decidido";
        }
        DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return decisionAt.format(formatter);
    }

    public String getCreationTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return createdAt.format(formatter);
    }

    public String getApprovalTimestamp() {
        if (approvalAt == null) return "Not approved yet";
        return approvalAt.toString();
    }

    public String getDenialTimestamp() {
        if (deniedAt == null) return "N/A";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return deniedAt.format(formatter);
    }

    public long getLeaveDuration() {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public void displayRequestInfo() {
        System.out.println("Leave Type: " + getLeaveType());
        System.out.println("Leave Request ID: " + requestId);
        System.out.println("Employee Name: " + employee.getName());
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Total Days: " + getLeaveDuration());
        System.out.println("Request Status: " + status);
        System.out.println("Leave Type: " + leaveType);
        System.out.println("Created At: " + getCreationTimestamp());
        System.out.println("Approved At: " + getApprovalTimestamp());
        System.out.println("Denied At: " + getDenialTimestamp());   
        System.out.println("Criado em: " + getCreatedAtFormatted());
        System.out.println("Decisão: " + getDecisionAtFormatted());
        System.out.println("Approval Time: " + getApprovalTimestamp());
    }


}

