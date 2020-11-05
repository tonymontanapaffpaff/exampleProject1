package by.gsu.epamlab.impl;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.factories.ResultFactory;
import by.gsu.epamlab.interfaces.IResultDAO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ResultImplCsv implements IResultDAO {
    private Scanner sc;
    private ResultFactory factory;

    public ResultImplCsv(String input, ResultFactory factory) throws SourceException {
        this.factory = factory;
        try {
            this.sc = new Scanner(new FileReader(input));
        } catch (FileNotFoundException e) {
            throw new SourceException(Constants.INPUT_EXCEPTION);
        }
    }

    @Override
    public Result nextResult() {
        return factory.getResultFromFactory(sc);
    }

    @Override
    public boolean hasResult() {
        return sc.hasNext();
    }

    @Override
    public void closeReader() {
        if (sc != null) {
            sc.close();
        }
    }
}
