package xpaths;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PATH = "xpath";
    private static final String ELEMENT = "element";
    private Xpath xpath;
    List<Xpath> xpaths = new ArrayList<>();

    public List<Xpath> parse(XMLEventReader xmlEventReader) throws FileNotFoundException, XMLStreamException {
        while (xmlEventReader.hasNext()) {
            XMLEvent xmlEvent = xmlEventReader.nextEvent();
            proceedStartElement(xmlEvent, xmlEventReader);
            proceedEndElement(xmlEvent);
        }
        return xpaths;
    }

    private void proceedStartElement(XMLEvent xmlEvent, XMLEventReader xmlEventReader) throws XMLStreamException {
        if (xmlEvent.isStartElement()) {
            StartElement startElement = xmlEvent.asStartElement();
            if (isTagNameEqual(startElement, ELEMENT)) {
                xpath = new Xpath();
                Attribute attribute = startElement.getAttributeByName(new QName(ID));
                if (attribute != null) {
                    xpath.setId(Integer.parseInt(attribute.getValue()));
                }
            } else if (isTagNameEqual(startElement, NAME)) {
                xpath.setName(xmlEventReader.nextEvent().asCharacters().getData());
            } else if (isTagNameEqual(startElement, PATH)) {
                xpath.setPath(xmlEventReader.nextEvent().asCharacters().getData());
            }
        }
    }

    private void proceedEndElement(XMLEvent xmlEvent) {
        if (xmlEvent.isEndElement()) {
            EndElement endElement = xmlEvent.asEndElement();
            if (endElement.getName().getLocalPart().equals(ELEMENT)) {
                xpaths.add(xpath);
            }
        }
    }

    private boolean isTagNameEqual(StartElement startElement, String tagName) {
        return startElement.getName().getLocalPart().equals(tagName);
    }
}
