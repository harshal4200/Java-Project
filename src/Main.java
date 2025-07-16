import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee[name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private double perHourSalary;
    private double hoursWorked;

    public PartTimeEmployee(String name, int id, double perHourSalary, double hoursWorked) {
        super(name, id);
        this.perHourSalary = perHourSalary;
        this.hoursWorked = hoursWorked;
    }

    @Override
    double calculateSalary() {
        return perHourSalary * hoursWorked;
    }
}

class PayRollSystem {
    private List<Employee> employeeList;

    public PayRollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;

        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
            System.out.println("Employee with ID " + id + " removed successfully.");
        } else {
            System.out.println("Employee with ID " + id + " not found.");
        }
    }

    public void displayEmployees() {
        if (employeeList.isEmpty()) {
            System.out.println("No employees to display.");
        } else {
            for (Employee emp : employeeList) {
                System.out.println(emp);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayRollSystem payRollSystem = new PayRollSystem();

        while (true) {
            System.out.println("\n1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Full-Time Employee Name: ");
                    String ftName = sc.nextLine();
                    System.out.print("Enter ID: ");
                    int ftId = sc.nextInt();
                    System.out.print("Enter Monthly Salary: ");
                    double monthlySalary = sc.nextDouble();
                    payRollSystem.addEmployee(new FullTimeEmployee(ftName, ftId, monthlySalary));
                    System.out.println("Full-Time Employee added.");
                    break;

                case 2:
                    System.out.print("Enter Part-Time Employee Name: ");
                    String ptName = sc.nextLine();
                    System.out.print("Enter ID: ");
                    int ptId = sc.nextInt();
                    System.out.print("Enter Per Hour Salary: ");
                    double perHourSalary = sc.nextDouble();
                    System.out.print("Enter Hours Worked: ");
                    double hoursWorked = sc.nextDouble();
                    payRollSystem.addEmployee(new PartTimeEmployee(ptName, ptId, perHourSalary, hoursWorked));
                    System.out.println("Part-Time Employee added.");
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Remove: ");
                    int removeId = sc.nextInt();
                    payRollSystem.removeEmployee(removeId);
                    break;

                case 4:
                    payRollSystem.displayEmployees();
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
