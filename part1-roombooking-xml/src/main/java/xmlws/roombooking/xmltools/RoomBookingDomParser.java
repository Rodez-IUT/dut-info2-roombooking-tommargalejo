/*
 * RoomBookingDomParser.java                                                         26                                   26/02/2020
 * Pas de copyright
 */

package xmlws.roombooking.xmltools;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.text.SimpleDateFormat;

/**
 * TODO commenter le rôle de la classe
 *
 * @author tommargalejo
 * @version 1.0
 */
public class RoomBookingDomParser implements RoomBookingParser {
    private RoomBooking roomBooking;

    @Override
    public RoomBooking parse(InputStream inputStream) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        roomBooking = new RoomBooking();
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(inputStream);
            roomBooking.setRoomLabel(doc.getElementsByTagName("label").item(0).getTextContent());
            roomBooking.setUsername(doc.getElementsByTagName("username").item(0).getTextContent());
            roomBooking.setStartDate(sdf.parse(doc.getElementsByTagName("startDate").item(0).getTextContent()));
            roomBooking.setEndDate(sdf.parse(doc.getElementsByTagName("endDate").item(0).getTextContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roomBooking;
    }
}