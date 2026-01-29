import java.time.LocalDate;

public class SickLeave extends LeaveRequest {

    public SickLeave(int requestId, Employee employee, LocalDate startDate, LocalDate endDate) {
        super(requestId, employee, startDate, endDate, "SICK");
    }

    @Override
    public String getLeaveType() {
        return "Sick Leave";
    }

}