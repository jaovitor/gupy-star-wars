package io.gupy.sw.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "planets")
public class Planet {
    
    @Id
    private String id;
    
    private String name;
    
    private String climate;
    
    private String terrain;
    
    private Integer movieAppearances;
    
    @JsonIgnore
    @Transient
    private List<String> films;

    public Planet() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClimate() {
        return climate;
    }

    public void setClimate(String climate) {
        this.climate = climate;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public Integer getMovieAppearances() {
        return movieAppearances;
    }

    public void setMovieAppearances(Integer movieAppearances) {
        this.movieAppearances = movieAppearances;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Planet [id=" + id + ", name=" + name + ", climate=" + climate + ", terrain=" + terrain
                + ", movieAppearances=" + movieAppearances + ", films=" + films + "]";
    }
}