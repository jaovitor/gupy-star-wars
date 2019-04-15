package io.gupy.sw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.gupy.sw.model.Planet;
import io.gupy.sw.repository.PlanetRepository;

@Service
public class PlanetService {

    private PlanetRepository planetRepository;
    
    private SwapiPlanetService swapiPlanetService;
    
    @Autowired
    public PlanetService(PlanetRepository planetRepository, SwapiPlanetService swapiPlanetService) {
        this.planetRepository = planetRepository;
        this.swapiPlanetService = swapiPlanetService;
    }
    
    public Planet get(String id) {
        return this.planetRepository.findById(id).orElse(null);
    }
    
    public List<Planet> getMany(String name) {
        List<Planet> out = new ArrayList<>();
        if (name != null && !name.trim().isEmpty()) {
            this.planetRepository.findByName(name).forEach(out::add);
        } else {
            this.planetRepository.findAll().forEach(out::add);
        }
        return out;
    }
    
    public Planet save(Planet planet) {
        try {
            Planet planetSwapi = findPlanetByName(planet.getName());
            planet.setMovieAppearances(planetSwapi.getMovieAppearances());
        } catch (Exception e) {
            planet.setMovieAppearances(0);
        }
        return planetRepository.save(planet);
    }
    
    public Planet delete(String id) {
        Planet planet = get(id);
        planetRepository.delete(planet);
        return planet;
    }
    
    public List<Planet> getSwapi() throws Exception {
        return swapiPlanetService.get();
    }
    
    private Planet findPlanetByName(String name) throws Exception {
        return getSwapi().stream().filter(isNamed(name)).collect(Collectors.toList()).get(0);
    }
    
    private static Predicate<Planet> isNamed(String name) {
        return planet -> planet.getName().equalsIgnoreCase(name);
    }
}