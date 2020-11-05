package by.gsu.epamlab.utility;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.DBConstants;
import by.gsu.epamlab.exceptions.ConnectException;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultsLoader {

    private static int getId(String login, PreparedStatement psSelectLogin, PreparedStatement psInsertLogin) throws SQLException {
        ResultSet resultSet = null;
        try {
            psSelectLogin.setString(DBConstants.SELECT_INDEX, login);
            resultSet = psSelectLogin.executeQuery();
            if (!resultSet.next()) {
                psInsertLogin.setString(DBConstants.SELECT_INDEX, login);
                psInsertLogin.executeUpdate();
                resultSet = psSelectLogin.executeQuery();
                resultSet.next();
            }
            return resultSet.getInt(DBConstants.SELECT_INDEX);
        } finally {
            ConnectionManager.closeResultSet(resultSet);
        }
    }

    public static void loadResults(IResultDAO reader) throws ConnectException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement psSelectLogin = null;
        PreparedStatement psInsertLogin = null;
        PreparedStatement psSelectTest = null;
        PreparedStatement psInsertTest = null;
        PreparedStatement psInsertResult = null;
        try {
            psSelectLogin = connection.prepareStatement(DBConstants.LOGIN_SELECT_QUERY);
            psInsertLogin = connection.prepareStatement(DBConstants.LOGIN_INSERT_QUERY);
            psSelectTest = connection.prepareStatement(DBConstants.TEST_SELECT_QUERY);
            psInsertTest = connection.prepareStatement(DBConstants.TEST_INSERT_QUERY);
            while (reader.hasResult()) {
                Result result = reader.nextResult();
                String login = result.getLogin();
                String test = result.getTest();
                int idLogin = getId(login, psSelectLogin, psInsertLogin);
                int idTest = getId(test, psSelectTest, psInsertTest);
                psInsertResult = connection.prepareStatement(DBConstants.RESULT_INSERT_QUERY);
                psInsertResult.setInt(DBConstants.TEST_INDEX, idTest);
                psInsertResult.setInt(DBConstants.LOGIN_INDEX, idLogin);
                psInsertResult.setDate(DBConstants.DATE_INDEX, result.getDate());
                psInsertResult.setInt(DBConstants.MARK_INDEX, result.getMark());
                psInsertResult.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ConnectException(e);
        } finally {
            reader.closeReader();
            ConnectionManager.closePStatement(psSelectLogin, psInsertLogin, psSelectTest, psInsertTest, psInsertResult);
        }
    }
}
