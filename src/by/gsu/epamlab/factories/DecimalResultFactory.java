package by.gsu.epamlab.factories;

import by.gsu.epamlab.beans.DecimalResult;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.impl.ResultImplXml;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.sql.Date;

public class DecimalResultFactory extends ResultFactory {

    @Override
    public double getMarkFromFactory(double initialValue) {
        return initialValue / Constants.DIVIDER_DECIMAL_RESULT;
    }

    public IResultDAO getResultDaoFromFactory(String input, ResultFactory factory) throws SourceException {
        return new ResultImplXml(input);
    }

    public Result getResultFromFactory(String login, String test, Date date, int mark) {
        return new DecimalResult(login, test, date, mark);
    }
}
