package com.googlecode.mgwt.examples.showcase.client.utils;

import com.google.gwt.xml.client.*;
import com.googlecode.mgwt.examples.showcase.client.model.Station;
import com.googlecode.mgwt.examples.showcase.client.model.StationData;
import com.googlecode.mgwt.examples.showcase.client.model.TrainPosition;

import java.util.List;
import java.util.Map;

public class XmlParser {

    public static void parseAllStationXml(String messageXml, Map stationList) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(messageXml);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objStation");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                String stationDesc = getElementAsString(messageDom,"StationDesc",i);
                String stationAlias = getElementAsString(messageDom,"StationAlias", i);
                String stationLatitude = getElementAsString(messageDom,"StationLatitude",i);
                String stationLongitude = getElementAsString(messageDom,"StationLongitude",i);
                String stationCode = getElementAsString(messageDom,"StationCode",i);
                int stationId = Integer.parseInt(getElementAsString(messageDom,"StationId",i));

                Station station = new Station(stationId, stationCode, stationLongitude, stationLatitude, stationDesc, stationAlias);
                stationList.put(stationDesc, station);
            }

        } catch (DOMException e) {
        }
    }

    public static void parseStationDataXml(String messageXml, List stationDataList) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(messageXml);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objStationData");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                String trainCode = getElementAsString(messageDom,"Traincode", i);
                String stationFullName = getElementAsString(messageDom,"Stationfullname", i);
                String stationCode = getElementAsString(messageDom,"Stationcode",i);
                String queryTime = getElementAsString(messageDom,"Querytime",i);
                String destination = getElementAsString(messageDom,"Destination",i);
                String direction = getElementAsString(messageDom,"Direction",i);
                String trainType = getElementAsString(messageDom,"Traintype",i);
                int dueIn = Integer.parseInt(getElementAsString(messageDom,"Duein",i));
                String expectedArrival = getElementAsString(messageDom,"Exparrival",i);
                String scheduledArrival = getElementAsString(messageDom,"Scharrival",i);
                String lastLocation = getElementAsString(messageDom,"Lastlocation",i);

                stationDataList.add(new StationData(trainCode, stationFullName, stationCode, queryTime, destination, direction, trainType, dueIn, expectedArrival, scheduledArrival, lastLocation));
            }

        } catch (DOMException e) {
        }

    }

    private static String getElementAsString(Document messageDom, String tagName, int i) {

        Node node = messageDom.getElementsByTagName(tagName).item(i).getFirstChild();
        if( node == null){
            return "";
        };

        return messageDom.getElementsByTagName(tagName).item(i).getFirstChild().getNodeValue().trim();

    }

    public static void parseTrainPositionsXml(String responseText, Map<String, TrainPosition> listTrainPosition) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(responseText);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getElementsByTagName("objTrainPositions");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                String trainStatus = getElementAsString(messageDom,"TrainStatus",i);
                double trainLatitude = Double.parseDouble(getElementAsString(messageDom,"TrainLatitude", i));
                double trainLongitude = Double.parseDouble(getElementAsString(messageDom,"TrainLongitude", i));
                String trainCode = getElementAsString(messageDom,"TrainCode", i);

                listTrainPosition.put(trainCode, new TrainPosition(trainStatus, trainLatitude, trainLongitude, trainCode));
            }

        } catch (DOMException e) {
        }

    }
}
