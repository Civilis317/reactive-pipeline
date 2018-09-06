package net.playground.reactive.demo.controller;

import net.playground.reactive.demo.model.GeoLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/api/publication")
public class PublicationController extends AbstractController {
    private static final Logger logger = LoggerFactory.getLogger(PublicationController.class);

    @PostMapping("/publish")
    public @ResponseBody PublicationResponse receive(@RequestBody PublicationRequest request) {
        RequestValidator.validate(request);
        publish(request);
        PublicationResponse response = new PublicationResponse();
        response.setPublicationDatetime(new Date());
        response.setGeoLocationList(request.getGeoLocationList());
        return response;
    }

    @GetMapping("/example")
    public @ResponseBody PublicationRequest getExampleRequest() {
        PublicationRequest request = new PublicationRequest();
        IntStream.range(0,10).forEach(i -> {
            request.getGeoLocationList().add(getRandomGeoLocation(new Random()));
        });
        return request;
    }

    private void publish(PublicationRequest request) {
        request.getGeoLocationList().forEach(l -> {
            logger.debug(l.toString());
        });
    }

    private GeoLocation getRandomGeoLocation (Random rand) {
        GeoLocation geoLocation = new GeoLocation();
        geoLocation.setName(UUID.randomUUID().toString());
        geoLocation.setDatetime(new Date());
        geoLocation.setLatitude(90 * rand.nextDouble());
        geoLocation.setLongitude(180 * rand.nextDouble());
        geoLocation.setElevation(100 * rand.nextDouble());
        return geoLocation;
    }
}
