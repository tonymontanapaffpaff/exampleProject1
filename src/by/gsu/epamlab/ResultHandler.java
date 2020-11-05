package by.gsu.epamlab;

import by.gsu.epamlab.beans.DecimalResult;
import by.gsu.epamlab.beans.HalfResult;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ResultHandler extends DefaultHandler {
    private enum ResultEnum {
        RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }
    private List<DecimalResult> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;
    private String test;
    private String date;
    private String mark;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentEnum = ResultEnum.valueOf(localName.toUpperCase());
        if (currentEnum == ResultEnum.TEST) {
            final int TEST_INDEX = 0, DATE_INDEX = 1, MARK_INDEX = 2;
            DecimalResult result = new DecimalResult(login, attributes.getValue(TEST_INDEX), attributes.getValue(DATE_INDEX), attributes.getValue(MARK_INDEX));
            results.add(result);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentEnum == ResultEnum.LOGIN) {
            String str = new String(ch, start, length).trim();
            if (!str.isEmpty()) {
                login = str;
            }
        }
    }

    public List<DecimalResult> getResults() {
        return results;
    }
}
