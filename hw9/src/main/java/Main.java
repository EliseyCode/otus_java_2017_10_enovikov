import data_base.DBService;
import data_base.DBServiceImpl;
import user_data.UserDataSet;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        UserDataSet user1 = new UserDataSet("Elisey", 32);
        UserDataSet user2 = new UserDataSet("Olga", 31);

        try (DBService dbService = new DBServiceImpl()) {
            dbService.createTable();
            System.out.println(dbService.getMetaData());
            dbService.save(user1);
            dbService.save(user2);

            UserDataSet fromDB = dbService.load(1, UserDataSet.class);

            System.out.println(fromDB.toString());

            List userList = dbService.getAllUsers();

            for (Object user : userList) {
                System.out.println(user.toString());
            }

            dbService.dropTable();
        }
    }
}
