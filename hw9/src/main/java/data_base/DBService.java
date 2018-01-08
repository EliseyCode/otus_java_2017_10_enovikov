package data_base;

import user_data.DataSet;
import user_data.UserDataSet;

import java.sql.SQLException;
import java.util.List;

public interface DBService extends AutoCloseable {
    String getMetaData();

    void createTable() throws SQLException;

    <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException, NoSuchFieldException;

    <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException;

    List<UserDataSet> getAllUsers() throws SQLException, IllegalAccessException, InstantiationException;

    void dropTable() throws SQLException;
}
