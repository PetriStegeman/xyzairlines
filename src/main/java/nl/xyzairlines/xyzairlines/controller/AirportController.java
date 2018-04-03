package nl.xyzairlines.xyzairlines.controller;

import nl.xyzairlines.xyzairlines.model.Airport;
import nl.xyzairlines.xyzairlines.repository.AirportRepository;
import nl.xyzairlines.xyzairlines.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Airport findOne(@PathVariable String name){
        return airportRepository.findOne(name);
    }



}
