public class Employee {

    private int id;
    private String name;
    private int leaveBalance;

    // Constructor

    public Employee(int id, String name, int leaveBalance) {
        this.id = id;
        this.name = name;
        this.leaveBalance = leaveBalance;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getLeaveBalance() {
        return leaveBalance;
    }

    //setters para atualizar os atributos
    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }

    // Método para exibir informações do funcionário
    public void displayEmployeeInfo() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Leave Balance: " + leaveBalance);
    }

}