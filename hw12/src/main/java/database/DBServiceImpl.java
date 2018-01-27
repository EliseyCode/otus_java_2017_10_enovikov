package database;

import DAO.UserDAOImpl;
import cache.CacheEngineImpl;
import cache.Value;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import user_data.DataSet;
import user_data.UserDataSet;

import java.util.List;
import java.util.function.Function;

public class DBServiceImpl implements DBService {
    private final SessionFactory sessionFactory;
    private final CacheEngineImpl<Long, DataSet> cache;

    public DBServiceImpl(CacheEngineImpl<Long, DataSet> cache) {
        this.cache = cache;
        sessionFactory = ConnectionHelper.getSessionFactory();
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()) {
            UserDAOImpl dao = new UserDAOImpl(session);
            dao.save(user);
        }

        if (cache != null) {
            System.out.println("Write to cache with id: " + user.getId());
            cache.put(user.getId(), new Value<>(user));
        }
    }

    @Override
    public UserDataSet load(long id) {
        UserDataSet user;
        if (cache != null) {
            user = (UserDataSet) cache.get(id);
            if (user != null) {
                System.out.println("Return from cache with id: " + id);
                return user;
            } else {
                user = runInSession(session -> {
                    UserDAOImpl dao = new UserDAOImpl(session);
                    return dao.load(id);
                });
                System.out.println("Return from DB and write to cache id: " + id);
                cache.put(id, new Value<>(user));
                return user;
            }
        }
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
