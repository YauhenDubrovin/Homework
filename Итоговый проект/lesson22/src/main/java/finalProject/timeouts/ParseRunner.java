package finalProject.timeouts;

import finalProject.timeouts.model.Timeout;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ParseRunner {
    private static final String TIMEOUTS_XML = "timeouts.xml";

    public static List<Timeout> readTimeouts() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        TimeoutHandler timeoutHandler = new TimeoutHandler();
        try {
            saxParser.parse(new File(TIMEOUTS_XML), timeoutHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Timeout> timeouts = timeoutHandler.getTimeouts();
        return timeouts;
    }
}
