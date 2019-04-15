package io.gupy.sw.service;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import io.gupy.sw.model.Page;
import io.gupy.sw.model.Planet;

@Service
public class SwapiPlanetService {
    
    @Autowired
    private Environment environment;
    
    private static final Gson GSON = new Gson();

    @Cacheable("planets")
    public List<Planet> get() throws Exception {
        return getPlanets(environment.getProperty("swapi.planets.endpoint"), new ArrayList<>());
    }
    
    private List<Planet> getPlanets(String url, List<Planet> planets) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(request);
        String json = parseResponse(httpResponse);
        Page page = GSON.fromJson(json, Page.class);
        if (page.getNext() != null) {
            if (page.getResults() != null) {
                page.getResults().stream().forEach(planet -> {
                    planet.setMovieAppearances(planet.getFilms() != null ? planet.getFilms().size() : 0);
                    planet.setFilms(null);
                    planets.add(planet);
                });
            }
            return getPlanets(page.getNext(), planets);
        }
        return planets;
    }
    
    private String parseResponse(HttpResponse response) throws Exception {
        HttpEntity responseEntity = response.getEntity();
        InputStream inputStream = responseEntity.getContent();
        String out = null;
        try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            out = scanner.useDelimiter("\\A").next();
        }
        return out;
    }
}