package finalProject.timeouts;

import finalProject.timeouts.model.Timeout;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TimeoutHandler extends DefaultHandler {
    private List<Timeout> timeouts;
    private Timeout timeout;
    boolean isName = false;
    boolean isLength = false;

    public List<Timeout> getTimeouts() {
        return timeouts;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("element")) {
            String id = attributes.getValue("id");
            timeout = new Timeout();
            timeout.setId(Integer.parseInt(id));
            if (timeouts == null) {
                timeouts = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("name")) {
            isName = true;
        } else if (qName.equalsIgnoreCase("length")) {
            isLength = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("element")) {
            timeouts.add(timeout);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {

        if (isName) {
            timeout.setName(new String(characters, start, length));
            isName = false;
        } else if (isLength) {
            timeout.setLength(Integer.parseInt(new String(characters, start, length)));
            isLength = false;
        }
    }
}
