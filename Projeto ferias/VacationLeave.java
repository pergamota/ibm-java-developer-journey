import java.time.LocalDate;

public class VacationLeave extends LeaveRequest {

    public VacationLeave(int requestId, Employee employee, LocalDate startDate, LocalDate endDate) {
        super(requestId, employee, startDate, endDate, "VACATION");
    }

    @Override
    public String getLeaveType() {
        return "Vacation Leave";
    }

}