package nl.xyzairlines.xyzairlines.controller;

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
        return planeRepository.save(plane);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Plane> findAll(){
        return planeRepository.findAll();
    }

}
