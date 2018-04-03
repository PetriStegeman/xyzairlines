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
    public Airport findOne(@PathVariable long id){
        return airportRepository.findOne(id);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public Airport update(@PathVariable String name, @RequestBody String newName){

        Iterable<Airport> allAirports = airportRepository.findAll();
        Airport correctAirport = new Airport();
        for (Airport airport: allAirports) {
            if(airport.getName() == name) {
                correctAirport = airport;
                correctAirport.setName(newName);
            }
        }
        return correctAirport;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id){
        airportRepository.delete(id);
    }

}
