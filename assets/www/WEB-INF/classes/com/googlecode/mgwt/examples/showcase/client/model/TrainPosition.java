package com.googlecode.mgwt.examples.showcase.client.model;

public class TrainPosition {

    private String trainStatus;
    private double trainLatitude;
    private String trainCode;
    private double trainLongitude;

    public TrainPosition(String trainStatus, double trainLatitude, double trainLongitude, String trainCode) {
        this.trainStatus = trainStatus;
        this.trainLatitude = trainLatitude;
        this.trainLongitude = trainLongitude;
        this.trainCode = trainCode;
    }

    public String getTrainStatus() {
        return trainStatus;
    }

    public double getTrainLatitude() {
        return trainLatitude;
    }

    public String getTrainCode() {
        return trainCode;
    }

    public double getTrainLongitude() {
        return trainLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrainPosition that = (TrainPosition) o;

        if (Double.compare(that.trainLatitude, trainLatitude) != 0) return false;
        if (Double.compare(that.trainLongitude, trainLongitude) != 0) return false;
        if (!trainCode.equals(that.trainCode)) return false;
        if (!trainStatus.equals(that.trainStatus)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = trainStatus.hashCode();
        temp = Double.doubleToLongBits(trainLatitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + trainCode.hashCode();
        temp = Double.doubleToLongBits(trainLongitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
