package by.gsu.epamlab.beans;


import by.gsu.epamlab.consts.Constants;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Result {
    private final static SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat STRING_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    private String login;
    private String test;
    private Date date;
    private int mark;

    public Result() {
    }

    public Result(Scanner sc) {
        String[] line = sc.nextLine().split(Constants.SPLIT_LINE);
        login = line[0];
        test = line[1];
        date = null;
        try {
            date = new Date(STRING_FORMAT.parse(line[2]).getTime());
        } catch (ParseException e) {
            throw new IllegalArgumentException(Constants.PARSE_EXCEPTION);
        }
        mark = getMarkFromString(line[3]);
    }

    public Result(String login, String test, String date, String mark) {
        this.login = login;
        this.test = test;
        try {
            this.date = new Date(FORMATTER.parse(date).getTime());
            this.mark = (int) (Double.parseDouble(mark)*Constants.DIVIDER_DECIMAL_RESULT);
        } catch (ParseException | NumberFormatException e) {
            throw new IllegalArgumentException(Constants.PARSE_EXCEPTION);
        }
    }

    public Result(String login, String test, Date date, int mark) {
        this.login = login;
        this.test = test;
        this.date = date;
        this.mark = mark;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public Date getDate() {
        return date;
    }

    private String getStringDate() {
        return STRING_FORMAT.format(date);
    }

    public int getMark() {
        return mark;
    }

    public String getStringMark() {
        return String.valueOf(mark);
    }

    public int getMarkFromString(String mark) {
        return Integer.parseInt(mark);
    }

    @Override
    public String toString() {
        return login + Constants.STRING_DELIMITER + test + Constants.STRING_DELIMITER + getStringDate() + Constants.STRING_DELIMITER + getStringMark();
    }
}
