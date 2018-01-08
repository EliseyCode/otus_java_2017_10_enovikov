package data_base;

import executor.Executor;
import user_data.DataSet;
import user_data.UserDataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBServiceImpl implements DBService {
    private Connection con;

    private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user(id BIGINT AUTO_INCREMENT, name VARCHAR(255), age INT(3), PRIMARY KEY(id))";
    private final String INSERT_USER = "INSERT INTO user(name, age) VALUES (?,?)";
    private final String SELECT_USER_ID = "SELECT * FROM user WHERE id=(?)";
    private final String SELECT_USERS = "SELECT * FROM user";
    private final String DROP_TABLE = "DROP TABLE user";

    public DBServiceImpl() {
        this.con = ConnectionHelper.getConnection();
    }

    @Override
    public String getMetaData() {
        try {
            return "Connected to " + con.getMetaData().getURL() + "\n" +
                    "DB name: " + con.getMetaData().getDatabaseProductName();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    @Override
    public void createTable() throws SQLException {
        Executor exec = new Executor(con);
        exec.execQuery(CREATE_TABLE);
        System.out.println("table is created");
    }

    @Override
    public <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException, NoSuchFieldException {
        Executor exec = new Executor(con);
        Field[] fields = user.getClass().getDeclaredFields();
        exec.execQuery(INSERT_USER, stmt -> {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals("name")) {
                    stmt.setString(1, (String) field.get(user));
                } else if (field.getName().equals("age")) {
                    stmt.setInt(2, (Integer) field.get(user));
                }
            }
            stmt.execute();
        });
    }


    @Override
    public <T extends DataSet> T load(long id, Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        Executor exec = new Executor(con);

        return exec.execQuery(SELECT_USER_ID, stmt -> {
                    stmt.setLong(1, id);
                    stmt.execute();
                }, result -> {
                    if (result.next()) {
                        Object user = clazz.newInstance();
                        Field[] fields = clazz.getDeclaredFields();
                        for (Field field : fields) {
                            field.setAccessible(true);
                            field.set(user, result.getObject(field.getName()));
                        }
                        return (T) user;
                    } else return null;
                }
        );
    }

    public List<UserDataSet> getAllUsers() throws SQLException, IllegalAccessException, InstantiationException {
        Executor exec = new Executor(con);

        return exec.execQuery(SELECT_USERS, result -> {
            List<UserDataSet> userList = new ArrayList();
            while (result.next()) {
                userList.add(new UserDataSet(result.getString("name"), result.getInt("age")));
            }
            return userList;
        });
    }

    public void dropTable() throws SQLException{
        Executor exec = new Executor(con);
        exec.execQuery(DROP_TABLE);
    }

    @Override
    public void close() throws Exception {
        con.close();
    }
}
