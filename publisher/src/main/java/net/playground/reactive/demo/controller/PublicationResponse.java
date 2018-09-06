package net.playground.reactive.demo.controller;

import net.playground.reactive.demo.model.GeoLocation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PublicationResponse {
    private Date publicationDatetime;
    private List<GeoLocation> geoLocationList = new ArrayList<>(1024);

    // getters and setters
    public Date getPublicationDatetime() {
        return publicationDatetime;
    }

    public void setPublicationDatetime(Date publicationDatetime) {
        this.publicationDatetime = publicationDatetime;
    }

    public List<GeoLocation> getGeoLocationList() {
        return geoLocationList;
    }

    public void setGeoLocationList(List<GeoLocation> geoLocationList) {
        this.geoLocationList = geoLocationList;
    }
}
