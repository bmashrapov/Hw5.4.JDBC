import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private SessionFactory sessionFactory;

    public EmployeeDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void createEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee getEmployeeById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();
            return employees;
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void deleteEmployee(Employee employee) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}

