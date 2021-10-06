package ru.ubusheev.springtest.controllers;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ubusheev.springtest.dao.AircraftDAO;
import ru.ubusheev.springtest.model.Aircraft;
import ru.ubusheev.springtest.model.*;
import ru.ubusheev.springtest.service.RestService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AircraftController {

    private final AircraftDAO aircraftDAO;

    @Autowired
    public AircraftController(AircraftDAO aircraftDAO){
        this.aircraftDAO = aircraftDAO;
    }

    @GetMapping(path="/air/{id}")
    public Aircraft getAirflight(@PathVariable int id) {
        return aircraftDAO.findById(id);
    }

    @GetMapping(path="/air")
    public Aircraft getAirflight(@RequestParam String departure, String destination, String outbounddate, @RequestParam(required = false, defaultValue = "0") String inbounddate) {
        Aircraft aircraft = new Aircraft();
        aircraft.setDepCode(departure);
        aircraft.setDesCode(destination);
        aircraft.setOutbounddate(LocalDate.parse(outbounddate));
        if(!inbounddate.equals("0"))
            aircraft.setInbounddate(LocalDate.parse(inbounddate));
        else aircraft.setInbounddate(null);
        int a = aircraftDAO.exist(aircraft);
        if(a != 0) {
            aircraft.setID(a);
            return aircraftDAO.findById(aircraft.getID());
        }
        else {
            Quote quote = getQuote(departure, destination, outbounddate, inbounddate);
            aircraft = toPOJO(quote);
            aircraftDAO.save(aircraft);
            return aircraft;
        }
    }

    @GetMapping(path="/airs")
    public List<Aircraft> getAirflights() {
        return aircraftDAO.findAll();
    }

    @GetMapping("/skyscanner")
    public Quote getSky(@RequestParam String departure, String destination, String outbounddate, @RequestParam(required = false, defaultValue = "0") String inbounddate) {
        RestService restService = new RestService();
        Quote quote = getQuote(departure, destination, outbounddate, inbounddate);
        Aircraft aircraft = toPOJO(quote);
        int a = aircraftDAO.exist(aircraft);
        if(a != 0)
            aircraft.setID(a);
        aircraftDAO.saveOrUpdate(aircraft);
        return quote;
    }


    @GetMapping(path = "/deleteair")
    public void deleteAircraft(@RequestParam int id) {
        aircraftDAO.delete(id);
    }

      

    public Aircraft toPOJO(Quote quote){
        int index = quote.getQuotes().get(0).getOutboundLeg().getDepartureDate().indexOf("T");
        Quote__1 quote__1 = quote.getQuotes().get(0);
        Place dep = quote.getPlaces().stream()
                .filter(a -> a.getPlaceId().equals(quote.getQuotes().get(0).getOutboundLeg().getOriginId()))
                .findAny()
                .get();
        Place des = quote.getPlaces().stream()
                .filter(a -> a.getPlaceId().equals(quote.getQuotes().get(0).getOutboundLeg().getDestinationId()))
                .findAny()
                .get();

        Aircraft aircraft = new Aircraft();
        aircraft.setDeparture(dep.getName());
        aircraft.setDestination(des.getName());
        aircraft.setAirfare(quote__1.getMinPrice());
        aircraft.setOutbounddate(LocalDate.parse(quote__1.getOutboundLeg().getDepartureDate().substring(0, index)));
        aircraft.setCarrier(quote.getCarriers().get(0).getName());
        aircraft.setDepCode(dep.getSkyscannerCode());
        aircraft.setDesCode(des.getSkyscannerCode());
        if(quote__1.isExistInboundLeg())
            aircraft.setInbounddate(LocalDate.parse(quote__1.getInboundLeg().getDepartureDate().substring(0, index)));
        return aircraft;
    }

    public Quote getQuote(String departure, String destination, String outbounddate, String inbounddate){
        Quote quote = null;
        try {
            quote = new RestService().get(departure, destination, outbounddate, inbounddate).getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return quote;
    }

}
