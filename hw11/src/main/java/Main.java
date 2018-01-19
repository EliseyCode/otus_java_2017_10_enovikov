import cache.CacheEngineImpl;
import database.DBServiceImpl;
import user_data.AddressDataSet;
import user_data.DataSet;
import user_data.UserDataSet;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CacheEngineImpl<Long, DataSet> cacheEngine = new CacheEngineImpl<>(2, 700, 700, false);

        DBServiceImpl dbService = new DBServiceImpl(cacheEngine);

        UserDataSet user1 = new UserDataSet("Elisey", 32, new AddressDataSet("Moscow"));
        UserDataSet user2 = new UserDataSet("Olga", 30, new AddressDataSet("Moscow"));
        UserDataSet user3 = new UserDataSet("Kattie", 26, new AddressDataSet("Miami"));

        UserDataSet userFromDB;
        List<UserDataSet> list;

        String status = dbService.getStatus();
        System.out.println("Status: " + status);
        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);
        System.out.println("User with name Elisey: " + dbService.getByName("Elisey"));
        list = dbService.getAllUsers();
        userFromDB = dbService.load(3);

        list.forEach(user -> System.out.println("User from List " + user.toString()));

        System.out.println("User from DB: " + userFromDB.toString());

        dbService.close();
    }
}
