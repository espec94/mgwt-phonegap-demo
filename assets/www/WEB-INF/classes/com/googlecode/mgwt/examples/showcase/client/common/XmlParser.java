package com.googlecode.mgwt.examples.showcase.client.common;

import com.google.gwt.xml.client.*;
import com.googlecode.mgwt.examples.showcase.client.model.Station;

import java.util.Map;

public class XmlParser {

    public static void parseMessage(String messageXml, Map stationList) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(messageXml);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objStation");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                final Element node = (Element) nodeList.item(i);
                String stationDesc = messageDom.getElementsByTagName("StationDesc").item(i).getFirstChild().getNodeValue();
                //String stationAlias = messageDom.getElementsByTagName("StationAlias").item(i).getFirstChild().getNodeValue();
                String stationAlias = "";
                String stationLatitude = messageDom.getElementsByTagName("StationLatitude").item(i).getFirstChild().getNodeValue();
                String stationLongitude = messageDom.getElementsByTagName("StationLongitude").item(i).getFirstChild().getNodeValue();
                String stationCode = messageDom.getElementsByTagName("StationCode").item(i).getFirstChild().getNodeValue();
                int stationId = Integer.parseInt(messageDom.getElementsByTagName("StationId").item(i).getFirstChild().getNodeValue());

                Station station = new Station(stationId, stationCode, stationLongitude, stationLatitude, stationDesc, stationAlias);
                stationList.put(stationDesc, station);
            }

        } catch (DOMException e) {
        }
    }

}
