package xpaths;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class XpathHandler extends DefaultHandler {
    private List<Xpath> xpaths;
    private Xpath xpath;
    boolean isName = false;
    boolean isPath = false;

    public List<Xpath> getXpaths() {
        return xpaths;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("element")) {
            String id = attributes.getValue("id");
            xpath = new Xpath();
            xpath.setId(Integer.parseInt(id));
            if (xpaths == null) {
                xpaths = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("name")) {
            isName = true;
        } else if (qName.equalsIgnoreCase("xpath")) {
            isPath = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("element")) {
            xpaths.add(xpath);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {

        if (isName) {
            xpath.setName(new String(characters, start, length));
            isName = false;
        } else if (isPath) {
            xpath.setPath(new String(characters, start, length));
            isPath = false;
        }
    }
}
