package by.gsu.epamlab.utility;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.consts.DBConstants;
import by.gsu.epamlab.exceptions.ConnectException;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.factories.ResultFactory;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RunnerLogic {
    private static List<Result> results;

    private static void printMeanValue(ResultFactory factory) {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = ConnectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(DBConstants.MEAN_COST_SELECT_QUERY);
            while (resultSet.next()) {
                System.out.println(Constants.LOGIN + resultSet.getString(DBConstants.MEAN_VALUE_LOGIN));
                System.out.print(Constants.MARK);
                System.out.printf("%.2f %s", factory.getMarkFromFactory(resultSet.getDouble(DBConstants.MEAN_VALUE_MARK)), Constants.NEW_LINE);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeResultSet(resultSet);
        }
    }

    private static void printList(ResultFactory factory) {
        Statement statement = null;
        ResultSet resultSet = null;
        results = new LinkedList<>();
        try {
            statement = ConnectionManager.getConnection().createStatement();
            resultSet = statement.executeQuery(DBConstants.LIST_SELECT_QUERY);
            while (resultSet.next()) {
                Result result = factory.getResultFromFactory(resultSet.getString(DBConstants.LOGIN_FIELD), resultSet.getString(DBConstants.TEST_FIELD),
                        resultSet.getDate(DBConstants.DATE_FIELD), resultSet.getInt(DBConstants.MARK_FIELD));
                results.add(result);
            }
            System.out.println(Constants.CURRENT_RESULTS);
            for (Result result : results) {
                System.out.println(result);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            ConnectionManager.closeConnection();
            ConnectionManager.closeStatement(statement);
            ConnectionManager.closeResultSet(resultSet);
        }
    }

    private static void printLastDayTests(List<Result> results) {
        ListIterator<Result> iterator;
        for (iterator = results.listIterator(); iterator.hasNext();) {
            iterator.next();
        }
        Result result = iterator.previous();
        System.out.println(Constants.CURRENT_RESULTS_LAST_DAY);
        System.out.println(result);
        Date date = result.getDate();
        while (iterator.hasPrevious()) {
            result = iterator.previous();
            if (result.getDate().equals(date)) {
                System.out.println(result);
            }
        }
    }

    public static void execute(ResultFactory factory, String input) {
        try {
            ResultsLoader.loadResults(factory.getResultDaoFromFactory(input, factory));
            printMeanValue(factory);
            printList(factory);
            printLastDayTests(results);
        } catch (ConnectException | SourceException e) {
            System.err.println(e.getMessage());
        }
    }
}
