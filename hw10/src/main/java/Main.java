import database.DBService;
import database.DBServiceImpl;
import user_data.AddressDataSet;
import user_data.PhoneDataSet;
import user_data.UserDataSet;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DBService dbService = new DBServiceImpl();

        List<String> numbers = new ArrayList();

        numbers.add("925-628-0658");
        numbers.add("906-085-9022");
        numbers.add("916-182-1942");

        UserDataSet user1 = new UserDataSet("Elisey", 32, new AddressDataSet("Moscow"));
        numbers.forEach((n) -> user1.addPhone(new PhoneDataSet(n)));

        UserDataSet user2 = new UserDataSet("Olga", 30, new AddressDataSet("Moscow"));
        numbers.forEach((n) -> user2.addPhone(new PhoneDataSet(n)));
        numbers.remove("916-182-1942");

        UserDataSet user3 = new UserDataSet("Kattie", 26, new AddressDataSet("Miami"));
        numbers.forEach((n) -> user3.addPhone(new PhoneDataSet(n)));

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
