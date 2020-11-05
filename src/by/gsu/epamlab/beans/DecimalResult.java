package by.gsu.epamlab.beans;


import by.gsu.epamlab.consts.Constants;

import java.sql.Date;
import java.util.Scanner;

public class DecimalResult extends Result {

    public DecimalResult() {
    }

    public DecimalResult(Scanner sc) {
        super(sc);
    }

    public DecimalResult(String login, String test, String date, String mark) {
        super(login, test, date, mark);
    }

    public DecimalResult(String login, String test, Date date, int mark) {
        super(login, test, date, mark);
    }

    @Override
    public String getStringMark() {
        return getMark() / Constants.DIVIDER_DECIMAL_RESULT + Constants.SEPARATOR + getMark() % Constants.DIVIDER_DECIMAL_RESULT;
    }
}
