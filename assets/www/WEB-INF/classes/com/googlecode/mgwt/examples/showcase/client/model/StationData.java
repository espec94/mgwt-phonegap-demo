package com.googlecode.mgwt.examples.showcase.client.model;

public class StationData {
    private String trainCode;
    private String stationFullName;
    private String stationCode;
    private String queryTime;
    private String destination;
    private String direction;
    private String trainType;
    private int dueIn;
    private String expectedArrival;
    private String scheduledArrival;
    private String lastLocation;


    public StationData(String trainCode, String stationFullName, String stationCode, String queryTime, String destination, String direction, String trainType, int dueIn, String expectedArrival, String scheduledArrival, String lastLocation) {
        this.trainCode = trainCode;
        this.stationFullName = stationFullName;
        this.stationCode = stationCode;
        this.queryTime = queryTime;
        this.destination = destination;
        this.direction = direction;
        this.trainType = trainType;
        this.dueIn = dueIn;
        this.expectedArrival = expectedArrival;
        this.scheduledArrival = scheduledArrival;
        this.lastLocation = lastLocation;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public String getStationFullName() {
        return stationFullName;
    }

    public String getStationCode() {
        return stationCode;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public String getDestination() {
        return destination;
    }

    public String getDirection() {
        return direction;
    }

    public String getTrainType() {
        return trainType;
    }

    public int getDueIn() {
        return dueIn;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public String getScheduledArrival() {
        return scheduledArrival;
    }

    public String getLastLocation() {
        return lastLocation;
    }

}
