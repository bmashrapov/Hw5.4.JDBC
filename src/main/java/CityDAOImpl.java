import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class CityDAOImpl implements CityDAO {
    private SessionFactory sessionFactory;

    public CityDAOImpl() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void createCity(City city) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City getCityById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> getAllCities() {
        try (Session session = sessionFactory.openSession()) {
            List<City> cities = session.createQuery("FROM City", City.class).list();
            return cities;
        }
    }

    @Override
    public void updateCity(City city) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteCity(City city) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(city);
            transaction.commit();
        }
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}
