import java.sql.*;
import java.util.List;

public class Application {
//    private Connection connection;

//    public Application() {
//        // Подключение к базе данных
//        String url = "jdbc:postgresql://localhost:5432/skypro";
//        String username = "postgres";
//        String password = "ulugar01";
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public void getEmployeeData(int employeeId) {
//        // Получение данных о работнике по его id
//        String query = "SELECT p.first_name, p.last_name, p.gender, c.city_name " +
//                "FROM persons AS p " +
//                "JOIN city AS c ON p.city_id = c.city_id " +
//                "WHERE p.id = ?;";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, employeeId);
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                // Вывод данных о работнике в консоль
//                String firstName = resultSet.getString("first_name");
//                String lastName = resultSet.getString("last_name");
//                String gender = resultSet.getString("gender");
//                String city = resultSet.getString("city_name");
//
//                System.out.println("Имя: " + firstName);
//                System.out.println("Фамилия: " + lastName);
//                System.out.println("Пол: " + gender);
//                System.out.println("Город: " + city);
//            } else {
//                System.out.println("Работник с указанным ID не найден.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/skypro";
        String username = "postgres";
        String password = "ulugar01";
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            Application application = new Application(employeeDAO);
            application.run();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private EmployeeDAO employeeDAO;
    public Application(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    public void run() {
        // Создание нового работника
        Employee newEmployee = new Employee(12, "Mark", "Johnson", "Male", 28, 2);
        employeeDAO.createEmployee(newEmployee);

        // Получение работника по id и вывод данных в консоль
        Employee employeeById = employeeDAO.getEmployeeById(11);
        if (employeeById != null) {
            System.out.println("Имя: " + employeeById.getFirstName());
            System.out.println("Фамилия: " + employeeById.getLastName());
            System.out.println("Пол: " + employeeById.getGender());
            System.out.println("Город: " + employeeById.getAge());
            System.out.println("-----------------------");
        }

        // Получение списка всех работников и вывод данных в консоль
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for (Employee employee : allEmployees) {
            System.out.println("Имя: " + employee.getFirstName());
            System.out.println("Фамилия: " + employee.getLastName());
            System.out.println("Пол: " + employee.getGender());
            System.out.println("Возраст: " + employeeById.getAge());
            System.out.println("-----------------------");
        }

        // Изменение данных работника
        Employee employeeToUpdate = employeeDAO.getEmployeeById(6);
        if (employeeToUpdate != null) {
            employeeToUpdate.setFirstName("Sara");
            employeeToUpdate.setLastName("Scotch");
            employeeToUpdate.setGender("Female");
            employeeToUpdate.setAge(30);
            employeeDAO.updateEmployee(employeeToUpdate);
        }

        // Удаление работника по id
        employeeDAO.deleteEmployee(7);
    }
}
