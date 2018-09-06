package net.playground.reactive.demo.controller;

public class RequestValidator {

    public static void validate(PublicationRequest request) {
        if (request == null) throw new IllegalArgumentException("request is mandatory");
        if (request.getGeoLocationList() == null || request.getGeoLocationList().isEmpty()) {
            throw new IllegalArgumentException("no content in request");
        }
    }

}
