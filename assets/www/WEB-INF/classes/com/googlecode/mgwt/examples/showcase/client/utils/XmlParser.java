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
            NodeList nodeList = messageDom.getDocumentElement().getElementsByTagName("objStation");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element entry = (Element)nodeList.item(i);
                String stationDesc = getElementAsString(entry,"StationDesc");
                String stationAlias = getElementAsString(entry,"StationAlias");
                String stationLatitude = getElementAsString(entry,"StationLatitude");
                String stationLongitude = getElementAsString(entry,"StationLongitude");
                String stationCode = getElementAsString(entry,"StationCode");
                int stationId = Integer.parseInt(getElementAsString(entry,"StationId"));

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
            NodeList nodeList = messageDom.getDocumentElement().getElementsByTagName("objStationData");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element entry = (Element)nodeList.item(i);
                String trainCode = getElementAsString(entry,"Traincode");
                String stationFullName = getElementAsString(entry,"Stationfullname");
                String stationCode = getElementAsString(entry,"Stationcode");
                String queryTime = getElementAsString(entry,"Querytime");
                String destination = getElementAsString(entry,"Destination");
                String direction = getElementAsString(entry,"Direction");
                String trainType = getElementAsString(entry,"Traintype");
                int dueIn = Integer.parseInt(getElementAsString(entry,"Duein"));
                String expectedArrival = getElementAsString(entry,"Exparrival");
                String scheduledArrival = getElementAsString(entry,"Scharrival");
                String lastLocation = getElementAsString(entry,"Lastlocation");

                stationDataList.add(new StationData(trainCode, stationFullName, stationCode, queryTime, destination, direction, trainType, dueIn, expectedArrival, scheduledArrival, lastLocation));
            }

        } catch (DOMException e) {
        }

    }

    private static String getElementAsString(Element messageDom, String tagName) {

        Node node = messageDom.getElementsByTagName(tagName).item(0).getFirstChild();
        if( node == null){
            return "";
        };

        return node.getNodeValue().trim();

    }

    public static void parseTrainPositionsXml(String responseText, Map<String, TrainPosition> listTrainPosition) {
        try {
            // parse the XML document into a DOM
            Document messageDom = XMLParser.parse(responseText);

            // find each station in an attribute of the <objStation> tag
            NodeList nodeList = messageDom.getDocumentElement().getElementsByTagName("objTrainPositions");

            //initialize Station object
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element entry = (Element)nodeList.item(i);
                String trainStatus = getElementAsString(entry,"TrainStatus");
                double trainLatitude = Double.parseDouble(getElementAsString(entry,"TrainLatitude"));
                double trainLongitude = Double.parseDouble(getElementAsString(entry,"TrainLongitude"));
                String trainCode = getElementAsString(entry,"TrainCode");

                listTrainPosition.put(trainCode, new TrainPosition(trainStatus, trainLatitude, trainLongitude, trainCode));
            }

        } catch (DOMException e) {
        }

    }
}
