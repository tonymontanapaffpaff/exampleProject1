package by.gsu.epamlab.impl;

import by.gsu.epamlab.ResultHandler;
import by.gsu.epamlab.beans.DecimalResult;
import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.consts.Constants;
import by.gsu.epamlab.exceptions.SourceException;
import by.gsu.epamlab.interfaces.IResultDAO;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResultImplXml implements IResultDAO {
    private Iterator<DecimalResult> iterator;

    public ResultImplXml(String input) throws SourceException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        ResultHandler handler = new ResultHandler();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(input, handler);
            List<DecimalResult> results = new ArrayList<>(handler.getResults());
            iterator = results.iterator();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new SourceException(Constants.INPUT_EXCEPTION);
        }
    }

    @Override
    public Result nextResult() {
        return iterator.next();
    }

    @Override
    public boolean hasResult() {
        return iterator.hasNext();
    }

    @Override
    public void closeReader() {
        iterator = null;
    }
}
