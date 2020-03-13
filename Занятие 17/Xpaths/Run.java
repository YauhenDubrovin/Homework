package xpaths;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Run {
    private static final String XPATHS_XML = "xpaths.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException {
        System.out.println(" ========================= SAX parser ==============================");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        XpathHandler xpathHandler = new XpathHandler();
        saxParser.parse(new File(XPATHS_XML), xpathHandler);
        List<Xpath> xpaths = xpathHandler.getXpaths();
        xpaths.forEach(xpath -> System.out.println(xpath));

        System.out.println(" ============================== STAX parser =========================");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(XPATHS_XML));
        xpaths = new StaxParser().parse(xmlEventReader);
        xpaths.forEach(xpath -> System.out.println(xpath));

        System.out.println(" ============================== DOM parser =========================");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(XPATHS_XML);
        xpaths = new DomParser().parse(document);
        xpaths.forEach(xpath -> System.out.println(xpath));
    }
}
