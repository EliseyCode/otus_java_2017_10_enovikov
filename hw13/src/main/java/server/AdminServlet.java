package server;

import database.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdminServlet extends HttpServlet {
    public static final String LOGIN_PARAMETER_NAME = "login";
    public static final String PASSWORD_PARAMETER_NAME = "password";

    private static final String LOGIN_VARIABLE_NAME = "log";
    private static final String PASSWORD_VARIABLE_NAME = "pass";

    private static final String ADMIN_PAGE_SERVLET = "admin.html";
    private static final String CACHE_PAGE_SERVLET = "cache.html";

    @Autowired
    private DBServiceImpl dbService;

    private Map<String, Object> pageVariables;

    public AdminServlet() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        pageVariables = new HashMap<>();
        pageVariables.put("message", "Enter login and password");
        resp.getWriter().println(Processor.instance().getPage(ADMIN_PAGE_SERVLET, pageVariables));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        pageVariables = new HashMap<>();
        String requestLogin = req.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = req.getParameter(PASSWORD_PARAMETER_NAME);

        if (LOGIN_VARIABLE_NAME.equals(requestLogin) && PASSWORD_VARIABLE_NAME.equals(requestPassword)) {
            pageVariables.putAll(dbService.getCache().getStat());

            resp.getWriter().println(Processor.instance().getPage(CACHE_PAGE_SERVLET, pageVariables));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            pageVariables.put("message", "Incorrect login or password");
            resp.getWriter().println(Processor.instance().getPage(ADMIN_PAGE_SERVLET, pageVariables));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
