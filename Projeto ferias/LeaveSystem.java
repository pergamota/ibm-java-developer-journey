import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class LeaveSystem {

    private LinkedList<LeaveRequest> leaveRequests;

    public LeaveSystem() {
        this.leaveRequests = new LinkedList<>();
        initializeFileStructure();
    }

    public void addLeaveRequest(LeaveRequest request) {
        leaveRequests.add(request);
    }

    public void displayAllLeaveRequests() {
        for (LeaveRequest request : leaveRequests) {
            request.displayRequestInfo();
            System.out.println();
        }
    }

    public void approveLeaveRequest(int requestId) {
        for (LeaveRequest request : leaveRequests) {
            if (request.getRequestId() == requestId) {
                request.approve();
                saveLeaveRequestToFile(request);
                System.out.println("Leave request " + requestId + " approved.");
                return;
            }
        }
        System.out.println("Leave request with ID " + requestId + " not found.");
    }

    public void searchByStatus(String status) {
        boolean found = false;

        for (LeaveRequest lr : leaveRequests) {

            /* 
                for: percorre todos os pedidos
               IgnoreCase: evita erro por letras minúsculas ou maiúsculas 
            */ 
            if (lr.getStatus().equalsIgnoreCase(status)) {
                lr.displayRequestInfo();
                System.out.println("---------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nenhum pedido encontrado com status: " + status);
        }
    }

    public void backupLeaveRequests() {

        File requestsDir = new File("leavetracker_data/requests");
        File backupDir = new File("leavetracker_data/backups");

        // cria pasta backup se não existir
        if (!backupDir.exists()) {
            backupDir.mkdir();
        }

        File[] files = requestsDir.listFiles();

        if (files == null) {
            System.out.println("Nenhum arquivo para backup.");
            return;
        }

        for (File file : files) {

            File backupFile = new File(
                backupDir,
                file.getName().replace(".txt", "_backup.txt")
            );

            try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                PrintWriter writer = new PrintWriter(new FileWriter(backupFile)))
            {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.println(line);
                }

                System.out.println("Backup criado: " + backupFile.getName());

            } catch (IOException e) {
                System.out.println("Erro ao fazer backup do arquivo: " + file.getName());
            }
        }
    }


    public void deleteLeaveRequest(int requestId) {

        LeaveRequest toRemove = null;

        // Procurar na lista
        for (LeaveRequest request : leaveRequests) {
            if (request.getRequestId() == requestId) {
                toRemove = request;
                break;
            }
        }

        if (toRemove == null) {
            System.out.println("Leave request não encontrado.");
            return;
        }

        // Remover da lista
        leaveRequests.remove(toRemove);

        // Apagar o arquivo
        File file = new File("leavetracker_data/requests/" + requestId + ".txt");

        if (file.exists() && file.delete()) {
            System.out.println("Leave request " + requestId + " deletado com sucesso.");
        } else {
            System.out.println("Arquivo não encontrado ou erro ao deletar.");
        }
    }


    public void saveLeaveRequestToFile(LeaveRequest request) {

        File file = new File(
            "leavetracker_data/requests/" + request.getRequestId() + ".txt"
        );

        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {

            writer.println("RequestId=" + request.getRequestId());
            writer.println("EmployeeId=" + request.getEmployee().getId());
            writer.println("EmployeeName=" + request.getEmployee().getName());
            writer.println("StartDate=" + request.getStartDate());
            writer.println("EndDate=" + request.getEndDate());
            writer.println("Status=" + request.getStatus());
            writer.println("Type=" + request.getLeaveType());
            writer.println("CreatedAt=" + request.createdAt);
            if (request.decisionAt != null) {
                writer.println("DecisionAt=" + request.decisionAt);
            }

            if (request.approvalAt != null) {
                writer.println("ApprovalAt=" + request.approvalAt);
            }

            System.out.println("LeaveRequest salvo: " + file.getName());

        }   catch (IOException e) {
            System.out.println("Erro ao salvar LeaveRequest.");
        }
    }

    public void loadLeaveRequestsFromDirectory() {

        leaveRequests.clear();

        File requestsDir = new File("leavetracker_data/requests");

        File[] files = requestsDir.listFiles();

        if (files == null) {
            System.out.println("Nenhum arquivo de solicitação encontrado.");
            return;
        }

        for (File file : files) {

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            int requestId = 0;
            int employeeId = 0;
            String employeeName = "";
            LocalDate startDate = null;
            LocalDate endDate = null;
            LocalDateTime createdAt = null;
            LocalDateTime decisionAt = null;
            ZonedDateTime approvalAt = null;
            String status = "";
            String type = "";

            String line;

                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("=");

                    switch (parts[0]) {
                        case "RequestId" -> requestId = Integer.parseInt(parts[1]);
                        case "EmployeeId" -> employeeId = Integer.parseInt(parts[1]);
                        case "EmployeeName" -> employeeName = parts[1];
                        case "StartDate" -> startDate = LocalDate.parse(parts[1]);
                        case "EndDate" -> endDate = LocalDate.parse(parts[1]);
                        case "CreatedAt" -> createdAt = LocalDateTime.parse(parts[1]);
                        case "DecisionAt" -> decisionAt = LocalDateTime.parse(parts[1]);
                        case "ApprovalAt" -> approvalAt = ZonedDateTime.parse(parts[1]);
                        case "Status" -> status = parts[1];
                        case "Type" -> type = parts[1];
                    }
                }

                Employee employee = new Employee(employeeId, employeeName, 0);

                LeaveRequest lr;

                if (type.equals("VACATION")) {
                    lr = new VacationLeave(requestId, employee, startDate, endDate);
                } else {
                    lr = new SickLeave(requestId, employee, startDate, endDate);
                }

                lr.status = status; 

                if (createdAt != null) {
                    lr.createdAt = createdAt;
                }

                if (decisionAt != null) {
                    lr.decisionAt = decisionAt;
                }

                if (approvalAt != null) {
                    lr.approvalAt = approvalAt;
                }

                leaveRequests.add(lr);

            }   catch (IOException e) {
                System.out.println("Erro ao ler arquivo: " + file.getName());
            }
        }
    }

    public void searchByDate (LocalDate date) {
        boolean found = false;

        for (LeaveRequest lr : leaveRequests) {

            if (
                !date.isBefore(lr.getStartDate()) &&
                !date.isAfter(lr.getEndDate())
            ) {
                lr.displayRequestInfo();
                System.out.println("---------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("Nenhum pedido encontrado para a data: " + date);
        }
    }

    public void searchByPeriod(LocalDate start, LocalDate end) {

        boolean found = false;

        for (LeaveRequest lr : leaveRequests) {

            if (
                !lr.getEndDate().isBefore(start) &&
                !lr.getStartDate().isAfter(end)
            ) {
                lr.displayRequestInfo();
                System.out.println("---------------");
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                "Nenhum pedido encontrado entre " + start + " e " + end
            );
        }
    }

    public void generateLeaveReportByEmployee() {

        if (leaveRequests.isEmpty()) {
            System.out.println("Nenhum pedido registrado.");
            return;
        }

        for (LeaveRequest lr : leaveRequests) {

            String employeeName = lr.getEmployee().getName();
            long totalDays = 0;

            for (LeaveRequest other : leaveRequests) {
                if (other.getEmployee().getName().equals(employeeName)) {
                    totalDays += other.getLeaveDuration();
                }
            }

            System.out.println("Funcionário: " + employeeName);
            System.out.println("Total de dias de licença: " + totalDays);
            System.out.println("------------------------");
        }
    }

    
    public void initializeFileStructure() {
        File dataDir = new File("leavetracker_data");
        //dataDir é a pasta principal.

        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        File requestsDir = new File(dataDir, "requests");

        //requests é uma subpasta dentro da pasta dataDir.

        if (!requestsDir.exists()) {
            if (requestsDir.mkdir()) {
                System.out.println("Diretório 'requests' criado.");
            } else {
                System.out.println("Falha ao criar o diretório 'requests'.");
            }
        } else {
            System.out.println("Diretório 'requests' já existe.");
        }
    }

}   