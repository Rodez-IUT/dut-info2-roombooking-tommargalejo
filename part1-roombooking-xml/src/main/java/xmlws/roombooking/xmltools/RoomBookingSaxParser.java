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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            roomBooking = new RoomBooking();
            spf.setNamespaceAware(true);
            SAXParser saxParser = spf.newSAXParser();
            saxParser.parse(inputStream, new RoomBookingBasicHandler());
        }catch (Exception e) {

        }
        return this.roomBooking;
    }

    private class RoomBookingBasicHandler extends DefaultHandler {
        private String actualElement;
        public void startElement(String namespaceURI,
                                 String localName,
                                 String qName,
                                 Attributes atts)
            throws SAXException {
            actualElement = localName;
        }

        public void characters(char ch[], int start, int length)
                throws SAXException {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            String temp = new String(ch, start, length);
            if (!temp.startsWith("\n")) {
                if (actualElement.equals("label")) {
                    roomBooking.setRoomLabel(temp);
                } else if (actualElement.equals("username")) {
                    roomBooking.setUsername(temp);
                } else if (actualElement.equals("startDate")) {
                    try {
                        roomBooking.setStartDate(sdf.parse(temp));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (actualElement.equals("endDate")) {
                    try {
                        roomBooking.setEndDate(sdf.parse(temp));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}