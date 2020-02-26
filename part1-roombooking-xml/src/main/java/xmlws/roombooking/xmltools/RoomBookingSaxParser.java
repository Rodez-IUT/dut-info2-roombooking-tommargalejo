/*
 * RoomBookingSaxParser.java                                                         26                                   26/02/2020
 * Pas de copyright
 */

package xmlws.roombooking.xmltools;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;

/**
 * TODO commenter le rôle de la classe
 *
 * @author tommargalejo
 * @version 1.0
 */
public class RoomBookingSaxParser implements RoomBookingParser {
    private RoomBooking roomBooking;

    @Override
    public RoomBooking parse(InputStream inputStream) {
        try {
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingBasicHandler());
        }catch (Exception e) {

        }
        return this.roomBooking;
    }

    private class RoomBookingBasicHandler extends DefaultHandler {
        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
            throws SAXException {
            System.out.println(qName);
        }


    }
}