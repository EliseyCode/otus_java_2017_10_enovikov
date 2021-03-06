package database;

import user_data.UserDataSet;

import java.util.List;

public interface DBService {
    String getStatus();

    void save(UserDataSet user);

    UserDataSet load(long id);

    List<UserDataSet> getAllUsers();

    UserDataSet getByName(String name);

    void close();
}
