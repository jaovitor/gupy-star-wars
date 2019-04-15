package io.gupy.sw.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.gupy.sw.model.Planet;

@Repository
public interface PlanetRepository extends CrudRepository<Planet, String> {
     
    Iterable<Planet> findByName(String nome);
}