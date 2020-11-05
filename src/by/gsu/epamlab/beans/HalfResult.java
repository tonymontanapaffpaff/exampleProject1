package by.gsu.epamlab.beans;


import by.gsu.epamlab.consts.Constants;

import java.sql.Date;
import java.util.Scanner;

public class HalfResult extends Result {

    public HalfResult() {
    }

    public HalfResult(Scanner sc) {
        super(sc);
    }

    public HalfResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    @Override
    public int getMarkFromString(String mark) {
        return (int) (Double.parseDouble(mark) * Constants.DIVIDER_HALF_RESULT);
    }

    @Override
    public String getStringMark() {
        return getMark() / Constants.DIVIDER_HALF_RESULT + Constants.SEPARATOR + getMark()  % Constants.DIVIDER_HALF_RESULT * Constants.MULTIPLIER_HALF_RESULT;
    }
}
