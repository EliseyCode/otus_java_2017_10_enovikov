package server;

import cache.CacheEngineImpl;
import user_data.DataSet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private static final String LOGIN_PARAMETER_NAME = "login";
    private static final String PASSWORD_PARAMETER_NAME = "password";

    private static final String LOGIN_VARIABLE_NAME = "log";
    private static final String PASSWORD_VARIABLE_NAME = "pass";

    private static final String ADMIN_PAGE_SERVLET = "admin.html";
    private static final String CACHE_PAGE_SERVLET = "cache.html";

    private final CacheEngineImpl<Long, DataSet> cacheEngine;


    public AdminServlet(CacheEngineImpl<Long, DataSet> cacheEngine) {
        this.cacheEngine = cacheEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Object> variables = new HashMap();
        String requestLogin = req.getParameter(LOGIN_PARAMETER_NAME);
        String requestPassword = req.getParameter(PASSWORD_PARAMETER_NAME);

        if (LOGIN_VARIABLE_NAME.equals(requestLogin) && PASSWORD_VARIABLE_NAME.equals(requestPassword)) {
            variables.put("hitCount", cacheEngine.getHitCount());
            variables.put("missCount", cacheEngine.getMissCount());

            resp.getWriter().println(Processor.instance().getPage(CACHE_PAGE_SERVLET, variables));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().println(Processor.instance().getPage(ADMIN_PAGE_SERVLET, variables));
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
