package by.gsu.epamlab.utility;


import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.consts.DBConstants;
import by.gsu.epamlab.exceptions.ConnectException;

import java.sql.*;

public class ConnectionManager {
    private static final Connection connection = createConnection();

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        return connection;
    }

    private static Connection createConnection() throws ConnectException {
        try {
            Class.forName(DBConstants.CLASS_NAME);
            return DriverManager.getConnection(DBConstants.DB_URL, DBConstants.USER, DBConstants.PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectException(Constants.CONNECTION_EXCEPTION);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Resource closing problem : " + e);
            }
        }
    }

    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.err.println("Resource closing problem : " + e);
            }
        }
    }

    public static void closePStatement(PreparedStatement ... pStatement) {
        for (PreparedStatement pS : pStatement)
        if (pS != null) {
            try {
                pS.close();
            } catch (SQLException e) {
                System.err.println("Resource closing problem : " + e);
            }
        }
    }

    public static void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.err.println("Resource closing problem : " + e);
            }
        }
    }
}
