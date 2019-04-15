package io.gupy.sw.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import io.gupy.sw.model.Planet;
import io.gupy.sw.service.PlanetService;

@RunWith(SpringRunner.class)
@WebMvcTest(PlanetController.class)
public class PlanetControllerTest {
    
    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private PlanetService planetService;
    
    @Test
    public void givenPlanets_whenGetMany_thenReturnJsonArray() throws Exception {
        Planet earth = new Planet();
        earth.setName("earth");
        
        List<Planet> allPlanets = Arrays.asList(earth);
     
        given(planetService.getMany(null)).willReturn(allPlanets);
     
        mvc.perform(get("/api/planets")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name").value(earth.getName()));
    }
    
    @Test
    public void givenPlanets_whenGetSwapi_thenReturnJsonArray() throws Exception {
        Planet earth = new Planet();
        earth.setName("earth");
        
        List<Planet> allPlanets = Arrays.asList(earth);
     
        given(planetService.getSwapi()).willReturn(allPlanets);
     
        mvc.perform(get("/api/planets/swapi")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$", hasSize(1)))
          .andExpect(jsonPath("$[0].name").value(earth.getName()));
    }
    
    @Test
    public void givenPlanet_whenGet_thenReturnJson() throws Exception {
        Planet earth = new Planet();
        earth.setId("1");
        earth.setName("earth");
        
        given(planetService.get(earth.getId())).willReturn(earth);
     
        mvc.perform(get("/api/planets/" + earth.getId())
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.name").value(earth.getName()));
    }
    
    @Test
    public void givenPlanet_whenSave_thenReturnJson() throws Exception {
        Gson gson = new Gson();
        Planet earth = new Planet();
        earth.setName("earth");
        
        given(planetService.save(earth)).willReturn(earth);
        
        mvc.perform(post("/api/planets")
          .content(gson.toJson(earth))
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
    
    @Test
    public void givenPlanet_whenDelete_thenReturnJson() throws Exception {
        Planet earth = new Planet();
        earth.setId("1");
        earth.setName("earth");
        
        given(planetService.delete(earth.getId())).willReturn(earth);
        
        mvc.perform(delete("/api/planets/" + earth.getId())
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }
}