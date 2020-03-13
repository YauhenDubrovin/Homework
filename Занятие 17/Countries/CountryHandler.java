package countries;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CountryHandler extends DefaultHandler {
    private List<Country> countries;
    private Country country;
    boolean isCode = false;
    boolean isName = false;
    boolean isDescription = false;


    public List<Country> getCountries() {
        return countries;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("Country")) {
            String id = attributes.getValue("id");
            country = new Country();
            country.setId(Integer.parseInt(id));
            if (countries == null) {
                countries = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("Code")) {
            isCode = true;
        } else if (qName.equalsIgnoreCase("Name")) {
            isName = true;
        } else if (qName.equalsIgnoreCase("Description")) {
            isDescription = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("Country")) {
            countries.add(country);
        }
    }

    @Override
    public void characters(char characters[], int start, int length) {

        if (isCode) {
            country.setCode(new String(characters, start, length));
            isCode = false;
        } else if (isName) {
            country.setName(new String(characters, start, length));
            isName = false;
        } else if (isDescription) {
            country.setDescription(new String(characters, start, length));
            isDescription = false;
        }
    }
}
