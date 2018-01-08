package executor;

import java.sql.*;

public class Executor {
    private final Connection con;

    public Executor(Connection con) {
        this.con = con;
    }

    public void execQuery(String query) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            stmt.execute(query);
        }
    }

    public void execQuery(String sqlQuery, ExecuteHandler executeHandler) throws SQLException, IllegalAccessException {
        try (PreparedStatement statement = con.prepareStatement(sqlQuery)) {
            executeHandler.accept(statement);
        }
    }

    public <T> T execQuery(String sqlQuery, ResultHandler<T> handler) throws SQLException, InstantiationException, IllegalAccessException {
        try (Statement statement = con.createStatement()) {
            statement.execute(sqlQuery);
            try (ResultSet result = statement.getResultSet()) {
                return handler.handle(result);
            }
        }
    }

    public <T> T execQuery(String sqlQuery, ExecuteHandler executeHandler, ResultHandler<T> handler) throws SQLException, InstantiationException, IllegalAccessException {
        try (PreparedStatement statement = con.prepareStatement(sqlQuery)) {
            executeHandler.accept(statement);
            try (ResultSet result = statement.getResultSet()) {
                return handler.handle(result);
            }
        }
    }
}
