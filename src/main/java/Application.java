import java.sql.*;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();

        // Создание нового сотрудника и города
        City newYork = new City(1, "New York");
        cityDAO.createCity(newYork);

        Employee mark = new Employee(1, "Mark", "Johnson", "Male", 28, newYork);
        employeeDAO.createEmployee(mark);

        // Получение сотрудника по ID
        Employee employee = employeeDAO.getEmployeeById(8);
        System.out.println(employee);

        // Получение всех сотрудников
        List<Employee> employees = employeeDAO.getAllEmployees();
        for (Employee emp : employees) {
            System.out.println(emp);
        }

        // Обновление информации о сотруднике
        Employee mark1 = new Employee(7, "Mark", "Johnson", "Male", 28, newYork);
        employeeDAO.updateEmployee(mark1);

        // Удаление сотрудника
        employeeDAO.deleteEmployee(mark1);
    }
}
