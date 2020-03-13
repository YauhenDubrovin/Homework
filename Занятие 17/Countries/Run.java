package countries;

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
    private static final String COUNTRIES_XML = "countries.xml";

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException
    {
        System.out.println(" ========================= SAX parser ==============================");
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        CountryHandler countryHandler = new CountryHandler();
        saxParser.parse(new File(COUNTRIES_XML), countryHandler);
        List<Country> countries = countryHandler.getCountries();
        countries.forEach(country -> System.out.println(country));

        System.out.println(" ============================== STAX parser =========================");
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(COUNTRIES_XML));
        countries = new StaxParser().parse(xmlEventReader);
        countries.forEach(country -> System.out.println(country));

        System.out.println(" ============================== DOM parser =========================");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(COUNTRIES_XML);
        countries = new DomParser().parse(document);
        countries.forEach(country -> System.out.println(country));
    }
}
