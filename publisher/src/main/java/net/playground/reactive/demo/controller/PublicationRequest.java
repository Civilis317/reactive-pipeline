package net.playground.reactive.demo.controller;

import net.playground.reactive.demo.model.GeoLocation;

import java.util.ArrayList;
import java.util.List;

public class PublicationRequest {
    private List<GeoLocation> geoLocationList = new ArrayList<>(1024);

    // getters and setters
    public List<GeoLocation> getGeoLocationList() {
        return geoLocationList;
    }

    public void setGeoLocationList(List<GeoLocation> geoLocationList) {
        this.geoLocationList = geoLocationList;
    }
}
