package nl.xyzairlines.xyzairlines.controller;

import nl.xyzairlines.xyzairlines.model.Airport;
import nl.xyzairlines.xyzairlines.repository.AirportRepository;
import nl.xyzairlines.xyzairlines.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/airport")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private PlaneRepository planeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Airport save(@RequestBody Airport airport){
        return airportRepository.save(airport);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Airport> findAll(){
        return airportRepository.findAll();
    }



}
