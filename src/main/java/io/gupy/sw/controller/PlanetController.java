package io.gupy.sw.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.gupy.sw.model.Planet;
import io.gupy.sw.service.PlanetService;

@RestController
@RequestMapping(value = "/api/planets")
public class PlanetController {

    private static final Logger LOG = LogManager.getLogger(PlanetController.class);
    
    @Autowired
    private PlanetService planetService;
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Planet get(@PathVariable(value = "id") String id) {
        try {
            return planetService.get(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public Iterable<Planet> getMany(@RequestParam(name = "name", required = false) String name) {
        try {
            return planetService.getMany(name);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    
    @RequestMapping(value = "/swapi", method = RequestMethod.GET, produces = "application/json")
    public Iterable<Planet> getSwapi() {
        try {
            return planetService.getSwapi();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    
    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Planet post(@RequestBody Planet planet) {
        try {
            return planetService.save(planet);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public Planet delete(@PathVariable(value = "id") String id) {
        try {
            return planetService.delete(id);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }
}