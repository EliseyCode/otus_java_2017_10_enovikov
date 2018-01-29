import cache.CacheEngineImpl;
import database.DBServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import server.AdminServlet;
import user_data.AddressDataSet;
import user_data.DataSet;
import user_data.UserDataSet;

import java.util.List;

public class Main {
    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {


        CacheEngineImpl<Long, DataSet> cacheEngine = new CacheEngineImpl<>(1, 700, 0, true);

        DBServiceImpl dbService = new DBServiceImpl(cacheEngine);

        UserDataSet user1 = new UserDataSet("Elisey", 32, new AddressDataSet("Moscow"));
        UserDataSet user2 = new UserDataSet("Olga", 30, new AddressDataSet("Moscow"));
        UserDataSet user3 = new UserDataSet("Kattie", 26, new AddressDataSet("Miami"));

        UserDataSet userFromDB;
        List<UserDataSet> list;
        List<UserDataSet> secondList;

        String status = dbService.getStatus();
        System.out.println("Status: " + status);
        dbService.save(user1);
        dbService.save(user2);
        dbService.save(user3);

        System.out.println("User with name Elisey: " + dbService.getByName("Elisey"));


        list = dbService.getAllUsers();
        secondList = dbService.getAllUsers();
        userFromDB = dbService.load(3);

        list.forEach(user -> System.out.println("User from List " + user.toString()));
        secondList.forEach(user -> System.out.println("User from List " + user.toString()));

        System.out.println("User from DB: " + userFromDB.toString());

        dbService.close();


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new AdminServlet(cacheEngine)), "/admin");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));

        server.start();
        server.join();
    }
}
