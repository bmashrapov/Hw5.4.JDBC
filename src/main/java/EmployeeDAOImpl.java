import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createEmployee(Employee employee) {
        String query = "INSERT INTO persons (id, first_name, last_name, gender, age, city_id) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setString(4, employee.getGender());
            statement.setInt(5, employee.getAge());
            statement.setInt(6, employee.getCityId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        String query = "SELECT * FROM persons WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String query = "SELECT * FROM persons;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Employee employee = extractEmployeeFromResultSet(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public void updateEmployee(Employee employee) {
        String query = "UPDATE persons SET first_name = ?, last_name = ?, gender = ?, age = ?, city_id = ? " +
                "WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.setInt(6, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String query = "DELETE FROM persons WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        int age = resultSet.getInt("age");
        int cityId = resultSet.getInt("city_id");
        return new Employee(id, firstName, lastName, gender, age, cityId);
    }
}

