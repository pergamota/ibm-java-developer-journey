import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // Criando um funcionário
        Employee employee1 = new Employee(1, "Pedro", 20);
        LeaveRequest vacation = new VacationLeave(2, employee1,
            LocalDate.of(2026, 1, 10), LocalDate.of(2026, 1, 15)
        );
        LeaveRequest sick = new SickLeave(3, employee1, LocalDate.of(2026, 1, 20), 
            LocalDate.of(2026, 1, 30)
        );

        LeaveSystem leaveSystem = new LeaveSystem();
        leaveSystem.addLeaveRequest(vacation);
        leaveSystem.addLeaveRequest(sick);
        leaveSystem.loadLeaveRequestsFromDirectory();
        leaveSystem.displayAllLeaveRequests();

        // Exibindo dados do funcionário
        employee1.displayEmployeeInfo();

        System.out.println();

        //Antes da aprovação / status pendente.

        leaveSystem.approveLeaveRequest(2);
        System.out.println();

        leaveSystem.approveLeaveRequest(3);
        System.out.println();

        System.out.println("Solicitações APROVADAS:");
        leaveSystem.searchByStatus("Approved");

        leaveSystem.displayAllLeaveRequests();
        System.out.println("After approval:");
        System.out.println();

        leaveSystem.deleteLeaveRequest(2);

        leaveSystem.saveLeaveRequestToFile(vacation);
        leaveSystem.saveLeaveRequestToFile(sick);

        leaveSystem.backupLeaveRequests();

    }
}