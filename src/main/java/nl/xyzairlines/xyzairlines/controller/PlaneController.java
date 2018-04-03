package nl.xyzairlines.xyzairlines.controller;

import nl.xyzairlines.xyzairlines.model.Airport;
import nl.xyzairlines.xyzairlines.model.Plane;
import nl.xyzairlines.xyzairlines.repository.AirportRepository;
import nl.xyzairlines.xyzairlines.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/plane")
public class PlaneController {

    @Autowired
    private AirportRepository airportRepository;
    @Autowired
    private PlaneRepository planeRepository;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Plane save(@RequestBody Plane plane){
        // First make sure the airport the new plane arrived at exist.
        Iterable<Airport> foundAirports = airportRepository.findAll();
        Airport correctAirport = new Airport();
        for (Airport airport: foundAirports) {
            if(airport.getName() == plane.getAirport().getName()) {
                // Airport exists in db.
                correctAirport  = airport;
            }
        }

        // If airport exists, save the plane with the airport from the DB.
        if(correctAirport.getName().length() > 0){
            plane.setAirport(correctAirport);
            return planeRepository.save(plane);
        }
        else {
            // No existing writer, throw error.
            throw new RuntimeException();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Plane> findAll(){
        return planeRepository.findAll();
    }

}
