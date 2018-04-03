package nl.xyzairlines.xyzairlines.controller;

import nl.xyzairlines.xyzairlines.model.Airport;
import nl.xyzairlines.xyzairlines.model.Plane;
import nl.xyzairlines.xyzairlines.repository.AirportRepository;
import nl.xyzairlines.xyzairlines.repository.PlaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Plane reFuel(@PathVariable("id") long planeId){
        Iterable<Plane> allPlanes = planeRepository.findAll();
        Plane output = new Plane();
        for (Plane plane: allPlanes) {
            if(plane.getPlaneId() == planeId) {
                output.setAirport(plane.getAirport());
                output.setFuel(5000);
                output.setType(plane.getType());
                output.setPlaneId(plane.getPlaneId());
                return output;
            }
        }
        throw new RuntimeException();
    }

}
