import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

        Employee mark = new Employee(1, "Mark", "Johnson", "Male", 28, 2);
        employeeDAO.createEmployee(mark);

        System.out.println(employeeDAO.getEmployeeById(8));

        List<Employee> list = employeeDAO.getAllEmployees();
        for (Employee employee : list) {
            System.out.println(employee);
        }
        Employee mark1 = new Employee(7, "Mark", "Johnson", "Male", 28, 2);
        employeeDAO.updateEmployee(mark1);
        employeeDAO.deleteEmployee(mark1);
    }
}
