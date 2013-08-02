package com.googlecode.mgwt.examples.showcase.client.model;

public class Station {

    private int id;

    private String code;

    private String logitude;

    private String latitude;

    private String description;

    private String alias;

    public Station(int id, String code, String logitude, String latitude, String description, String alias){
        this.id = id;
        this.code = code;
        this.logitude = logitude;
        this.latitude = latitude;
        this.description = description;
        this.alias = alias;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLogitude() {
        return logitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDescription() {
        return description;
    }

    public String getAlias() {
        return alias;
    }
}
