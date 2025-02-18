public abstract class Employee implements Payable {
    private String name;
    private int id;
    protected int baseSalary;

    public Employee(String name, int baseSalary, int id) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.id = id;
    }

    public Employee(String name, int id) {
        this(name, 0, id);
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getBaseSalary() {
        return baseSalary;
    }

    public abstract double calculateSalary();

    public void displayEmployeeInfo() {
        System.out.println("Name: " + name + "\nID: " + id + "\nBase Salary: " + baseSalary);
    }

    @Override
    public double getPaymentAmount() {
        return calculateSalary();
    }
}

interface Payable {
    double getPaymentAmount();
}

class FullTimeEmployee extends Employee {
    private static final double BONUS = 1.2;
    private boolean healthInsurance = true;

    public FullTimeEmployee(String name, int salary, int id) {
        super(name, salary, id);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() * BONUS;
    }
}

class ContractEmployee extends Employee {
    private int hoursWorked;
    private int hourlyRate;

    public ContractEmployee(String name, int id, int hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class EmployeeManager {
    public static void main(String[] args) {
        Employee[] employees = new Employee[10];
        employees[0] = new FullTimeEmployee("Salma", 10000, 100);
        employees[1] = new ContractEmployee("Atai", 100, 100, 12);

        for (int i = 0; i < employees.length; i++) {
            if (employees[i] != null) {
                employees[i].displayEmployeeInfo();
                System.out.println("Total Salary: " + employees[i].calculateSalary() + "\n");
            }
        }
    }
}
