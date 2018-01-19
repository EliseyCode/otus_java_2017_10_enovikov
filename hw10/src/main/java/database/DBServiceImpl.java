package database;

import DAO.UserDAOImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import user_data.UserDataSet;

import java.util.List;
import java.util.function.Function;

public class DBServiceImpl implements DBService {
    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        sessionFactory = ConnectionHelper.getSessionFactory();
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            UserDAOImpl dao = new UserDAOImpl(session);
            dao.save(user);
        }
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.load(id);
        });
    }

    @Override
    public List<UserDataSet> getAllUsers() {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.getAllUsers();
        });
    }

    @Override
    public UserDataSet getByName(String name) {
        return runInSession(session -> {
            UserDAOImpl dao = new UserDAOImpl(session);
            return dao.getByName(name);
        });
    }

    @Override
    public String getStatus() {
        return runInSession(session -> session.getTransaction().getStatus().name());
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public void close() {
        sessionFactory.close();

    }
}
