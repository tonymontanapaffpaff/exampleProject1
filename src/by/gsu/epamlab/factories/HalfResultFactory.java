package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.HalfResult;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.impl.ResultImplCsv;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.sql.Date;
import java.util.Scanner;

public class HalfResultFactory extends ResultFactory {

    @Override
    public double getMarkFromFactory(double initialValue) {
        return initialValue / Constants.DIVIDER_HALF_RESULT;
    }

    public IResultDAO getResultDaoFromFactory(String input, ResultFactory factory) throws SourceException {
        return new ResultImplCsv(input, factory);
    }

    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new HalfResult(login, test, date, mark);
    }

    public Result getResultFromFactory(Scanner sc) {
        return new HalfResult(sc);
    }
}
