package xpaths;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DomParser {
    public List<Xpath> parse(Document document) throws FileNotFoundException, XMLStreamException {
        NodeList nodeList = document.getElementsByTagName("element");
        List<Xpath> xpaths = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            xpaths.add(getXpath(nodeList.item(i)));
        }
        return xpaths;
    }

    private static Xpath getXpath(Node node) {
        Xpath xpath = new Xpath();
        Element element = (Element) node;
        xpath.setId(Integer.parseInt(element.getAttribute("id")));
        xpath.setName(getTagValue("name", element));
        xpath.setPath(getTagValue("xpath", element));
        return xpath;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
